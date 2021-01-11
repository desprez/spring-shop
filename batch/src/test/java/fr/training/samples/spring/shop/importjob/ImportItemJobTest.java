package fr.training.samples.spring.shop.importjob;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
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

import fr.training.samples.spring.shop.SpringBootBatchApp;
import fr.training.samples.spring.shop.common.FullReportListener;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootBatchApp.class, ImportItemJobConfig.class, FullReportListener.class,
		DeleteTasklet.class })
public class ImportItemJobTest {

	/**
	 * orderExportJob of type Job
	 */
	@Autowired
	@Qualifier("importJob")
	private Job importJob;

	/**
	 * jobLauncher of type JobLauncher
	 */
	@Autowired
	private JobLauncher jobLauncher;

	@Test
	public void importItemJob_should_success() throws Exception {
		// Given
		final JobParametersBuilder jobParameterBuilder = new JobParametersBuilder();
		jobParameterBuilder.addString("input-file", "src/main/resources/sample-data.csv").toJobParameters();
		// When
		final JobExecution jobExecution = getJobLauncherTestUtils(importJob)
				.launchJob(jobParameterBuilder.toJobParameters());
		// Then
		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
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