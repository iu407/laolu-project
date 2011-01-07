package com.jyzz.test;

import java.io.File;
import java.io.FileFilter;

public class ImgFileFilter implements FileFilter {
	private String keyword;

	@Override
	public boolean accept(File pathname) {
		return false;
	}

}
