package com.jyzz.test.jmf;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;

public class JMFTest {
	private static String capture_url = "vfw:Luminositi Softcam [VFW]:0";
	private static Player player = null;

	public static void main(String[] args) throws NoPlayerException,
			IOException, CannotRealizeException {

		MediaLocator ml = null; // 视频采集设备对应的MediaLocator
		VideoFormat currentFormat = null;// 用户定制获得视频采集设备支持的格式
		Format setFormat = null;// 用户定制视频采集设备输出的格式
		Format[] videoFormats = null;// 视频采集设备支持的所有格式
		player = null;
		System.out.println(" 自动搜索微软监控设备");// VFW:微软的 Video for Windows

		// 取得设备这里可以做的更加强壮一点，可以搜索所有的设备然后，找到一个合适的使用
		// Vector deviceList = CaptureDeviceManager.getDeviceList(null);
		CaptureDeviceInfo device = CaptureDeviceManager.getDevice(capture_url);// capture
		System.out.println(device);
		ml = device.getLocator();// 对应的 locator
		player = Manager.createRealizedPlayer(ml);
		play();
		System.out.println("开始捕捉");
		player.stop();
		FrameGrabbingControl fbc = (FrameGrabbingControl) player
				.getControl("javax.media.control.FrameGrabbingControl");
		Buffer buf = fbc.grabFrame();
		if (buf.getData() == null) {
			System.out.println("没有得到数据退出");
			System.exit(0);
		}
		System.out.println(buf);
		BufferToImage bti = new BufferToImage((VideoFormat) buf.getFormat());
		System.out.println("bti:" + bti);
		Image image = bti.createImage(buf);// 图片
		System.out.println(image);
		Date now = new Date();
		String filename = "abc" + now.getTime() + ".png";
		File out = new File(filename);
		BufferedImage bi;
		try {
			bi = toBufferedImage(image);
			ImageIO.write(bi, "png", out);
			System.out.println("保存到文件：" + filename);

		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		// player.close();
		System.out.println("exit.....");
		System.exit(0);
	}

	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// This code ensures that all the pixels in the image are loaded
		ImageIcon imageicon = new ImageIcon(image);
		if (imageicon == null) {
			System.out.println("imageicon is null");
			System.exit(1);
		}
		image = imageicon.getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image
					.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
			System.err.println("The system does not have a screen!");
			System.exit(0);
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null), image
					.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	public static boolean hasAlpha(Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}
		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
		}

		// Get the image's color model
		ColorModel cm = pg.getColorModel();
		return cm.hasAlpha();
	}

	public static void play() {
		if (player.getState() != player.Started) {
			player.start();
		}
	}
}
