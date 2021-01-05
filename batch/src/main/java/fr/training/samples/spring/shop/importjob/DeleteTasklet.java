package fr.training.samples.spring.shop.importjob;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteTasklet implements Tasklet {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteTasklet.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		jdbcTemplate.setDataSource(dataSource);
		final int rowCount = jdbcTemplate
				.update("DELETE FROM ITEM I WHERE I.ID NOT IN (SELECT ITEMS_ID FROM ORDERS_ITEMS)");
		contribution.incrementWriteCount(rowCount);

		LOGGER.info("{} Deleted rows", rowCount);

		return RepeatStatus.FINISHED;
	}

}