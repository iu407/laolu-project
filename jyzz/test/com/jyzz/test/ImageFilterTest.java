package com.jyzz.test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jhlabs.image.AbstractBufferedImageOp;

public class ImageFilterTest {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String filePath = "C:/img/201011GQ智族.jpg";
		BufferedImage src = ImageIO.read(new File(filePath));
		
		
		com.aviyehuda.easyimage.Image image  = new com.aviyehuda.easyimage.Image(filePath);
		int h = 300;
		double width = image.getWidth();
		double height = image.getHeight();
		double r = width/height;
		
		Double d = r*h;
		System.out.println("比例："+r);
		int w = d.intValue();

		File output = new File("C:/img/201011GQ智族4.jpg");
		
		BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
		ScaleFilter filter = new ScaleFilter(w, h);
		dst = filter.filter(src, dst);
		ImageIO.write(dst, "jpg", output);

	}

}

class ScaleFilter extends AbstractBufferedImageOp{

	private int width;
	private int height;

	/**
	 * Construct a ScaleFilter.
	 */
	public ScaleFilter() {
		this(32, 32);
	}

	/**
	 * Construct a ScaleFilter.
	 * 
	 * @param width
	 *            the width to scale to
	 * @param height
	 *            the height to scale to
	 */
	public ScaleFilter(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public BufferedImage filter(BufferedImage src, BufferedImage dst) {
		if (dst == null) {
			ColorModel dstCM = src.getColorModel();
			dst = new BufferedImage(dstCM, dstCM
					.createCompatibleWritableRaster(width, height), dstCM
					.isAlphaPremultiplied(), null);
		}

		Image scaleImage = src.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		Graphics2D g = dst.createGraphics();
		g.drawImage(scaleImage, 0, 0, width, height, null);
		g.dispose();

		return dst;
	}

	public String toString() {
		return "Distort/Scale";
	}

}