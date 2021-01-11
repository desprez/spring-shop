package fr.training.samples.spring.shop.exportjob;


import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
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
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class ExportCustomerJobConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExportCustomerJobConfig.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Bean
	public Step exportStep(final FlatFileItemWriter<CustomerDto> exportWriter) {
		return stepBuilderFactory.get("export-step").<CustomerDto, CustomerDto>chunk(10) //
				.reader(exportReader()) //
				.processor(exportProcessor()) //
				.writer(exportWriter) //
				.build();
	}

	@Bean(name = "exportJob")
	public Job exportBookJob(final Step exportStep) {
		return jobBuilderFactory.get("export-job") //
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
	public JdbcCursorItemReader<CustomerDto> exportReader() {
		final JdbcCursorItemReader<CustomerDto> reader = new JdbcCursorItemReader<CustomerDto>();
		reader.setDataSource(dataSource);
		reader.setSql("SELECT id, name, password FROM Customer");
		reader.setRowMapper(new CustomerRowMapper());

		return reader;
	}

	/**
	 * RowMapper used to map resultset to OrderDto
	 */
	public class CustomerRowMapper implements RowMapper<CustomerDto> {

		@Override
		public CustomerDto mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			final CustomerDto customer = new CustomerDto();
			customer.setId(rs.getString("id"));
			customer.setName(rs.getString("name"));
			customer.setPassword(rs.getString("password"));
			return customer;
		}
	}

	/**
	 * ItemProcessor represents the business processing of an item. The data read by
	 * ItemReader can be passed on to ItemProcessor. In this unit, the data is
	 * transformed and sent for writing. If, while processing the item, it becomes
	 * invalid for further processing, you can return null. The nulls are not
	 * written by ItemWriter.
	 */
	@Bean
	public ItemProcessor<CustomerDto, CustomerDto> exportProcessor() {
		return new ItemProcessor<CustomerDto, CustomerDto>() {

			@Override
			public CustomerDto process(final CustomerDto customer) throws Exception {
				LOGGER.info("Processing {}", customer);
				return customer;
			}
		};
	}

	/**
	 * ItemWriter is the output of a Step. The writer writes one batch or chunk of
	 * items at a time to the target system. ItemWriter has no knowledge of the
	 * input it will receive next, only the item that was passed in its current
	 * invocation.
	 */
	@StepScope // Mandatory for using jobParameters
	@Bean
	public FlatFileItemWriter<CustomerDto> exportWriter(@Value("#{jobParameters['output-file']}") final String outputFile) {
		final FlatFileItemWriter<CustomerDto> writer = new FlatFileItemWriter<CustomerDto>();
		writer.setResource(new FileSystemResource(outputFile));
		final DelimitedLineAggregator<CustomerDto> lineAggregator = new DelimitedLineAggregator<CustomerDto>();
		final BeanWrapperFieldExtractor<CustomerDto> fieldExtractor = new BeanWrapperFieldExtractor<CustomerDto>();
		fieldExtractor.setNames(new String[] { "id", "name", "password" });
		lineAggregator.setFieldExtractor(fieldExtractor);
		lineAggregator.setDelimiter(";");
		writer.setLineAggregator(lineAggregator);
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(final Writer writer) throws IOException {
				writer.write("id;name;password");
			}
		});
		return writer;
	}
}