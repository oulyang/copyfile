package Copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����Դ
		File srcfile=new File("D:\\Ӧ��\\����");
		//����Ŀ�ĵ�
		File destfile=new File("C:\\File");
		//��������
		copyFile(srcfile,destfile);
	}

	private static void copyFile(File srcfile, File destfile) {
		// TODO Auto-generated method stub
		if(srcfile.isFile()) {
			//�����ļ�
			FileInputStream fis=null;
			FileOutputStream fos=null;
			try {
				//һ�߶�һ��д
				//���ļ�
				fis=new FileInputStream(srcfile);
				//д�ļ�
				fos=new FileOutputStream(destfile.getAbsolutePath().endsWith("\\")?destfile.getAbsolutePath():destfile.getAbsolutePath()+"\\" +srcfile.getAbsolutePath().substring(3));
				
				byte[] bytes=new byte[1024*1024];//һ�θ���1Mb
				int readCount=0;
				while((readCount=fis.read(bytes))!=-1) {
					fos.write(bytes, 0, readCount);
				}
				
				fos.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(fis!=null) {
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return;
		}
		//����Ŀ¼
		//��ȡ������File
		File[] srcfiles=srcfile.listFiles();
		for(File file:srcfiles) {
			if(file.isDirectory()) {
				//��ȡԴĿ¼
				String srcpath=file.getAbsolutePath();
				//�õ�Ŀ��Ŀ¼
				String destpath=destfile.getAbsolutePath().endsWith("\\")?destfile.getAbsolutePath():destfile.getAbsolutePath()+"\\" +srcpath.substring(3);
				//����Ŀ��Ŀ¼
				File newFile=new File(destpath);
				if(!newFile.exists()) {
					newFile.mkdirs();
				}
			}
			
			copyFile(file,destfile);
		}
		
	}

}
