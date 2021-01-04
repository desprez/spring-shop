package fr.training.samples.spring.shop.exportjob;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileSystemUtils;

import fr.training.samples.spring.shop.SpringBootBatchApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootBatchApp.class, ExportCustomerJobConfig.class})
public class ExportCustomerJobTest {

	private static final Logger LOG = LoggerFactory.getLogger(ExportCustomerJobTest.class);

	/** directory for temporary test files */
	/**
	 * TMP_DIR of type String
	 */
	private static final String TMP_DIR = "./tmp";

	/**
	 * orderExportJob of type Job
	 */
	@Autowired
	@Qualifier("exportJob")
	private Job exportJob;

	/**
	 * jobLauncher of type JobLauncher
	 */
	@Autowired
	private JobLauncher jobLauncher;

	/**
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {

		// setup test data
		LOG.debug("Delete tmp directory");
		FileSystemUtils.deleteRecursively(new File(TMP_DIR));
	}

	/**
	 * @throws Exception thrown by JobLauncherTestUtils
	 */
	@Test
	public void testJob() throws Exception {

		final File targetFile = new File(TMP_DIR + "/customer.txt");
		final JobParametersBuilder jobParameterBuilder = new JobParametersBuilder();
		jobParameterBuilder.addString("output-file", targetFile.getAbsolutePath());
		// run job
		final JobExecution jobExecution = getJobLauncherTestUtils(exportJob)
				.launchJob(jobParameterBuilder.toJobParameters());

		// - job status
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

		// - exported data
		assertTrue(targetFile.exists());
	}

	/**
	 * @param job
	 * @return
	 */
	private JobLauncherTestUtils getJobLauncherTestUtils(final Job job) {
		final JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
		jobLauncherTestUtils.setJob(job);
		jobLauncherTestUtils.setJobLauncher(jobLauncher);
		return jobLauncherTestUtils;
	}
}