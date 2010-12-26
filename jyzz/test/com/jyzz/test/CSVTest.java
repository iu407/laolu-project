package com.jyzz.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

import au.com.bytecode.opencsv.CSVReader;

public class CSVTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSVTest csvTest = new CSVTest();
		csvTest.importCsvFile(new File("F:/magazine_project/doc/message/products.csv"));
	}

	public void importCsvFile(File importFile) {

		CSVReader csvReader = null;
		 String charsetName = "UTF-16";
		try {
//			csvReader = new CSVReader(new FileReader(importFile), ',');// importFile为要导入的文本格式逗号分隔的csv文件，提供getXX/setXX方法
			csvReader = new CSVReader(new InputStreamReader(new FileInputStream(importFile),charsetName));   

			if (csvReader != null) {
				csvReader.readNext();
				String[] csvRow = null;// row
				while ((csvRow = csvReader.readNext()) != null) {
					for (int i = 0; i < csvRow.length; i++) {
						String temp = csvRow[i];
						switch (i) {
						case 0:
							if (StringUtils.isNotEmpty(temp)) {
								System.out.print(temp);
							}
							break;
						case 1:
							if (StringUtils.isNotEmpty(temp)) {
								System.out.print(temp);
							}
							break;
						case 2:
							if (StringUtils.isNotEmpty(temp)) {
								System.out.print(temp);
							}
						case 13:
							if (StringUtils.isNotEmpty(temp)) {
								System.out.print(temp);
							}
							break;
						default:
							break;
						}
					}
					System.out.println();
					// 保存linkman到数据库
//					if (linkman.getLinkmanName() != null
//							&& linkman.getLinkmanEmail() != null) {
//						EmailLinkmanAPI.insertLinkman(linkman);
//					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
