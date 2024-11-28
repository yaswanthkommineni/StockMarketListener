import yfinance as yf
import os
import json
from KafkaProducerClass import KafkaProducerClass
from RateLimiter import RateLimiter

# Function to fetch the latest prices
def fetch_stock_data(symbols):
  result = []
  try:
    # Fetch the latest data
    stocks = yf.download(symbols, period="1d", interval="1m")  # 1-minute interval
    latest_prices = stocks['Close'].iloc[-1]  # Get the latest closing prices
    result = latest_prices
    return result
  except Exception as e:
    print(f"Error fetching data: {e}")
    return -1
  
def make_message_obj(symbols, prices):
  message = {}
  for ticker in symbols:
    message[ticker] = round(prices[ticker],3)
  return message

# List of stock symbols
symbols = ["RELIANCE.NS", "TATASTEEL.NS", "INFY.NS"]
rateLimiter = RateLimiter(os.environ.get('SLEEP_INTERVAL_TIME'))
kafkaProducer = KafkaProducerClass([os.environ.get('KAFKA_BOOTSTRAP_SERVERS')], os.environ.get('KAFKA_TOPIC'))
# Infinite loop to fetch data every second
try:
  while True:
    fetchedPrices = fetch_stock_data(symbols)  # Fetch stock data
    if type(fetchedPrices) == type(-1):
      rateLimiter.increaseSleepingTime()
    else:
      rateLimiter.decreaseSleepingTime()
    streamData = {}
    for ticker in symbols:
      streamData[ticker] = round(fetchedPrices[ticker],3)
    kafkaProducer.send(make_message_obj(symbols,fetchedPrices))
    rateLimiter.wait()  # Wait as per the rate limiting time (generally 1 second)
except KeyboardInterrupt:
  kafkaProducer.close()
  print("Producer interrupted.")
except Exception as e:
  kafkaProducer.close()
  print(f"Stopped fetching stock data: {e}")