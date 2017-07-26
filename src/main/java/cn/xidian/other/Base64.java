package cn.xidian.other;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Base64
 * 类描述：Base64加密和解密测试
 * 创建时间：2015年9月25日 上午11:25:24
 * 创建人： 陈苗
 */
public class Base64 {
	public static void main(String[] args) throws IOException {
		BASE64Encoder encoder = new BASE64Encoder();
		String encodeResult = encoder.encode("Chen MiaoM".getBytes());
		System.out.println("Encode:" + encodeResult);
		System.out.println("Encode:" + encoder.encode("Chen MiaoM".getBytes()));
		BASE64Decoder decoder = new BASE64Decoder();
		String decodeResult = new String(decoder.decodeBuffer(encodeResult));
		System.out.println("Decode:" + decodeResult);
	}
}
