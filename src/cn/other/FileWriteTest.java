package cn.other;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

import org.junit.Test;
/**
 * 
 * 项目名称：JavaSE
 * 类名称：FileWriteTest
 * 类描述：文件的写入测试
 * 创建时间：2015年8月10日 下午4:16:15
 * 创建人： 陈苗
 */
public class FileWriteTest {
	@Test
	public void simpleFileWrite() throws FileNotFoundException,IOException {
		InputStream stream = new FileInputStream("C:/Users/Administrator/Desktop/测试文件/F1.doc");
		byte[] read = new byte[1024];
		RandomAccessFile file = new RandomAccessFile("C:/Users/Administrator/Desktop/Report.doc", "rw");
		int readByte;
		while((readByte = stream.read(read, 0, 1024)) > 0){
			file.write(read, 0, readByte);
		}
		stream.close();
		file.close();
		System.out.println("写入成功！");
	}
	@Test
	public void multipleFilesWrite() throws Exception {
		//从输入流中读取的字节内容
		byte[] read = new byte[1024];
		//每一次读出的临时字节数
		int tempBytesCount;
		//定义报告文件
		RandomAccessFile file = new RandomAccessFile("C:/Users/Administrator/Desktop/Report.doc", "rw");
		for(int i = 1;i <= 4;i++){
			String path = "C:/Users/Administrator/Desktop/测试文件/F" + i + ".txt";
			InputStream stream = new FileInputStream(path);
			while((tempBytesCount = stream.read(read, 0, 1024)) > 0){
				file.write(read, 0, tempBytesCount);
			}
			//定位到已经读入的数据字节最后面的位置
			file.seek(file.getFilePointer());
			System.out.println("指针在：" + file.getFilePointer());
			stream.close();
		}
		file.close();
		System.out.println("写入成功！");
	}
	@Test
	public void multiFileWriteByFileWriter() throws Exception {
		//从输入流中读取的字节内容
		byte[] read = new byte[1024];
		//每一次读出的临时字节数
		@SuppressWarnings("unused")
		int tempBytesCount;
		@SuppressWarnings("resource")
		FileWriter file = new FileWriter("C:/Users/Administrator/Desktop/Report.doc", true);
		for(int i = 1;i <= 4;i++){
			String path = "C:/Users/Administrator/Desktop/测试文件/F" + i + ".doc";
			InputStream stream = new FileInputStream(path);
			while((tempBytesCount = stream.read(read, 0, 1024)) > 0){
				file.write(read.toString());
			}
			stream.close();
		}
		System.out.println("导出成功");
	}
	@Test
	public void useBuffer() {   
        BufferedWriter out = null;
        //从输入流中读取的字节内容
  		byte[] read = new byte[1024];
  		//每一次读出的临时字节数
  		@SuppressWarnings("unused")
  		int tempBytesCount;
        String file = "C:/Users/Administrator/Desktop/Report.doc";
        try {   
           out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
           for(int i = 1;i <= 4;i++){
	   			String path = "C:/Users/Administrator/Desktop/测试文件/F" + i + ".doc";
	   			InputStream stream = new FileInputStream(path);
	   			while((tempBytesCount = stream.read(read, 0, 1024)) > 0){
	   				out.write(read.toString());
	   			}
	   			stream.close();
   			}
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                out.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
    } 
}
