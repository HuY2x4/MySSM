package com.zucc.kcgl.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017/3/29.
 */
public class PhotoUtil {

    public static String saveFile( MultipartFile filedata, HttpServletRequest request) {
        // TODO Auto-generated method stub
        String pathval = "C://KCGLIMG//";
        String newFileName = String.valueOf( System.currentTimeMillis());
        String saveFilePath = "images//equipment//";

        File fileDir = new File(pathval + saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String filename=filedata.getOriginalFilename();

        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            String imgPath = saveFilePath + newFileName + "." +extensionName;
            System.out.println(pathval + imgPath);
            FileOutputStream out = new FileOutputStream(pathval + imgPath);
  
            out.write(filedata.getBytes());
            out.flush();
            out.close();

            String path=pathval+imgPath;
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    private void deleteFile(String oldPic) {
        // TODO Auto-generated method stub


        File fileDir = new File(oldPic);
        if (fileDir.exists()) {
   
            fileDir.delete();
        }

    }
    
    
    /*
     * <mvc:resources location="/WEB-INF/images/" mapping="/images/**"  cache-period="31556926" />
	
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="-1"></property>
		<property name="maxInMemorySize" value="2048"></property>
		<property name="defaultEncoding" value="UTF-8"></property>
	</bean>
     * */
}