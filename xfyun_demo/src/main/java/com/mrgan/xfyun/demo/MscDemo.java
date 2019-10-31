package com.mrgan.xfyun.demo;

import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
import com.iflytek.cloud.speech.SynthesizerListener;

public class MscDemo {
	public static void main(String[] args) {
		SpeechUtility.createUtility(SpeechConstant.APPID + "=5bd12bd7 ");

		// 1.创建SpeechSynthesizer对象
		SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
		// 2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
		mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");// 设置发音人
		mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
		mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围0~100
		// 设置合成音频保存位置（可自定义保存位置），保存在“./tts_test.pcm”
		// 如果不需要保存合成音频，注释该行代码
		mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./tts_test.pcm");
		// 3.开始合成
		mTts.startSpeaking("迪神的大锤用的真是棒  呱呱叫", mSynListener);

		mTts.synthesizeToUri("迪神的大锤用的真是棒  呱呱叫", "./tts_test.pcm",synthesizeToUriListener);
	}

	//合成监听器
	static SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
	    //progress为合成进度0~100 
	    public void onBufferProgress(int progress) {}
	    //会话合成完成回调接口
	    //uri为合成保存地址，error为错误信息，为null时表示合成会话成功
	    public void onSynthesizeCompleted(String uri, SpeechError error) {}
		public void onEvent(int arg0, int arg1, int arg2, int arg3, Object arg4, Object arg5) {
			// TODO Auto-generated method stub
			
		}
	};
	// 合成监听器
	private static SynthesizerListener mSynListener = new SynthesizerListener() {
		// 会话结束回调接口，没有错误时，error为null
		public void onCompleted(SpeechError error) {
		}

		// 缓冲进度回调
		// percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
		public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
		}

		// 开始播放
		public void onSpeakBegin() {
		}

		// 暂停播放
		public void onSpeakPaused() {
		}

		// 播放进度回调
		// percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
		public void onSpeakProgress(int percent, int beginPos, int endPos) {
		}

		// 恢复播放回调接口
		public void onSpeakResumed() {
		}

		public void onEvent(int arg0, int arg1, int arg2, int arg3, Object arg4, Object arg5) {
			// TODO Auto-generated method stub

		}
	};
}
