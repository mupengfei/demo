package com.mrgan.zookeeper;

import com.mrgan.zookeeper.leader.LeaderElection;
import com.mrgan.zookeeper.leader.LeaderElectionCallback;

public class AsyncDemo {
	private static String _ROOT_PATH = "/ephemeral_sequential";

	public static void main(String[] args) throws Exception {
		LeaderElection le = new LeaderElection();
		boolean flag = le.getLeaderShip(new LeaderElectionCallback() {

			public void leaderShip() {
				System.out.println("i am get leader");
			}
		});
		if (flag) {
			System.out.println("i am get leader");
		} else {
			System.out.println("i am not the leader");
		}
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
