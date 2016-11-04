package com.mrgan.zookeeper;

import com.mrgan.zookeeper.leader.LeaderElection;
import com.mrgan.zookeeper.leader.LeaderElectionCallback;

public class SyncDemo {
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
