package com.mrgan.hadoop.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class Test {
	public static void main(String[] args) throws Exception {
		String uri = "hdfs://testserver1:9000/";
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), config);

		// 列出hdfs上/user/fkong/目录下的所有文件和目录
		FileStatus[] statuses = fs.listStatus(new Path("/"));
		for (FileStatus status : statuses) {
			System.out.println(status.getPath());
		}
		fs.deleteOnExit(new Path("/test2.mp4"));

		// 在hdfs的/user/fkong目录下创建一个文件，并写入一行文本
		// FSDataOutputStream os = fs.create(new Path("/user/fkong/test.log"));
		// os.write("Hello World!".getBytes());
		// os.flush();
		// os.close();

		// // 显示在hdfs的/user/fkong下指定文件的内容
		File file = new File("E:/test.mp4");
		if (!file.exists()) {
			file.createNewFile();
		}

		System.out.println(new Date());
		FSDataOutputStream os = fs.create(new Path("/test2.mp4"));
		InputStream in = new FileInputStream(file);
		byte b[] = new byte[1024];
		int len = -1;
		while ((len = in.read(b)) != -1) {
			os.write(b, 0, len);
		}
		os.flush();
		os.close();
		System.out.println(new Date());

		OutputStream out = new FileOutputStream(new File("E:/test2.mp4"));

		InputStream is = fs.open(new Path("/test2.mp4"));
		System.out.println(new Date());
		IOUtils.copyBytes(is, out, 1024000, true);
		System.out.println(new Date());
	}
}