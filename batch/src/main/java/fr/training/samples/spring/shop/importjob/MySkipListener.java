package fr.training.samples.spring.shop.importjob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;

public class MySkipListener<T, S> implements SkipListener<T, S> {

	private final FileWriter fileWriter;

	public MySkipListener(final File file) throws IOException {
		fileWriter = new FileWriter(file);
	}

	@Override
	public void onSkipInRead(final Throwable throwable) {
		if (throwable instanceof FlatFileParseException) {
			final FlatFileParseException flatFileParseException = (FlatFileParseException) throwable;
			try {
				fileWriter.write(flatFileParseException.getInput());
			} catch (final IOException e) {
				System.err.println("Unable to write skipped line to error file");
			}
		}
	}

	@Override
	public void onSkipInWrite(final S item, final Throwable t) {
		System.out.println("Item " + item + " was skipped due to: " + t.getMessage());
	}

	@Override
	public void onSkipInProcess(final T item, final Throwable t) {
		System.out.println("Item " + item + " was skipped due to: " + t.getMessage());
	}
}