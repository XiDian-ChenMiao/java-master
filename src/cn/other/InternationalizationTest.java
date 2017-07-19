package cn.other;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：InternationalizationTest
 * 类描述：国际化测试
 * 创建时间：2015年9月23日 下午2:49:33
 * 创建人： 陈苗
 */
public class InternationalizationTest {

	/**
	 * 显示国家与语言编号
	 */
	@Test
	public void showLanguageAndCountry(){
		Locale[] local = Locale.getAvailableLocales();
		for (Locale locale : local) {
			System.out.println(locale.getDisplayLanguage() + "=" + locale.getLanguage() + " " + locale.getDisplayCountry() + "=" + locale.getCountry());
		}
	}
	/**
	 * 国际化测试
	 */
	@Test
	public void i18nTest(){
		//取得系统默认的国家及语言环境
		Locale local = Locale.getDefault();
		//根据指定国家或者语言环境加载资源文件
		ResourceBundle bundle = ResourceBundle.getBundle("message", local);
		System.out.println("取得结果为：" + bundle.getString("Hello"));
	}
}
