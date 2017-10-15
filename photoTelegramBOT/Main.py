import telebot
import constants
import os
import random

bot = telebot.TeleBot(constants.token)

upd = bot.get_updates()
last_upd = upd[-1]

message_from_user = last_upd.message

@bot.message_handler(commands=['start'])
def handle_text(message):
    user_markup = telebot.types.ReplyKeyboardMarkup(True, False)
    user_markup.row("Фото \U0001F609", "Gif \U0001F60F")
    bot.send_message(message.chat.id, "Привет, выбирай что тебя интересует)", reply_markup=user_markup)

@bot.message_handler(content_types=['text'])
def handle_text(message):
    if message.text == "Фото \U0001F609":
        directory = 'C:/Users/KLUBN_000/Desktop/New folder/photo'
        all_files_in_directory = os.listdir(directory)
        random_file = random.choice(all_files_in_directory)
        img = open(directory + '/' + random_file, 'rb')
        bot.send_chat_action(message.from_user.id, 'upload_photo')
        bot.send_photo(message.from_user.id, img)
        img.close()
    elif message.text == "Gif \U0001F60F":
        directory = 'C:/Users/KLUBN_000/Desktop/New folder/gif'
        all_files_in_directory = os.listdir(directory)
        random_file = random.choice(all_files_in_directory)
        video = open(directory + '/' + random_file, 'rb')
        bot.send_chat_action(message.from_user.id, 'upload_video')
        bot.send_video(message.from_user.id, video)
        video.close()

##@bot.message_handler(content_types=['text'])
#def handle_text(message):
#   if message.text == "а":
#       bot.send_message(message.chat.id, "б")
#   elif message.text == "б":
#       bot.send_message(message.chat.id, "в")
#    else:
#       bot.send_message(message.chat.id, "Ты не умеешь играть(")

bot.polling(none_stop=True, interval=0)
