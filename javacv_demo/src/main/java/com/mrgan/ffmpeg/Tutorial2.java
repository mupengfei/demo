package com.mrgan.ffmpeg;

import static org.bytedeco.javacpp.avcodec.avcodec_copy_context;
import static org.bytedeco.javacpp.avformat.AVIO_FLAG_WRITE;
import static org.bytedeco.javacpp.avformat.av_dump_format;
import static org.bytedeco.javacpp.avformat.av_read_frame;
import static org.bytedeco.javacpp.avformat.av_register_all;
import static org.bytedeco.javacpp.avformat.avformat_alloc_output_context2;
import static org.bytedeco.javacpp.avformat.avformat_find_stream_info;
import static org.bytedeco.javacpp.avformat.avformat_network_init;
import static org.bytedeco.javacpp.avformat.avformat_new_stream;
import static org.bytedeco.javacpp.avformat.avformat_open_input;
import static org.bytedeco.javacpp.avformat.avformat_write_header;
import static org.bytedeco.javacpp.avformat.avio_open;
import static org.bytedeco.javacpp.avutil.AVMEDIA_TYPE_VIDEO;
import static org.bytedeco.javacpp.avutil.AV_NOPTS_VALUE;
import static org.bytedeco.javacpp.avutil.AV_TIME_BASE;
import static org.bytedeco.javacpp.avutil.av_q2d;
import static org.bytedeco.javacpp.avutil.av_rescale_q;

import java.awt.Point;
import java.util.Date;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.avcodec.AVPacket;
import org.bytedeco.javacpp.avformat.AVFormatContext;
import org.bytedeco.javacpp.avformat.AVOutputFormat;
import org.bytedeco.javacpp.avformat.AVStream;
import org.bytedeco.javacpp.avutil.AVDictionary;
import org.bytedeco.javacpp.avutil.AVRational;

public class Tutorial2 {
	public static void main(String[] args) {
		// AVFormatContext ifmt_ctx_tmp = null;
		// AVOutputFormat ofmt = null;
		// //输入对应一个AVFormatContext，输出对应一个AVFormatContext
		// //（Input AVFormatContext and Output AVFormatContext）
		// AVFormatContext ifmt_ctx = null, ofmt_ctx = null;
		// AVPacket pkt;
		// String inputUrl = "rtmp://testserver2/mylive/1111";
		// String out_filename = "rtmp://testserver2/mylive/8888";//out_filename
		// = "rtp://233.233.233.233:6666";//输出 URL（Output URL）[UDP]
		// int ret, i;
		// int videoindex = -1;
		// int frame_index = 0;
		// long start_time = 0;
		//
		// av_register_all();
		// //Network
		// avformat_network_init();
		// //输入（Input）
		// if ((ret = avformat_open_input(ifmt_ctx, inputUrl, null,
		// (AVDictionary)null)) < 0) {
		// }
		// if ((ret = avformat_find_stream_info(ifmt_ctx, (AVDictionary)null)) <
		// 0) {
		// }
		//
		// for (i = 0; i<ifmt_ctx.nb_streams(); i++)
		// if (ifmt_ctx.streams(i).codec().codec_type() == AVMEDIA_TYPE_VIDEO) {
		// videoindex = i;
		// break;
		// }
		//
		// av_dump_format(ifmt_ctx, 0, inputUrl, 0);
		//
		// //输出（Output）
		//
		// avformat_alloc_output_context2(ofmt_ctx, null, "flv", out_filename);
		// //RTMP
		// //avformat_alloc_output_context2(&ofmt_ctx, NULL, "mpegts",
		// out_filename);//UDP
		//
		// ofmt = ofmt_ctx.oformat();
		// for (i = 0; i < ifmt_ctx.nb_streams(); i++) {
		// //根据输入流创建输出流（Create output AVStream according to input AVStream）
		// AVStream in_stream = ifmt_ctx.streams(i);
		// AVStream out_stream = avformat_new_stream(ofmt_ctx,
		// in_stream.codec().codec());
		// //复制AVCodecContext的设置（Copy the settings of AVCodecContext）
		// ret = avcodec_copy_context(out_stream.codec(), in_stream.codec());
		// out_stream.codec().codec_tag(0);
		//// if (ofmt_ctx.oformat().flags() & AVFMT_GLOBALHEADER)
		//// out_stream.codec().flags() |= CODEC_FLAG_GLOBAL_HEADER;
		// }
		// //Dump Format------------------
		// av_dump_format(ofmt_ctx, 0, out_filename, 1);
		// //打开输出URL（Open output URL）
		//// if (!(ofmt.flags() & AVFMT_NOFILE)) {
		// ret = avio_open(ofmt_ctx.pb(), out_filename, AVIO_FLAG_WRITE);
		//// if (ret < 0) {
		//// printf("Could not open output URL '%s'", out_filename);
		//// goto end;
		//// }
		//// }
		// //写文件头（Write file header）
		// ret = avformat_write_header(ofmt_ctx, (AVDictionary)null);
		// start_time = new Date().getTime();
		// while (true) {
		// AVStream in_stream, out_stream;
		// //获取一个AVPacket（Get an AVPacket）
		// ret = av_read_frame(ifmt_ctx, pkt);
		// if (ret < 0)
		// break;
		// //FIX：No PTS (Example: Raw H.264)
		// //Simple Write PTS
		// if (pkt.pts() == AV_NOPTS_VALUE) {
		// //Write PTS
		// AVRational time_base1 = ifmt_ctx.streams(videoindex).time_base();
		// //Duration between 2 frames (us) k
		// double calc_duration = (double)AV_TIME_BASE /
		// av_q2d(ifmt_ctx.streams(videoindex).r_frame_rate());
		// //Parameters
		// pkt.pts((long)(frame_index*calc_duration) /
		// (long)(av_q2d(time_base1)*AV_TIME_BASE));
		// pkt.dts(pkt.pts());
		// pkt.duration((long)calc_duration /
		// (long)(av_q2d(time_base1)*AV_TIME_BASE));
		// }
		// //Important:Delay
		// if (pkt.stream_index() == videoindex) {
		// AVRational time_base = ifmt_ctx.streams(videoindex).time_base();
		// AVRational time_base_q = new AVRational(new Pointer(AV_TIME_BASE));
		// int64_t pts_time = av_rescale_q(pkt.dts, time_base, time_base_q);
		// int64_t now_time = av_gettime() - start_time;
		// if (pts_time > now_time)
		// av_usleep(pts_time - now_time);
		//
		// }
		//
		// in_stream = ifmt_ctx->streams[pkt.stream_index];
		// out_stream = ofmt_ctx->streams[pkt.stream_index];
		// /* copy packet */
		// //转换PTS/DTS（Convert PTS/DTS）
		// pkt.pts = av_rescale_q_rnd(pkt.pts, in_stream->time_base,
		// out_stream->time_base, (AVRounding)(AV_ROUND_NEAR_INF |
		// AV_ROUND_PASS_MINMAX));
		// pkt.dts = av_rescale_q_rnd(pkt.dts, in_stream->time_base,
		// out_stream->time_base, (AVRounding)(AV_ROUND_NEAR_INF |
		// AV_ROUND_PASS_MINMAX));
		// pkt.duration = av_rescale_q(pkt.duration, in_stream->time_base,
		// out_stream->time_base);
		// pkt.pos = -1;
		// //Print to Screen
		// if (pkt.stream_index == videoindex) {
		// printf("Send %8d video %8d frames to output URL\n", frame_index,
		// flag);
		// frame_index++;
		// }
		// //ret = av_write_frame(ofmt_ctx, &pkt);
		// ret = av_interleaved_write_frame(ofmt_ctx, &pkt);
		//
		// if (ret < 0) {
		// printf("Error muxing packet\n");
		// break;
		// }
		//
		// av_free_packet(&pkt);
		//
		// }
		// //写文件尾（Write file trailer）
		// av_write_trailer(ofmt_ctx);
		// end:
		// avformat_close_input(&ifmt_ctx);
		// /* close output */
		// if (ofmt_ctx && !(ofmt->flags & AVFMT_NOFILE))
		// avio_close(ofmt_ctx->pb);
		// avformat_free_context(ofmt_ctx);
		// if (ret < 0 && ret != AVERROR_EOF) {
		// printf("Error occurred.\n");
		// return -1;
		// }
		// return 0;
	}
}