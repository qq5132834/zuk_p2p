/**
 * 
 */
package com.zuk.media.javacv.watermark;

import javax.swing.JFrame;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.CvSize;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

/**
 * @author:  大聊
 * @Package:  com.zuk.media.javacv
 * @ClassName:  RecordCamera
 * @Description:  推流器
 * @date:  2018年7月22日 下午4:02:38
 * @email: 513283439@qq.com
 */
public class CameraPush {

	
	public static void main(String[] args) throws org.bytedeco.javacv.FrameRecorder.Exception, InterruptedException, Exception {
//		recordCamera("C:\\bbg.mp4",25);
//		recordCamera("rtmp://127.0.0.1:1935/firstapp/record1", 25);
		recordCamera("rtmp://180.76.137.253:1935/live/record1", 25);
	}
	
	/**
	 * 按帧录制本机摄像头视频（边预览边录制，停止预览即停止录制）
	 * 
	 * @author eguid
	 * @param outputFile -录制的文件路径，也可以是rtsp或者rtmp等流媒体服务器发布地址
	 * @param frameRate - 视频帧率
	 * @throws Exception
	 * @throws InterruptedException
	 * @throws org.bytedeco.javacv.FrameRecorder.Exception
	 */
	public static void recordCamera(String outputFile, double frameRate)
			throws Exception, InterruptedException, org.bytedeco.javacv.FrameRecorder.Exception {
		Loader.load(opencv_objdetect.class);
		FrameGrabber grabber = FrameGrabber.createDefault(0);//本机摄像头默认0，这里使用javacv的抓取器，至于使用的是ffmpeg还是opencv，请自行查看源码
		grabber.start();//开启抓取器
 
		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();//转换器
		IplImage grabbedImage = converter.convert(grabber.grab());//抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
		
		//TODO 抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
		
		
		//
		
		int width = grabbedImage.width()/2;
		int height = grabbedImage.height()/2;
	
		FrameRecorder recorder = FrameRecorder.createDefault(outputFile, width, height);
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264，编码
		recorder.setFormat("flv");//封装格式，如果是推送到rtmp就必须是flv封装格式
		recorder.setFrameRate(frameRate);
		
		recorder.start();//开启录制器
		long startTime=0;
		long videoTS=0;
		CanvasFrame frame = new CanvasFrame("camera-push", CanvasFrame.getDefaultGamma() / grabber.getGamma());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		 
		
		Frame rotatedFrame=converter.convert(grabbedImage);//不知道为什么这里不做转换就不能推到rtmp
		
		while (frame.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null) {
			rotatedFrame = converter.convert(grabbedImage);
			frame.showImage(rotatedFrame);
			
			
			//水印文字
//			Mat mat = converter.convertToMat(grabber.grabFrame());
//			Point point = new Point(10, 30);
//			Scalar scalar = new Scalar(0, 255, 255, 0);
//			opencv_imgproc.putText(mat, "pingAn", point, opencv_imgproc.CV_FONT_VECTOR0, 1.2, scalar, 1, 20, false);
//			//水印图片
//			Mat logo = opencv_imgcodecs.imread("C:\\Users\\Administrator\\Desktop\\pa.png");
//			Mat ROI = mat.apply(new Rect(10, 40, logo.cols(), logo.rows()));
//			double alpha = 0.5;// 图像透明权重值,0-1之间
//			opencv_core.addWeighted(ROI, alpha, logo, 1.0 - alpha, 0.0, ROI);
//			frame.showImage(converter.convert(mat));
			
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
			}
			videoTS = 1000 * (System.currentTimeMillis() - startTime);
			recorder.setTimestamp(videoTS);
			recorder.record(rotatedFrame);
			Thread.sleep(40);
		}
		frame.dispose();
		recorder.stop();
		recorder.release();
		grabber.stop();
	
	}



}
