import asyncio
from telegram import Update
import logging
import json
import httpx
from telegram.ext import ApplicationBuilder, CommandHandler, ContextTypes, MessageHandler, Application, filters

# install commands for this file to run
# pip install python-telegram-bot==20.3
# pip install requests

# The environment variables that needs to be set to execute this file
# the telegramToken should be moved to database
telegramToken = "<your_token>"
endpoint = "http://localhost:9090/"

# Logging configuration
logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                    level=logging.INFO)
logger = logging.getLogger(__name__)


async def getUserObj(userId):

    url = endpoint + f"api/users/{userId}"
    # headers = {"Content-Type": "*/*"}

    try:
        # Sending GET request
        response = await httpx.AsyncClient().get(url)
        logger.info(f"Got this response whiel making get call: {response.json()}")

        return response.json()

    except Exception as err:
        logger.error(f"An error occurred: {err}")
    
    return {
        "message" : "error"
    }

async def updateUserDetails(userId, teleUsername, teleId):

    url = endpoint + f"api/users/{userId}"
    headers = {
        "Content-Type": "application/json",
        # "Authorization": "Bearer your_token_here"
    }
    payload = {
        "userId": userId,
        "teleId": teleId,
        "teleUsername": teleUsername
    }

    try:
        # Sending GET request
        logger.info(f"Sending put request with body : {payload}")
        response = await httpx.AsyncClient().put(url,  headers=headers, json=payload)
        logger.info(f"Got this response while making put call: {response.json()}")

        return response.json()

    except Exception as err:
        logger.error(f"An error occurred: {err}")
    
    return {
        "message" : "error"
    }

async def handleMessage(update: Update, context: ContextTypes.DEFAULT_TYPE):
    message_text = update.message.text.strip()
    username = update.message.from_user.username
    chat_id = update.message.chat_id
    logger.info(f"Received message: {message_text}, username : {username}, chat_id: {chat_id}")

    userAPIresponse = await getUserObj(message_text)

    reply_text = 'An error occured please try again later :('

    try:
        if(userAPIresponse['message'] == 'success'):
            if(len(userAPIresponse['results']) > 0):

                userObj = userAPIresponse['results'][0]

                logger.info(f"userObj : {userObj}")

                if(("teleId" in userObj) and (userObj['teleId'] != None)):
                    reply_text = "User already registered with a talegram chat"
                
                else:
                    updateStatus = await updateUserDetails(message_text, username, chat_id)
                    if(updateStatus["message"] == "success"):
                        reply_text = "User details updated successfully"
                    else:
                        reply_text = "An error occured while trying to update the details. Please try again :("
            else:
                reply_text = "No users found with the entered details"

    except Exception as e:
        logger.info(f"An error orrucred while processing the response: {e}")

    try:
        await update.message.reply_text(f"{reply_text}")
        # await context.bot.send_message(chat_id=chat_id, text="testing using chatId")
    except RuntimeError as e:
        logger.info(f"Error sending message {e}")


# Create and run the bot application
def main():
    application = (
        ApplicationBuilder()
        .token(telegramToken)
        .build()
    )

    # Message handler for text messages
    message_handler = MessageHandler(filters.TEXT & ~filters.COMMAND, handleMessage)
    application.add_handler(message_handler)

    # Run the application, adjusting for already running event loops
    try:
        logger.info(f"Polling started")
        asyncio.run(application.run_polling())
    except RuntimeError as e:
        logger.info(f"Unable to start the polling: {e}")

if __name__ == "__main__":
    main()
