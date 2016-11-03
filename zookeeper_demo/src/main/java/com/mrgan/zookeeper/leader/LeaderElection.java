package com.mrgan.zookeeper.leader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.event.DocumentEvent.EventType;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class LeaderElection {
	private static String _ROOT_PATH = "/ephemeral_sequential";
	private static String _ZOOKEEPER_URL = "192.168.222.110:2181,192.168.222.110:2182,192.168.222.110:2183";
	private LeaderElectionCallback callback;
	private ZooKeeper zk;
	private boolean getLeader = false;

	public void getLeaderShip() throws Exception {
		getLeader = getLeaderShip(new LeaderElectionCallback() {

			public void leaderShip() {
				getLeader = true;
			}
		});
		while (!getLeader) {
			Thread.sleep(100);
		}
	}

	public boolean getLeaderShip(LeaderElectionCallback callback)
			throws Exception {
		this.callback = callback;
		zk = connect();

		String myPath = zk.create(_ROOT_PATH + "/", null, Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);
		myPath = myPath.split("/")[2];
		return judgeLeader(myPath);
	}

	Watcher watcherPrevious = new Watcher() {
		public void process(WatchedEvent event) {
			if (event.getType() == Watcher.Event.EventType.NodeDeleted)
				callback.leaderShip();
			else
				try {
					zk.exists(event.getPath(), watcherPrevious);
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	};

	private int getMinNum(List<String> childrens, String myPath)
			throws Exception {
		int num = 0;
		for (int i = 0; i < childrens.size(); i++) {
			if (childrens.get(i).equals(myPath)) {
				num = i;
			}
		}
		return num;
	}

	private boolean judgeLeader(String myPath) throws Exception {
		List<String> childrens = zk.getChildren(_ROOT_PATH, false);

		Collections.sort(childrens);
		int num = getMinNum(childrens, myPath);
		if (num == 0)
			return true;
		else {
			Stat stat = zk.exists(_ROOT_PATH + "/" + childrens.get(num - 1),
					watcherPrevious);
			if (stat == null)
				return judgeLeader(myPath);
			return false;
		}
	}

	private ZooKeeper connect() throws IOException, KeeperException,
			InterruptedException {
		ZooKeeper zk = new ZooKeeper(_ZOOKEEPER_URL, 6000, new Watcher() {
			public void process(WatchedEvent event) {
			}
		});

		// create root
		if (zk.exists(_ROOT_PATH, false) == null)
			zk.create(_ROOT_PATH, null, Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		return zk;
	}
}
