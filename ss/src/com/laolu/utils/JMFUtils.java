package com.laolu.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSourceException;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.util.BufferToImage;

import org.springframework.stereotype.Repository;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 这个对象可以用作截屏的工具
 * 使用方法见
 * jmfcontroller
 * @author laolu
 *
 */
@Repository("jmfUtils")
public class JMFUtils {

	private String capture_url = "vfw:Microsoft WDM Image Capture (Win32):0";
	private DataSource dataSource;
	private Player player;
	private CaptureDeviceInfo device;
	private MediaLocator ml;
	
	public JMFUtils() throws Exception{
		device = CaptureDeviceManager.getDevice(capture_url);
		ml = device.getLocator();
		if (player == null) {
			dataSource = Manager.createDataSource(ml);
			player = Manager.createRealizedPlayer(dataSource);
			if(player.getState() != Player.Started){
				player.start();
			}
		}
	}
	
	

	public Image doCaptured() {
		FrameGrabbingControl frameGrabbing = (FrameGrabbingControl) player
				.getControl("javax.media.control.FrameGrabbingControl");
		Buffer buffer = frameGrabbing.grabFrame();
		
		Image image = new BufferToImage((VideoFormat) buffer.getFormat()).createImage(buffer);
		
		return image;
	}

	public String saveImage(Image image) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D graph = bufferedImage.createGraphics();
		graph.drawImage(image, null, null);
		String filename = new Date().getTime() + ".jpg";
		File file = new File(filename);
		filename = file.getAbsolutePath();
		try {
			FileOutputStream out = new FileOutputStream(file);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder
					.getDefaultJPEGEncodeParam(bufferedImage);
			param.setQuality(1.0f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(bufferedImage);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

}
