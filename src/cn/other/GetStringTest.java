package cn.other;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：GetStringTest
 * 类描述：关于字符串的截取测试用例
 * 创建时间：2015年9月18日 下午1:57:57
 * 创建人： 陈苗
 */
public class GetStringTest {

	@Test //通过XML中ID的值找到其对应的值<item id="22" value="192.43.244.18" /><item id="23" value="198.60.22.240" />
	public void getXMLValueById(){
		String content = "<item id=\"22\" value=\"192.43.244.18\" /><item id=\"23\" value=\"198.60.22.240\" />";
		System.out.println("25的起始值为：" + content.indexOf("25"));
		System.out.println("22的长度为：" + "22".length());
		String subStr = content.substring(content.indexOf("22") + "22".length() + 9);
		System.out.println("字串为：" + subStr.substring(0,subStr.indexOf("\"")));
	}
	
	@Test //例子：<value>FXS-1/1/8004/0/1/8/(C)8000/21</value>
	public void getXMLValue(){
		String content = "<value>FXS-1/1/8004/0/1/8/(C)8000/21</value>";
		int bracket_index = content.indexOf("(");
		if(bracket_index > 0)
		{
			//自定义空间为4的存储返回信息的空间
			String[] info = new String[4];
			//初始化从左括号需要后退找到斜线的个数为6
			int oblique_line_number = 6;
			
			char[] arrs = content.toCharArray();
			info[0] = arrs[bracket_index + 1] + "";
			int i = bracket_index + 3;//从右括号第一个位置开始
			while(arrs[i++] != '/');
			info[1] = content.substring(bracket_index + 3,i - 1);
			int temp = i + 1;//从斜线的第一个位置开始
			while(arrs[temp++] != '<');
			info[2] = content.substring(i,temp - 1);
			System.out.println("第一个数：" + info[0] + "\n第二个数：" + info[1] + "\n第三个数：" + info[2]);
			//重新将临时变量值设为左括号的位置
			temp = bracket_index;
			while(oblique_line_number != 0)
			{
				temp--;
				if(arrs[temp] == '/')
					oblique_line_number--;
			}
			System.out.println("第一个斜线的位置为：" + temp);
			int end = temp;
			while(arrs[--temp] != '>');
			info[3] = content.substring(temp + 1, end); 
		}
		else
			System.out.println("内容中不存在要查找的内容");
	}

	@Test
	public void format(){
		String format = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
		if(Pattern.matches(format,"192.168.7.1"))
		{
			System.out.println("匹配成功");
		}
	}
}
