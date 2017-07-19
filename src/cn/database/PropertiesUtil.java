package cn.database;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：PropertiesReaderUtil
 * 类描述：属性配置文件的工具包
 * 创建时间：2015年8月25日 下午5:45:58
 * 创建人： 陈苗
 */
public class PropertiesUtil {
	private static Properties pro = new Properties();
	/**
	 * 通过属性配置文件获取到配置映射
	 * @param propertiesPath 文件地址
	 * @return 配置映射
	 * @exception 加载输入流产生的异常
	 */
	private PropertiesUtil(){}
	public static Map<String,String> getConfigMap(String propertiesPath) throws Exception{
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesPath);
		Map<String,String> configMap = new HashMap<String, String>();
		pro.load(stream);
		Set<Object> keys = pro.keySet();
		for (Object object : keys) {
			configMap.put(object.toString(), pro.getProperty(object.toString()));
		}
		return configMap;
	}
	/**
	 * 打印属性配置文件中的信息
	 * @param propertiesPath 文件地址
	 */
	public static void printConfigProperties(String propertiesPath) throws Exception{
		Map<String,String> config = getConfigMap(propertiesPath);
		Set<String> keys = config.keySet();
		for (String key : keys) {
			System.out.println("键：" + key + ",值：" + config.get(key));
		}
	}
	public static void main(String[] args) throws Exception{
		printConfigProperties("DatabaseUti/jdbc-sqlserver.properties");
	}
}
