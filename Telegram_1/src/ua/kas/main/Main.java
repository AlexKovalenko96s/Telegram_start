package ua.kas.main;

import java.io.IOException;

public class Main {

	private static final String TOKEN = "477876881:AAGf5f8G6AEvZgIs2wutm8wFJzDKErolzVA";

	@SuppressWarnings("unused")
	private static FirstScript firstScript;

	public static void main(String[] args) throws IOException {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				firstScript = new FirstScript(TOKEN);
			}
		});
		thread.start();
	}
}