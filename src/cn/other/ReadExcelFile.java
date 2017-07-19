package cn.other;

import java.io.FileInputStream;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：ReadExcelFile
 * 类描述：Excel读写的测试类
 * 创建时间：2015年8月27日 下午5:03:27
 * 创建人： 陈苗
 */
public class ReadExcelFile {
	public String getContentsFromExcel(String excelPath) throws BiffException, IOException
	{
		String contents = "";
		//得到Excel对应的Workbook类下的对象
		Workbook excel = Workbook.getWorkbook(new FileInputStream(excelPath));
		//得到此Excel对应的工作薄的数量
		int sheetSize = excel.getNumberOfSheets();
		for(int i = 0;i < sheetSize;i++)
		{
			Sheet sheet = excel.getSheet(i);
			for(int j = 1;j < sheet.getRows();j++)
			{
				for(int k = 0;k < 5;k++)
				{
					Cell cell = sheet.getCell(k, j);
					if(k == 0)
					{
						contents += j + ". " + cell.getContents() + "\n" + "Answer: " + sheet.getCell(7, j).getContents() + "\n";
					}
					else
					{
						contents += "\t" + Character.toString((char) ('A' + k - 1)) + ")." + cell.getContents() + "\n";
					}
				}
			}
		}
		return contents;
	}
	public static void main(String[] args) throws BiffException, IOException {
		ReadExcelFile reader = new ReadExcelFile();
		System.out.println(reader.getContentsFromExcel("E:\\Microsoft-2.xls"));
	}
}
