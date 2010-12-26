package com.jyzz.test.jmf;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame {
	public static Player player = null;
	private CaptureDeviceInfo deviceInfo = null;
	private MediaLocator mediaLocator = null;
	private Component component = null;
	private JPanel mainPanel = null;
	private JPanel vedioPanel = null;
	String str1 = "vfw:Logitech USB Video Camera:0";
	String str2 = "vfw:Microsoft WDM Image Capture (Win32):0";

	Test() {
		super("Test for the vedio...");
		Init();
	}

	private void Init() {
		mainPanel = (JPanel) this.getContentPane();
		deviceInfo = CaptureDeviceManager.getDevice(str2);
		mediaLocator = deviceInfo.getLocator();
		try {
			// 利用Medialocator 获取一个player
			player = Manager.createRealizedPlayer(mediaLocator);
			component = player.getVisualComponent();
			if (component != null) {
				vedioPanel = new JPanel();
				vedioPanel.add(component, BorderLayout.NORTH);
				mainPanel.add(vedioPanel);

				this.pack(); // 自动分配窗体大小
				this.setResizable(false);
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				this.setVisible(true);
				player.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Test aTest = new Test();
	}
}