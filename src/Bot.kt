package me.onebone.tgbot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.exceptions.TelegramApiException

class Bot(
		private val token: String
): TelegramLongPollingBot() {
	private val username: String

	init {
		username = getMe().userName
	}

	override fun onUpdateReceived(update: Update) {
		if(update.hasMessage() && update.getMessage().hasText()){
			val msg = SendMessage().apply {
				chatId = update.message.chatId.toString()
				text = "Hello World"
			}

			try{
				execute(msg)
			}catch(e: TelegramApiException){}
		}
	}

	override fun getBotUsername(): String = username

	override fun getBotToken(): String = token
}
