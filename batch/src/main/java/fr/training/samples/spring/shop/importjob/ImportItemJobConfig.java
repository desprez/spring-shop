package fr.training.samples.spring.shop.importjob;

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
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import fr.training.samples.spring.shop.common.FullReportListener;

@Configuration
public class ImportItemJobConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportItemJobConfig.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private FullReportListener listener;

	@Autowired
	private DeleteTasklet deleteTasklet;

	@Bean
	public Job importJob() {
		return jobBuilderFactory.get("import-job") //
				.incrementer(new RunIdIncrementer()) //
				.start(deleteStep()) //
				.next(importStep()) //
				.listener(listener) //
				.build();
	}

	/**
	 * Delete Step for deleting all Book records.
	 *
	 * @return the Step
	 */
	@Bean
	public Step deleteStep() {
		return stepBuilderFactory.get("delete-step") //
				.tasklet(deleteTasklet) //
				.build();
	}

	@Bean
	public Step importStep() {
		return stepBuilderFactory.get("import-step") //
				.<ItemDto, ItemDto>chunk(5) //
				.reader(importReader(null)) //
				.processor(importProcessor()) //
				.writer(importWriter()) //
				.faultTolerant() //
				//.skipPolicy(new CustomSkipPolicy()) //
				.skip(FlatFileParseException.class) //
				.skipLimit(2) //
				.build();
	}

	/**
	 * Fake processor that only logs
	 *
	 * @return an item processor
	 */
	private ItemProcessor<ItemDto, ItemDto> importProcessor() {
		return new ItemProcessor<ItemDto, ItemDto>() {

			@Override
			public ItemDto process(final ItemDto item) throws Exception {
				LOGGER.info(item.toString());
				return item;
			}
		};
	}

	@StepScope // Mandatory for using jobParameters
	@Bean
	public FlatFileItemReader<ItemDto> importReader(@Value("#{jobParameters['input-file']}") final String inputFile) {
		final FlatFileItemReader<ItemDto> reader = new FlatFileItemReader<ItemDto>();
		final DefaultLineMapper<ItemDto> lineMapper = new DefaultLineMapper<ItemDto>();

		final DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(";");
		tokenizer.setNames(new String[] { "id", "description", "price" });
		lineMapper.setLineTokenizer(tokenizer);

		final BeanWrapperFieldSetMapper<ItemDto> fieldSetMapper = new BeanWrapperFieldSetMapper<ItemDto>();
		fieldSetMapper.setTargetType(ItemDto.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		reader.setResource(new FileSystemResource(inputFile));
		reader.setLineMapper(lineMapper);
		reader.setLinesToSkip(1);
		return reader;
	}

	@Bean
	public JdbcBatchItemWriter<ItemDto> importWriter() {
		final JdbcBatchItemWriter<ItemDto> writer = new JdbcBatchItemWriter<ItemDto>();
		writer.setDataSource(dataSource);
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ItemDto>());
		writer.setSql("INSERT INTO item(id, description, price, version) VALUES (:id, :description, :price, 1)");
		return writer;
	}
}