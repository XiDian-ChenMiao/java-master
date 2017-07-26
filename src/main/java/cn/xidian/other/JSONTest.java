package cn.xidian.other;

import java.io.File;
import java.io.IOException;

import cn.xidian.annotation.Student;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 关于对象与JSON对象之间的转换
 * @ClassName: JSONTest 
 * @author 陈苗 
 * @date 2016年6月29日 下午8:04:06
 */
public class JSONTest {
	public static void main(String[] args) {
		ObjectMapper obm = new ObjectMapper();
		
		Student student = new Student();
		student.setStu_name("陈苗");
		student.setStu_number(1503121727);
		student.setStu_score(86.9f);
		try{
			//写入JSON文件
			obm.writeValue(new File("E:/student.json"), student);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Student stuFromJson = null;
		try {
			stuFromJson = obm.readValue(new File("E:/student.json"), Student.class);
			if (stuFromJson != null) {
				System.out.println(stuFromJson);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
