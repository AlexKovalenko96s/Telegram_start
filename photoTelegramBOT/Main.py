import telebot
import constants
import os
import random

bot = telebot.TeleBot(constants.token)

upd = bot.get_updates()

if upd == []:
    while upd == []:
        upd = bot.get_updates()

print(upd)
last_upd = upd[-1]

message_from_user = last_upd.message

def log(message):
    from datetime import datetime
    print("\n-----")
    print(datetime.now())
    print("Сообщение от {0} {1}. (id = {2}) \nТекст - {3}".format(message.from_user.first_name,
                                                                   message.from_user.last_name,
                                                                   str(message.from_user.id),
                                                                   message.text))


@bot.message_handler(commands=['start'])
def handle_text(message):
    user_markup = telebot.types.ReplyKeyboardMarkup(True, False)
    user_markup.row("Фото \U0001F609", "Gif \U0001F60F", "Видео \U0001F60B")
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
        file = open(directory + '/' + random_file, 'rb')
        bot.send_chat_action(message.from_user.id, 'upload_document')
        bot.send_document(message.from_user.id, file)
        file.close()
    elif message.text == "Видео \U0001F60B":
        directory = 'C:/Users/KLUBN_000/Desktop/New folder/video'
        all_files_in_directory = os.listdir(directory)
        random_file = random.choice(all_files_in_directory)
        video = open(directory + '/' + random_file, 'rb')
        bot.send_chat_action(message.from_user.id, 'upload_video')
        bot.send_video(message.from_user.id, video)
        video.close()
    else:
        bot.send_message(message.chat.id, "Используй специальные кнопки для выбора")
    log(message)


##@bot.message_handler(content_types=['text'])
#def handle_text(message):
#   if message.text == "а":
#       bot.send_message(message.chat.id, "б")
#   elif message.text == "б":
#       bot.send_message(message.chat.id, "в")
#   else:
#       bot.send_message(message.chat.id, "Ты не умеешь играть(")

bot.polling(none_stop=True, interval=0)
