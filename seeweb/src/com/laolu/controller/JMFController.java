package com.laolu.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 后台管理
 *
 * @author laolu
 *
 */
@Controller
@RequestMapping("/jmf")
public class JMFController {
	
//	@Autowired
//	private JMFUtils jmfUtils;
	
	@RequestMapping("/show")
	public void show(HttpServletRequest request, 
            HttpServletResponse response) throws IOException {
		try {
			File file = new File("C:/img/Sunset.jpg");
		    Image image = ImageIO.read(file);
//			jmfUtils.start();
//			Image image = jmfUtils.doCaptured();
			// String filename = jmfUtils.saveImage(image);
//			System.out.println(image);
			BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			bufferedImage.getGraphics().drawImage(image, 0, 0,
					image.getWidth(null), image.getHeight(null), null);

			ChartUtilities.writeBufferedImageAsJPEG(response.getOutputStream(), bufferedImage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
