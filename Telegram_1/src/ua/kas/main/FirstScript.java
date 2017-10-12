package ua.kas.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

import org.apache.commons.lang.StringEscapeUtils;

public class FirstScript {

	private String token;
	private String lastId = "";

	@SuppressWarnings("serial")
	private ArrayList<String> newFilms = new ArrayList<String>() {
		{
			add("Малыш на драйве (2017)");
			add("Ветреная река (2017)");
			add("Человек-паук: Возвращение домой (2017)");
			add("Бегущий по лезвию 2049 (2017)");
			add("Оно (2017)");
			add("Дюнкерк (2017)");
		}
	};

	@SuppressWarnings("serial")
	private ArrayList<String> comedyFilms = new ArrayList<String>() {
		{
			add("…А в душе я танцую (2004)");
			add("Мэверик (1994)");
			add("Прочь (2017)");
			add("Откройте, полиция! (1984)");
			add("Карты, деньги, два ствола (1998)");
			add("Большой куш (2000)");
		}
	};

	@SuppressWarnings("serial")
	private ArrayList<String> thrillerFilms = new ArrayList<String>() {
		{
			add("Леон (1994)");
			add("Ветреная река (2017)");
			add("Престиж (2006)");
			add("Отступники (2006)");
			add("Семь (1995)");
			add("Игра (1997)");
		}
	};

	@SuppressWarnings("serial")
	private ArrayList<String> lightFilms = new ArrayList<String>() {
		{
			add("Простые сложности (2009)");
			add("Отпуск по обмену (2006)");
			add("Реальная любовь (2003)");
			add("Всегда говори «да» (2008)");
			add("Однажды в Вегасе (2008)");
			add("Секс по дружбе (2011)");
		}
	};

	private Random random = new Random();

	public FirstScript(String token) {
		this.token = token;

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(250);
						getUpdates();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	public void getUpdates() throws IOException {
		String url = "https://api.telegram.org/bot" + token + "/getupdates";
		String line = "";
		String out = "";
		URL url2 = new URL(url);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));

		while ((line = reader.readLine()) != null) {
			out = line;
			System.out.println(out);
		}

		action(out);
		reader.close();
	}

	public void action(String line) throws IOException {
		// line = URLEncoder.encode(line, "UTF-8");
		line = StringEscapeUtils.unescapeJava(line);

		String messageId = line.substring(line.indexOf("\"message_id\":") + 13, line.indexOf(",\"from\":{\"id\":"));
		String id = line.substring(line.indexOf("\"from\":{\"id\":") + 13, line.indexOf(",\"is_bot\":"));
		// String firstName;
		// String lastName;
		// String languageCode;
		// String type;
		// String text = line.substring(line.indexOf(",\"text\":\"") + 9,
		// line.indexOf("\"}}]}"));
		String text = line.substring(line.indexOf(",\"text\":\"") + 9);

		System.err.println(messageId + "    " + text);

		if (!lastId.equals(messageId)) {
			lastId = messageId;

			String name = "";

			if (text.contains("/new")) {
				name = newFilms.get(random.nextInt(newFilms.size()));
				sentMessage(name, id);
			} else if (text.contains("/comedy")) {
				name = comedyFilms.get(random.nextInt(comedyFilms.size()));
				sentMessage(name, id);
			} else if (text.contains("/thriller")) {
				name = thrillerFilms.get(random.nextInt(thrillerFilms.size()));
				sentMessage(name, id);
			} else if (text.contains("/light")) {
				name = lightFilms.get(random.nextInt(lightFilms.size()));
				sentMessage(name, id);
			} else if (text.contains("/random")) {
				LinkedHashSet<String> allFilms = new LinkedHashSet<>();

				allFilms.addAll(comedyFilms);
				allFilms.addAll(lightFilms);
				allFilms.addAll(newFilms);
				allFilms.addAll(thrillerFilms);

				ArrayList<String> all = new ArrayList<>();
				all.addAll(allFilms);
				name = all.get(random.nextInt(all.size()));
				sentMessage(name, id);
			} else if (text.contains("/start")) {
				sentMessage("Привет, выбери одну из команд!", id);
			} else {
				sentMessage("Пожалуйста, выбери одну из команд!", id);
			}
		}
	}

	public void sentMessage(String name, String messageId) throws IOException {
		String url = "https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + messageId + "&text=" + name
				+ "";
		URL url2 = new URL(url);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
		reader.close();
	}
}
