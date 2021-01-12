package fr.training.samples.spring.shop.importjob;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import fr.training.samples.spring.shop.common.FullReportListener;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

@Configuration
public class ImportItemJobConfig {

	private static final Logger logger = LoggerFactory.getLogger(ImportItemJobConfig.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FullReportListener listener;

	@Autowired
	private DeleteTasklet deleteTasklet;

	@Autowired
	private ItemRepository itemRepository;

	@Bean
	public Job importJob() throws IOException {
		return jobBuilderFactory.get("import-job") //
				.validator(new DefaultJobParametersValidator(new String[] { "input-file" }, new String[] {})) //
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
	public Step importStep() throws IOException {
		return stepBuilderFactory.get("import-step") //
				.<ItemDto, Item>chunk(5) //
				.reader(importReader(null)) //
				.processor(importProcessor()) //
				.writer(importWriter()) //
				.faultTolerant() //
				// .skipPolicy(new CustomSkipPolicy()) //
				//.skipLimit(2) //
				//.listener(new MySkipListener<>(new File("rejects.txt"))) //
				.build();
	}

	/**
	 * ItemProcessor represents the business processing of an item. The data read by
	 * ItemReader can be passed on to ItemProcessor. In this unit, the data is
	 * transformed and sent for writing. If, while processing the item, it becomes
	 * invalid for further processing, you can return null. The nulls are not
	 * written by ItemWriter.
	 */
	private ItemProcessor<ItemDto, Item> importProcessor() {
		return new ItemProcessor<ItemDto, Item>() {

			@Override
			public Item process(final ItemDto itemDto) throws Exception {
				final Item item = Item.builder().description(itemDto.getDescription()).price(itemDto.getPrice())
						.build();
				logger.info(item.toString());
				return item;
			}
		};
	}

	/**
	 * ItemReader is an abstract representation of how data is provided as input to
	 * a Step. When the inputs are exhausted, the ItemReader returns null.
	 */
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

	/**
	 * ItemWriter is the output of a Step. The writer writes one batch or chunk of
	 * items at a time to the target system. ItemWriter has no knowledge of the
	 * input it will receive next, only the item that was passed in its current
	 * invocation.
	 */
	@Bean
	public ItemWriterAdapter<Item> importWriter() {
		final ItemWriterAdapter<Item> writer = new ItemWriterAdapter<Item>();
		writer.setTargetObject(itemRepository);
		writer.setTargetMethod("save");
		return writer;
	}
}