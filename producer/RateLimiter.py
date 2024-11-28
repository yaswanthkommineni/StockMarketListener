import time
import os

class RateLimiter:
  def __init__(self, initialSleepingTime):
    self.sleepingTime = initialSleepingTime
  def increaseSleepingTime(self):
    self.sleepingTime *= 2
    print(f'sleep time increased to {self.sleepingTime} seconds')
  def decreaseSleepingTime(self):
    self.sleepingTime = max(self.sleepingTime//2, int(os.environ.get('SLEEP_INTERVAL_TIME')))
  def wait(self):
    time.sleep(self.sleepingTime)