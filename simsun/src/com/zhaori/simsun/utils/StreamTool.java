package com.zhaori.simsun.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import android.util.Log;

public class StreamTool {
	 
	 public static void save(File file, byte[] data) throws Exception {
		 FileOutputStream outStream = new FileOutputStream(file);
		 outStream.write(data);
		 outStream.close();
	 }
	 
	 //创建交易序号
	 public static String getJYXH() {
		 String result = null;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 result = sdf.format(new Date());
		 int i = new Random().nextInt(900) + 100;
		 Log.i("a", "3位随机数为:  " + i);
		 return result = result + String.valueOf(i);
	 }
	 
	 public static String readLine(PushbackInputStream in) throws IOException {
			char buf[] = new char[128];
			int room = buf.length;
			int offset = 0;
			int c;
loop:		while (true) {
				switch (c = in.read()) {
					case -1:
					case '\n':
						break loop;
					case '\r':
						int c2 = in.read();
						if ((c2 != '\n') && (c2 != -1)) in.unread(c2);
						break loop;
					default:
						if (--room < 0) {
							char[] lineBuffer = buf;
							buf = new char[offset + 128];
						    room = buf.length - offset - 1;
						    System.arraycopy(lineBuffer, 0, buf, 0, offset);
						   
						}
						buf[offset++] = (char) c;
						break;
				}
			}
			if ((c == -1) && (offset == 0)) return null;
			return String.copyValueOf(buf, 0, offset);
	}
	 
	/**
	* 读取流
	* @param inStream
	* @return 字节数组
	* @throws Exception
	*/
	public static String readStream(InputStream inStream) throws Exception{
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while( (len=inStream.read(buffer)) != -1){
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			return new String(outSteam.toByteArray());
	}
	
	public static String md5encrypt(String md5Str) throws NoSuchAlgorithmException {
	    MessageDigest md5 = MessageDigest.getInstance("MD5");
	    byte[] byteArray =md5Str.getBytes();
	    byte[] md5Bytes = md5.digest(byteArray);
	    StringBuffer hexValue = new StringBuffer();
	    for (int i=0; i<md5Bytes.length; i++)
	    {
	       int val = ((int) md5Bytes[i] ) & 0xff; 
	       if (val < 16) hexValue.append("0");
	       hexValue.append(Integer.toHexString(val));
	       
	    }
	    return hexValue.toString();
}
}
