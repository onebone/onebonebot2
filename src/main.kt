package me.onebone.tgbot

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.exceptions.TelegramApiException
import java.io.File
import java.io.FileOutputStream
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Json

fun main(args: Array<String>) {
	if(!File("config.json").exists()) {
		Object::class.java.getResourceAsStream("/config.json").use {input ->
			FileOutputStream(File("config.json")).use {
				input.copyTo(it)
			}
		}
	}

	val config = Klaxon().parse<Config>(File("config.json"))!!


	ApiContextInitializer.init()

	val api = TelegramBotsApi()

	try{
		api.registerBot(Bot(config.token))
	}catch(e: TelegramApiException){
		e.printStackTrace()
	}
}

data class Config (
	@Json(name="token")
	val token: String
)
