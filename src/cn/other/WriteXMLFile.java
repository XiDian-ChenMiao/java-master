package cn.other;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;



import cn.database.SQLHelper;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：WriteXMLFile
 * 类描述：XML文件的读写测试方法
 * 创建时间：2015年8月27日 下午5:46:36
 * 创建人： 陈苗
 */
public class WriteXMLFile {
	/**
	 * 将数据库中的数据读出为模型列表
	 * @return
	 */
	public static List<StudentModel> getStudentsList()
	{
		List<StudentModel> list = new ArrayList<StudentModel>();
		try {
			ResultSet rs = SQLHelper.executeQuery("select * from studentinfo");		
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				String engineer = rs.getString("Engineering");
				String grade = rs.getString("Grade");
				String classname = rs.getString("Class");
				int password = rs.getInt("Password");
				StudentModel stu = new StudentModel(id, name, engineer, grade, classname, password);
				list.add(stu);
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return list;
	}
	/**
	 * 将读取到的模型列表数据导出为XML文档保存
	 * @param outPath
	 * @throws IOException
	 */
	public static void writeXMLFile(String outPath) throws IOException
	{
		List<StudentModel> list = getStudentsList();
		//创建一个XML文档的根节点 
		Element root = DocumentHelper.createElement("students"); 
		//由根节点创建整个XML文档框架 
		Document doc = DocumentHelper.createDocument(root);
		Iterator<StudentModel> itor = list.iterator();
		while(itor.hasNext())
		{
			StudentModel stu = itor.next();
			Element idNode = DocumentHelper.createElement("id");
			idNode.setText(((Integer)stu.getId()).toString());
			Element nameNode = DocumentHelper.createElement("name");
			nameNode.setText(stu.getName());
			Element engineerNode = DocumentHelper.createElement("engineer");
			engineerNode.setText(stu.getEngineering());
			Element gradeNode = DocumentHelper.createElement("grade");
			gradeNode.setText(stu.getGrade());
			Element classNode = DocumentHelper.createElement("classname");
			classNode.setText(stu.getClassname());
			Element passwordNode = DocumentHelper.createElement("password");
			passwordNode.setText(((Integer)stu.getPassword()).toString());
			//创建一个学生节点
			Element studentNode = DocumentHelper.createElement("student");
			studentNode.add(classNode);
			studentNode.add(idNode);
			studentNode.add(gradeNode);
			studentNode.add(nameNode);
			studentNode.add(passwordNode);
			studentNode.add(engineerNode);
			root.add(studentNode);
		}
		org.dom4j.io.OutputFormat format = org.dom4j.io.OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(outPath), format);
		writer.write(doc);
		System.out.println("导出成功!");
		writer.close();
	}
	public static void main(String[] args) throws IOException {
		writeXMLFile("student.xml");
	}
}
