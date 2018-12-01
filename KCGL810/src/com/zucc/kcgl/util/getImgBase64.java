package com.zucc.kcgl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import sun.misc.BASE64Encoder;


public class getImgBase64 {

	public static String getImageStr(String imgFile) {
		String imgStr = "";
		try {

		    File file = new File(imgFile);
		    FileInputStream fis = new FileInputStream(file);
		    byte[] buffer = new byte[(int) file.length()];
		    int offset = 0;
		    int numRead = 0;
		    while (offset < buffer.length && (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {
		        offset += numRead;
		    }

		    if (offset != buffer.length) {
		        throw new IOException("Could not completely read file "
		                + file.getName());
		    }
		    fis.close();
		    BASE64Encoder encoder = new BASE64Encoder();
		    imgStr = encoder.encode(buffer);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		String img_path="data:image/jpeg;base64,"+imgStr;
		return img_path;
	}
}
