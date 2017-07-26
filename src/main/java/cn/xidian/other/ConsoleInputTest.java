package cn.xidian.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.junit.Test;
/**
 * 控制台控制输入测试
 * @ClassName: ConsoleInputTest 
 * @author 陈苗 
 * @date 2016年5月28日 下午12:20:34
 */
public class ConsoleInputTest {
	@Test
	public void testScanner() {
		Scanner scanner = new Scanner(System.in);
		int data;
		while((data = scanner.nextInt()) != -1) {
			System.out.println(data);
		}
	}
	@Test
	public void testBufferReader() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String data;
		while(!(data = reader.readLine()).equals("EOF")) {
			System.out.println(data);
		}
	}
}
