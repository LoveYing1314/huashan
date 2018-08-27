package com.zzy.BaseUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.UUIDUtils.UUIDCreate;

public class FileUpload {
	/**
	 * 
	 * @param file2
	 *            ��Ӧ�ϴ���ͼƬ�ļ�
	 * @param contentType2
	 *            ��Ӧ�ϴ�ͼƬ�ĸ�ʽ
	 * @param filename2
	 *            ��Ӧ�ϴ�ͼƬ������
	 * @return ���ر����·��
	 * @throws IOException 
	 */
	public static String fileUpload(File file2, String contentType2, String filename2, String fileDir) throws IOException {
			if (file2 != null) {

				String uuid = UUIDCreate.getUUID() + UUIDCreate.getUUID();

				String rootPath = System.getProperty("user.dir").split("bin")[0] + "webapps" + File.separator + "image" + File.separator;
				
				System.out.println("------------------------------------"+file2.exists());
				System.out.println("------------------------------------"+file2.getTotalSpace());
				
				String filePath = rootPath + fileDir + File.separator + uuid + ".png";
				
				File destFile = new File(filePath);

//				FileInputStream inputStream = new FileInputStream(file2);
				FileOutputStream outputStream = new FileOutputStream(destFile);
				
//				byte[] b= new byte[1024];
//				int n;
//				
//				while((n = inputStream.read(b)) != -1){
//					outputStream.write(b, 0, n);
//				}
				
				long l = FileUtils.copyFile(file2, outputStream);
				
				if(l != 0){
					String urlPath = "/" + fileDir + "/" + uuid + ".png";
					
					return urlPath;
				}
				
				
			}
			return null;
		}

}
