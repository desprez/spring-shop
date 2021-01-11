package fr.training.samples.spring.shop.exportjob;

import java.io.IOException;
import java.io.Writer;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.SingleColumnRowMapper;

@Configuration
public class ExportCustomerJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Bean
	public Step exportStep(final FlatFileItemWriter<CustomerDto> exportWriter, final CustomerProcessor customerProcessor) {
		return stepBuilderFactory.get("export-step").<String, CustomerDto>chunk(10) //
				.reader(exportReader()) //
				.processor(customerProcessor) //
				.writer(exportWriter) //
				.build();
	}

	@Bean(name = "exportJob")
	public Job exportJob(final Step exportStep) {
		return jobBuilderFactory.get("export-job") //
				.validator(new DefaultJobParametersValidator(new String[] { "output-file" }, new String[] {})) //
				.incrementer(new RunIdIncrementer()) //
				.flow(exportStep) //
				.end() //
				.build();
	}

	/**
	 * ItemReader is an abstract representation of how data is provided as input to
	 * a Step. When the inputs are exhausted, the ItemReader returns null.
	 */
	@Bean
	public JdbcCursorItemReader<String> exportReader() {
		final JdbcCursorItemReader<String> reader = new JdbcCursorItemReader<String>();
		reader.setDataSource(dataSource);
		reader.setSql("SELECT id  FROM Customer");
		reader.setRowMapper(new SingleColumnRowMapper<String>());
		return reader;
	}

	/**
	 * ItemWriter is the output of a Step. The writer writes one batch or chunk of
	 * items at a time to the target system. ItemWriter has no knowledge of the
	 * input it will receive next, only the item that was passed in its current
	 * invocation.
	 */
	@StepScope // Mandatory for using jobParameters
	@Bean
	public FlatFileItemWriter<CustomerDto> exportWriter(
			@Value("#{jobParameters['output-file']}") final String outputFile) {
		final FlatFileItemWriter<CustomerDto> writer = new FlatFileItemWriter<CustomerDto>();
		writer.setResource(new FileSystemResource(outputFile));

		final DelimitedLineAggregator<CustomerDto> lineAggregator = new DelimitedLineAggregator<CustomerDto>();

		final BeanWrapperFieldExtractor<CustomerDto> fieldExtractor = new BeanWrapperFieldExtractor<CustomerDto>();
		fieldExtractor.setNames(new String[] { "id", "name", "password", "email", "street", "country", "postalCode" });
		lineAggregator.setFieldExtractor(fieldExtractor);
		lineAggregator.setDelimiter(";");

		writer.setLineAggregator(lineAggregator);
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(final Writer writer) throws IOException {
				writer.write("id;name;password;email;street;city;country;postalCode");
			}
		});
		return writer;
	}
}