from kafka import KafkaProducer
import json

class KafkaProducerClass:
  def __init__(self, bootstrap_servers, topic):
    self.server = KafkaProducer(bootstrap_servers=bootstrap_servers,value_serializer=lambda x: json.dumps(x).encode('utf-8'))
    self.topic = topic
  def send(self, data):
    self.server.send(self.topic, data)
    self.server.flush()
  def close(self):
    self.server.close()