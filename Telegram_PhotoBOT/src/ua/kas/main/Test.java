package ua.kas.main;

import java.io.IOException;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.ChosenInlineResult;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;

public class Test {

	public static void main(String[] args) {

		TelegramBot bot = TelegramBotAdapter.build("373911647:AAGsLA42ghRqVOV78BMJJ33uEiXGTx-mPgg");
		bot.execute(request, new Callback() {
			@Override
			public void onResponse(BaseRequest request, BaseResponse response) {

			}

			@Override
			public void onFailure(BaseRequest request, IOException e) {

			}
		});
	}

}

class Update {
	Integer updateId() {
		return null;
	}

	Message message() {
		return null;
	}

	Message editedMessage() {
		return null;
	}

	InlineQuery inlineQuery() {
		return null;
	}

	ChosenInlineResult chosenInlineResult() {
		return null;
	}

	CallbackQuery callbackQuery() {
		return null;
	}
}
