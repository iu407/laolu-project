package com.lu.see;

import java.io.Serializable;

/**
 * 路径模型
 * @author laolu
 *
 */
public class PathModel implements Serializable{
	private static final long serialVersionUID = 4296609921179250754L;
	
	
	private String ipaddress = "58.247.180.253";//ip地址
	private String port = "80";//端口；例如8080
	private String path = "";//路径；例如/jyzz/jmf/show
	public PathModel() {
		super();
	}
	public PathModel(String ipaddress, String port, String path) {
		super();
		this.ipaddress = ipaddress;
		this.port = port;
		this.path = path;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "http://"+ ipaddress +":"+ port + path;
	}
}
