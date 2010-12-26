package com.jyzz.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReadCSV { 

    public static void main(String[] args) { 
        try { 
            File csv = new File("F:/magazine_project/doc/message/products.csv"); // CSV文件 
//            BufferedReader br = new BufferedReader(new FileReader(csv)); 
            String charsetName = "UTF-16";
            
            BufferedReader br = new BufferedReader(
            		new InputStreamReader(
            				new FileInputStream(csv),charsetName));
            // 读取直到最后一行 
            String line = ""; 
            while ((line = br.readLine()) != null) {
//            	line = new String(line.getBytes(), charsetName );
                // 把一行数据分割成多个字段 
                StringTokenizer st = new StringTokenizer(line, ","); 

                while (st.hasMoreTokens()) { 
                    // 每一行的多个字段用TAB隔开表示 
                    System.out.print(st.nextToken() + "\t"); 
                } 
                System.out.println(); 
            } 
            br.close(); 

        } catch (FileNotFoundException e) { 
            // 捕获File对象生成时的异常 
            e.printStackTrace(); 
        } catch (IOException e) { 
            // 捕获BufferedReader对象关闭时的异常 
            e.printStackTrace(); 
        } 
    } 
}

