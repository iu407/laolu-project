package com.jyzz.test.jmf;

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

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class JMFTest3 {
	private String capture_url = "vfw:Microsoft WDM Image Capture (Win32):0";
	private DataSource dataSource;
	private Player player;

	public void start() throws NoDataSourceException, IOException,
			NoPlayerException, CannotRealizeException {
		CaptureDeviceInfo device = CaptureDeviceManager.getDevice(capture_url);
        System.out.println("device:"+device);
		dataSource = Manager.createDataSource(new MediaLocator(capture_url));
		player = Manager.createRealizedPlayer(dataSource);
		System.out.println("player state :"+player.getState());
		player.start();
	}

	public Image doCaptured() {
		FrameGrabbingControl frameGrabbing = (FrameGrabbingControl) player
				.getControl("javax.media.control.FrameGrabbingControl");
		Buffer buffer = frameGrabbing.grabFrame();
		Image image = new BufferToImage((VideoFormat) buffer.getFormat())
				.createImage(buffer);
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

	public static void main(String[] args) throws Exception {
		JMFTest3 t = new JMFTest3();
		t.start();
		Image image = t.doCaptured();
		String filename = t.saveImage(image);
		System.out.println(filename);
	}
}