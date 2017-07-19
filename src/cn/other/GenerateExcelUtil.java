package cn.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：GenerateXMLUtil
 * 类描述：生成XML的工具类
 * 创建时间：2015年12月5日 下午2:29:32
 * 创建人： 陈苗
 */
public class GenerateExcelUtil {

	private static WritableWorkbook workBook;
	private static WritableSheet sheet;
	/**
	 * 生成Excel方法
	 * @param outputPath 输出文件路径
	 * @param headers Excel中头部信息
	 * @param contents 内容
	 * @return 导出成功与否标志
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static boolean generateExcel(String outputPath,List<String> headers,List<Resume> contents) throws FileNotFoundException, IOException, RowsExceededException, WriteException
	{
		if(outputPath == null || "".equals(outputPath))
			return false;
		else
		{
			if(headers.size() == 0)
				return false;
			else
			{
				workBook = Workbook.createWorkbook(new FileOutputStream(new File(outputPath)));
				sheet = workBook.createSheet("工作薄", 0);
				generateHeader(sheet, headers);
				generateContent(sheet,contents,headers.size());
				workBook.write();
				workBook.close();
				return true;
			}
		}
	}
	/**
	 * 生成等级表的表头
	 * @param sheet 工作薄
	 * @param headers 头部信息
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	private static void generateHeader(WritableSheet sheet,List<String> headers) throws RowsExceededException, WriteException
	{
		WritableFont headerFont = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
		WritableCellFormat cellFormat = new WritableCellFormat(headerFont);
		for(int columnIndex = 0;columnIndex < headers.size();++columnIndex)
		{
			String header = headers.get(columnIndex);
			Label headerLab = new Label(columnIndex, 0, header, cellFormat);
			sheet.addCell(headerLab);
		}
	}
	/**
	 * 填充内容
	 * @param sheet 工作薄
	 * @param contents 内容列表
	 * @param columnSize 列数
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	private static void generateContent(WritableSheet sheet,List<Resume> contents,int columnSize) throws RowsExceededException, WriteException
	{
		int contentSize = contents.size();
		for(int row = 0;row < contentSize;++row)
		{
			Label resumeId = new Label(0, row, contents.get(row).getResumeid());
			Label name = new Label(0, row, contents.get(row).getName());
			Label school = new Label(0, row, contents.get(row).getSchool());
			Label phonenumber = new Label(0, row, contents.get(row).getPhonenumber());
			Label department = new Label(0, row, contents.get(row).getDepartment());
			Label position = new Label(0, row, contents.get(row).getPosition());
			Label description = new Label(0, row, contents.get(row).getDescription());
			Label mail = new Label(0, row, contents.get(row).getMail());
			
			sheet.addCell(resumeId);
			sheet.addCell(name);
			sheet.addCell(school);
			sheet.addCell(phonenumber);
			sheet.addCell(department);
			sheet.addCell(position);
			sheet.addCell(description);
			sheet.addCell(mail);
		}
	}
	public static void main(String[] args) {
		System.out.println(File.separator);
	}
}
