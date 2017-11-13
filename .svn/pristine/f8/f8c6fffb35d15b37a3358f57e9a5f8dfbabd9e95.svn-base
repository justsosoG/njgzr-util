package com.njgzr.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *@author Justsoso丶G
 *@description （一句话描述作用）
 *@version 2017年11月7日上午10:34:47
 */
public class ImgDownUtil {
	//链接url下载图片
    public static String downloadPicture(String imgDownLoadUrl) {
        URL url = null;

        try {
            url = new URL(imgDownLoadUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imgpath =  "/data/download";
            
            File f = new File(imgpath);
            if(!f.exists()){
            	f.mkdirs();
            }
            
            String imgName = imgpath+"/"+System.currentTimeMillis()+".jpg";
            
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imgName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
            return imgName;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 识别网络上的二维码
     * @param imgDownLoadUrl
     * @return
     */
    public static String scanQrCodeOnline(String imgDownLoadUrl){
    	try {
			URL url = new URL(imgDownLoadUrl);
			String content = QrcodeUtil.scanQrCodeStream(url.openStream());
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
    
    public static void main(String[] args) {
    	String res = scanQrCodeOnline("https://qr.api.cli.im/qr?data=%25E9%2594%25A6%25E4%25BA%2591%25E6%2599%25BA%25E5%25BC%2580%25E5%2590%258C%25E5%259F%258E&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=7bbe122009a5803da8c465b8dffa7fa5");
    	System.err.println(res);
    }
}
