package ua.kas.main;

public class Main {

	private static final String TOKEN = "373911647:AAGsLA42ghRqVOV78BMJJ33uEiXGTx-mPgg";

	private static Script script;

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				script = new Script(TOKEN);
			}
		});
		thread.start();
	}

}
