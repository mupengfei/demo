package com.mrgan.ffmpeg;

import javax.swing.JFrame;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class FFmpegPushDemo {
	public static String videoFile = "E://videotest/Wildlife.wmv";

	public static void main(String[] args) {
		String inputFile = "rtsp://admin:admin@192.168.2.236:37779/cam/realmonitor?channel=1&subtype=0";

		String outputFile = "rtmp://testserver2/mylive/111";

		try {
			recordPush(videoFile, outputFile, 30);
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// long startTime = 0;
		// FFmpegFrameGrabber grabber = null;
		// try {
		// grabber = FFmpegFrameGrabber.createDefault(videoFile);
		// grabber.start();
		//
		// OpenCVFrameConverter.ToIplImage converter = new
		// OpenCVFrameConverter.ToIplImage();
		// Frame grabframe = grabber.grab();
		// IplImage grabbedImage = null;
		// if (grabframe != null) {
		// System.out.println("取到第一帧");
		// grabbedImage = converter.convert(grabframe);
		// } else {
		// System.out.println("没有取到第一帧");
		// }
		// // 如果想要保存图片,可以使用 opencv_imgcodecs.cvSaveImage("hello.jpg",
		// // grabbedImage);来保存图片
		// opencv_imgcodecs.cvSaveImage("E://hello.jpg", grabbedImage);
		//
		// FFmpegFrameRecorder recorder;
		// recorder = new FFmpegFrameRecorder("rtmp://testserver2/mylive/111",
		// 1280, 720, 1);
		// recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); //
		// avcodec.AV_CODEC_ID_H264
		// recorder.setFormat("flv");
		// recorder.setFrameRate(20);
		// // recorder.setGopSize(1);
		// System.out.println("准备开始推流...");
		// try {
		// recorder.start();
		// } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
		// try {
		// System.out.println("录制器启动失败，正在重新启动...");
		// if (recorder != null) {
		// System.out.println("尝试关闭录制器");
		// recorder.stop();
		// System.out.println("尝试重新开启录制器");
		// recorder.start();
		// }
		//
		// } catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
		// throw e;
		// }
		// }
		// System.out.println("开始推流");
		//
		// // CanvasFrame frame = new CanvasFrame("camera",
		// // CanvasFrame.getDefaultGamma() / grabber.getGamma());
		// // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// // frame.setAlwaysOnTop(true);
		// // while ((grabframe = grabber.grab()) != null) {
		// // // System.out.println("推流...");
		// // // frame.showImage(grabframe);
		// // grabbedImage = converter.convert(grabframe);
		// // Frame rotatedFrame = converter.convert(grabbedImage);
		// //
		// // if (startTime == 0) {
		// // startTime = System.currentTimeMillis();
		// // }
		// // recorder.setTimestamp(1000 * (System.currentTimeMillis() -
		// // startTime));// 时间戳
		// // if (rotatedFrame != null) {
		// // recorder.record(rotatedFrame);
		// // }
		// //
		// // Thread.sleep(1000 / 40);
		// // }
		// Frame frame;
		// while ((frame = grabber.grabFrame(true, true, true, false)) != null)
		// {
		// System.out.println("推流...");
		// recorder.record(frame);
		// }
		// // frame.dispose();
		// recorder.stop();
		// recorder.release();
		// recorder.close();
		// grabber.stop();
		// System.exit(2);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 转流器
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @throws Exception
	 * @throws org.bytedeco.javacv.FrameRecorder.Exception
	 * @throws InterruptedException
	 */
	public static void recordPush(String inputFile, String outputFile, int v_rs)
			throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, InterruptedException {
		Loader.load(opencv_objdetect.class);
		long startTime = 0;
		FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(inputFile);
		try {
			grabber.start();
		} catch (Exception e) {
			try {
				grabber.restart();
			} catch (Exception e1) {
				throw e;
			}
		}

		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
		// Frame grabframe = grabber.grab();
		// IplImage grabbedImage = null;
		// if (grabframe != null) {
		// System.out.println("取到第一帧");
		// grabbedImage = converter.convert(grabframe);
		// } else {
		// System.out.println("没有取到第一帧");
		// }
		// 如果想要保存图片,可以使用 opencv_imgcodecs.cvSaveImage("hello.jpg",
		// grabbedImage);来保存图片
		FFmpegFrameRecorder recorder;
		recorder = new FFmpegFrameRecorder(outputFile, 1280, 720, 2);
		recorder.setInterleaved(true);
		recorder.setVideoOption("tune", "zerolatency");
		recorder.setVideoOption("preset", "ultrafast");
		recorder.setVideoOption("crf", "28");
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264
		recorder.setFormat("flv");
		recorder.setFrameRate(v_rs);
		recorder.setGopSize(60);
		recorder.setVideoBitrate(900 * 1024);
		System.out.println("准备开始推流...");
		try {
			recorder.start();
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			try {
				System.out.println("录制器启动失败，正在重新启动...");
				if (recorder != null) {
					System.out.println("尝试关闭录制器");
					recorder.stop();
					System.out.println("尝试重新开启录制器");
					recorder.start();
				}

			} catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
				throw e;
			}
		}
		System.out.println("开始推流");
		// CanvasFrame frame = new CanvasFrame("camera",
		// CanvasFrame.getDefaultGamma() / grabber.getGamma());
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setAlwaysOnTop(true);
		Frame grabframe = null;
		IplImage grabbedImage = null;
		while ((grabframe = grabber.grabFrame(true, true, true, false)) != null) {
			System.out.println("推流..." + grabber.getFrameNumber());
			// frame.showImage(grabframe);
			// grabbedImage = converter.convert(grabframe);
			// Frame rotatedFrame = converter.convert(grabbedImage);
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
			}
			recorder.setTimestamp(1000 * (System.currentTimeMillis() - startTime));// 时间戳
			if (grabframe != null) {
				recorder.record(grabframe);
			}

			Thread.sleep(1000 / v_rs);
		}
		// frame.dispose();
		recorder.close();
		recorder.stop();
		recorder.release();
		grabber.stop();
		System.exit(2);
	}
}
