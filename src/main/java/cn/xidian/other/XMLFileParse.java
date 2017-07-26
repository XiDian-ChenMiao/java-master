package cn.xidian.other;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：XMLFileParse
 * 类描述：XML文档的解析
 * 创建时间：2015年8月27日 下午5:56:07
 * 创建人： 陈苗
 */
public class XMLFileParse {
	/**
	 * 静态的解析XML文档方法
	 */
	public static void parseXML() {
		//得到解析器的工厂实例
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			//将XML转化为文件输入流
			InputStream is = new FileInputStream("XMLFileTest.xml");
			//文档构造器加载输入流成为一个Document对象
			org.w3c.dom.Document doc = db.parse(is);
			//得到此对象的根节点
			Element root = doc.getDocumentElement();
			//得到根节点下面的所有子节点
			NodeList nodes = root.getChildNodes();
			for(int i = 0;i < nodes.getLength();i++)
			{
				//对于子节点的解析
				Node node = nodes.item(i);
				for(Node child = node.getFirstChild();child != null;child = child.getNextSibling())
				{
					if(child.getNodeType() == Node.ELEMENT_NODE)
					{
						System.out.println(child.getFirstChild().getNodeValue());
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
