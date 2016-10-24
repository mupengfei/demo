package com.mrgan.kafka.partitioner;

import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class RandomPartitioner implements Partitioner {
	private static Random RANDOM_NUM = new Random();

	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub

	}

	public int partition(String topic, Object key, byte[] keyBytes,
			Object value, byte[] valueBytes, Cluster cluster) {
		System.out.println(this);
		int count = cluster.partitionCountForTopic(topic);
		return RANDOM_NUM.nextInt(count);
	}

	public void close() {
		// TODO Auto-generated method stub

	}
}
