package cn.other;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

/**
 * 测试Java中的格式化输出类MessageFormat
 * NOTE:（MessageFormat模式）
 *	FormatElement:
 *		{ArgumentIndex}
 *		{ArgumentIndex，FormatType}
 *		{ArgumentIndex，FormatType，FormatStyle}
 *	FormatType:
 *		number
 *		date
 *		time
 *		choice(需要使用ChoiceFormat)
 *	FormatStyle:
 *		short
 *		medium
 *		long
 *		full
 *		integer
 *		currency
 *		percent
 *		SubformatPattern（子模式）
 *	Notice:
 *		1）ArgumentIndex必须是非负整数；
 *		2）格式化字符串时，两个单引号才表示一个单引号，单个单引号会被省略；
 *		3）无论是有引号字符串还是无引号字符串，左花括号都是不支持的，但支持右花括号显示；
 *		4）每次调用MessageFormat的静态方法format，都会创建一个MessageFormat类型的实例；
 * @ClassName: MessageFormatTest 
 * @author 陈苗 
 * @date 2016年4月14日 上午9:45:22
 */
public class MessageFormatTest {
	@Test
	public void testFormatFloat() {
		String message = "You konw,{0,number,#.#} is a number.";
		Object[] array = new Object[]{new Double(3.1415)};
		String value = MessageFormat.format(message, array);
		System.out.println(value);
	}
	@Test
	public void testFormatDate() {
		String message = "当前时间为：{0,date}";
		Object[] array = new Object[]{new Date()};
		String value = MessageFormat.format(message, array);
		System.out.println(value);
	}
	/**
	 * MessageFormat类的parse方法解析字符串
	 * @throws ParseException
	 */
	@Test
	public void testParseNumber() throws ParseException {
		String sid = "N1234B5678";
		Object[] values = new MessageFormat("N{0,number,integer}B{1,number,integer}").parse(sid);
		for(Object v : values)
			System.out.println(v + "(" + v.getClass().getName() + ")");
	}
}
