/**
 * 
 */
package com.zuk.media.javacv.watermark;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;

/**
 * @author:  大聊
 * @Package:  com.zuk.media.javacv
 * @ClassName:  FrameRecord
 * @Description:  收流器
 * @date:  2018年7月22日 下午4:31:01
 * @email: 513283439@qq.com
 */
public class CameraPull {
	
	public static void main(String[] args)
			throws Exception {
 
//		 String inputFile = "C:\\bbg.mp4";
//		 String inputFile = "rtmp://127.0.0.1:1935/firstapp/record1";
//		 String inputFile = "rtmp://127.0.0.1:1935/firstapp/myheart.flv";
		 String inputFile = "rtmp://180.76.137.253:1935/live/record1";
		 // Decodes-encodes
		 String outputFile = "c:\\recorde1.mp4";
		 frameRecord(inputFile, outputFile,1);
}

	
	private static void recordByFrame(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder, Boolean status)
			throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
		try {//建议在线程中使用该方法
			System.out.println("start.");
			grabber.start();
			recorder.start();
			Frame frame = null;
			CanvasFrame cframe = new CanvasFrame("CameraPull.");
			while (status&& (frame = grabber.grabFrame()) != null) {
				recorder.record(frame);
				cframe.showImage(frame);
				System.out.println("huangliao.");
			}
			recorder.stop();
			grabber.stop();
			System.out.println("over");
		} finally {
			if (grabber != null) {
				grabber.stop();
			}
		}
	}

	
	/**
	 * 按帧录制视频
	 * 
	 * @param inputFile-该地址可以是网络直播/录播地址，也可以是远程/本地文件路径
	 * @param outputFile
	 *            -该地址只能是文件地址，如果使用该方法推送流媒体服务器会报错，原因是没有设置编码格式
	 * @throws FrameGrabber.Exception
	 * @throws FrameRecorder.Exception
	 * @throws org.bytedeco.javacv.FrameRecorder.Exception
	 */
	public static void frameRecord(String inputFile, String outputFile, int audioChannel)
			throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
		
	        boolean isStart=true;//该变量建议设置为全局控制变量，用于控制录制结束
		// 获取视频源
		FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
		// 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
		FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, 1280, 720, audioChannel);
		// 开始取视频源
		recordByFrame(grabber, recorder, isStart);
	}


}
