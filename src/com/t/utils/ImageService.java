package com.t.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.t.core.entities.Merchant;

@Component(value = "imageService")
public class ImageService {
	private static final long serialVersionUID = 572146812454l;  
	private static final int BUFFER_SIZE = 16 * 1024;  

	private void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					this.deleteFile(files[i]);
				}
			}
			file.delete();
		} else {
			System.out.println("删除成功" + '\n');
		}
	}

	public static void copy(File src, File dst) {  
		try {  
			InputStream in = null;  
			OutputStream out = null;  
			try {  
				in = new BufferedInputStream(new FileInputStream(src),  
						BUFFER_SIZE);  
				out = new BufferedOutputStream(new FileOutputStream(dst),  
						BUFFER_SIZE);  
				byte[] buffer = new byte[BUFFER_SIZE];  
				while (in.read(buffer) > 0) {  
					out.write(buffer);  
				}  
			} finally {  
				if (null != in) {  
					in.close();  
				}  
				if (null != out) {  
					out.close();  
				}  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  

}
