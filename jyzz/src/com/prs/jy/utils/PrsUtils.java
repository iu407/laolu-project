package com.prs.jy.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import au.com.bytecode.opencsv.CSVReader;

import com.prs.jy.model.Goods;
import com.prs.jy.model.User;
import com.prs.jy.service.SecurityUserHolder;


public class PrsUtils {

	public static void md5(){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
	}
	/**
	 * 读取文件
	 * @param importFile
	 * @throws Exception 
	 */
	public static List<Goods>  getGoodsFromCSVInputStream(InputStream in) throws Exception {
		User user = SecurityUserHolder.getCurrentUser();
		CSVReader csvReader = null;
		String charsetName = PrsConstants.UTF16;
		List<Goods> goodses = new ArrayList<Goods>();
		
		NumberFormat formatter=NumberFormat.getNumberInstance();
		formatter.setMaximumFractionDigits(2);
		
			csvReader = new CSVReader(new InputStreamReader(in,charsetName));   

			if (csvReader != null) {
				csvReader.readNext();
				String[] csvRow = null;// row
				while ((csvRow = csvReader.readNext()) != null) {
					Goods goods = new Goods();
					goods.setAuthorid(user.getUserid());
					goods.setAuthorname(user.getUsername());
					goods.setCategoryid(11);
					for (int i = 0; i < csvRow.length; i++) {
						String temp = csvRow[i];
						switch (i) {
						case 0:
							if (StringUtils.isNotEmpty(temp)) {
								goods.setJyid(temp);
							}
							break;
						case 2:
							if (StringUtils.isNotEmpty(temp)) {
								goods.setGoodsname(temp);
								goods.setDetail(temp);
							}
							break;
						case 13://价格
							if (StringUtils.isNotEmpty(temp)) {
//								goods.setPrice(new BigDecimal());
								double d = Double.valueOf(temp);
								String dd = formatter.format(d);
								d = Double.valueOf(dd);
								BigDecimal bigDecimal = new BigDecimal(d);
								goods.setPrice(bigDecimal);
							}
							break;
						default:
							break;
						}
					}
					goodses.add(goods);
				}
				
			}
			return goodses;
	}

}
