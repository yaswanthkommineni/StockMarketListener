import time

class RateLimiter:
  def __init__(self, initialSleepingTime):
    self.sleepingTime = initialSleepingTime
  def increaseSleepingTime(self):
    self.sleepingTime *= 2
    print(f'sleep time increased to {self.sleepingTime} seconds')
  def decreaseSleepingTime(self):
    self.sleepingTime = max(self.sleepingTime//2, 1)
  def wait(self):
    time.sleep(self.sleepingTime)