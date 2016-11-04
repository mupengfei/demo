package com.mrgan.kafka.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerGroupExample {
	private final Consumer consumer;
	private ExecutorService executor;

	public ConsumerGroupExample() {
		// consumer = kafka.consumer.Consumer
		// .createJavaConsumerConnector(createConsumerConfig(a_zookeeper,
		// a_groupId));
		Map<String, Object> props = new HashMap<String, Object>();
		// 10.0.60.146:9092,10.0.60.147:9092,10.0.60.148:9092
		// 10.0.60.146:2181,10.0.60.147:2181,10.0.60.148:2181
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.222.110:9092,192.168.222.110:9093,192.168.222.110:9094");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "1");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class.getName());
		consumer = new KafkaConsumer(props);
		// this.topic = a_topic;
	}

	public void shutdown() {
		// if (consumer != null)
		// consumer.shutdown();
		if (executor != null)
			executor.shutdown();
		try {
			if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
				System.out
						.println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
			}
		} catch (InterruptedException e) {
			System.out
					.println("Interrupted during shutdown, exiting uncleanly");
		}
	}

	public void run(int a_numThreads) {
		// Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		// Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
		// .createMessageStreams(topicCountMap);
		// List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

		// now launch all the threads
		//
		// executor = Executors.newFixedThreadPool(a_numThreads);
		List<String> topics = new ArrayList<String>();
		topics.add("kps");
		consumer.subscribe(topics);
		while (true) {
			ConsumerRecords<String, String> record = consumer.poll(60000);
			for (ConsumerRecord<String, String> consumerRecord : record) {
				System.out.print("key : " + consumerRecord.key());
				System.out.print(" partition : " + consumerRecord.partition());
				System.out.print(" topic : " + consumerRecord.topic());
				System.out.println(" value : " + consumerRecord.value());
			}
		}
		// now create an object to consume the messages
		//
		// int threadNumber = 0;
		// for (final KafkaStream stream : streams) {
		// executor.submit(new ConsumerTest(stream, threadNumber));
		// threadNumber++;
		// }
	}

	// private static ConsumerConfig createConsumerConfig(String a_zookeeper,
	// String a_groupId) {
	// Properties props = new Properties();
	// props.put("zookeeper.connect", a_zookeeper);
	// props.put("group.id", a_groupId);
	// props.put("zookeeper.session.timeout.ms", "400");
	// props.put("zookeeper.sync.time.ms", "200");
	// props.put("auto.commit.interval.ms", "1000");
	//
	// return new ConsumerConfig(props);
	// }

	public static void main(String[] args) {
		// String zooKeeper = args[0];
		// String groupId = args[1];
		// String topic = args[2];
		// int threads = Integer.parseInt(args[3]);

		ConsumerGroupExample example = new ConsumerGroupExample();
		example.run(1);

		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException ie) {
		//
		// }
		// example.shutdown();
	}
}