package com.mrgan.kafka.producer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemo {
	public static void main(String[] args) {
		long events = 5L;
		Random rnd = new Random();

		Map<String, Object> props = new HashMap<String, Object>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"10.0.60.146:9092,10.0.60.147:9092,10.0.60.148:9092");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
				"com.mrgan.kafka.partitioner.RandomPartitioner");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class.getName());

		// ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new KafkaProducer<String, String>(
				props);

		for (long nEvents = 0; nEvents < events; nEvents++) {
			long runtime = new Date().getTime();
			String ip = "key" + runtime;
			String msg = runtime + ",www.example.com," + ip;
			ProducerRecord<String, String> data = new ProducerRecord<String, String>(
					"class31", ip, msg);
			Future<RecordMetadata> future = producer.send(data);
			try {
				System.out.println(future.get().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		producer.close();
	}
}
