package com.mrgan.zookeeper;

import com.mrgan.zookeeper.leader.LeaderElection;
import com.mrgan.zookeeper.leader.LeaderElectionCallback;

public class SyncDemo {
	private static String _ROOT_PATH = "/ephemeral_sequential";

	public static void main(String[] args) throws Exception {
		LeaderElection le = new LeaderElection();

		System.out.println("i want the leader");

		le.getLeaderShip();

		System.out.println("i am get leader");
		while (true) {
			try {
				Thread.sleep(50000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
