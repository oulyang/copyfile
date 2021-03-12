package Copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//拷贝源
		File srcfile=new File("D:\\应用\\虎牙");
		//拷贝目的地
		File destfile=new File("C:\\File");
		//拷贝方法
		copyFile(srcfile,destfile);
	}

	private static void copyFile(File srcfile, File destfile) {
		// TODO Auto-generated method stub
		if(srcfile.isFile()) {
			//拷贝文件
			FileInputStream fis=null;
			FileOutputStream fos=null;
			try {
				//一边读一边写
				//读文件
				fis=new FileInputStream(srcfile);
				//写文件
				fos=new FileOutputStream(destfile.getAbsolutePath().endsWith("\\")?destfile.getAbsolutePath():destfile.getAbsolutePath()+"\\" +srcfile.getAbsolutePath().substring(3));
				
				byte[] bytes=new byte[1024*1024];//一次复制1Mb
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
		//拷贝目录
		//获取所有子File
		File[] srcfiles=srcfile.listFiles();
		for(File file:srcfiles) {
			if(file.isDirectory()) {
				//获取源目录
				String srcpath=file.getAbsolutePath();
				//得到目的目录
				String destpath=destfile.getAbsolutePath().endsWith("\\")?destfile.getAbsolutePath():destfile.getAbsolutePath()+"\\" +srcpath.substring(3);
				//创建目的目录
				File newFile=new File(destpath);
				if(!newFile.exists()) {
					newFile.mkdirs();
				}
			}
			
			copyFile(file,destfile);
		}
		
	}

}
