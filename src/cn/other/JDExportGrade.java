package cn.other;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * 交大医学院导出小学期成绩等级表格类
 * @author Administrator
 *
 */
public class JDExportGrade {
	private static WritableWorkbook levelBook = null;
	private static Workbook gradeBook = null;
	private static boolean isNumberOne = true;
	/**
	 * 计算总成绩并且输出等级表格的函数
	 * @param excelPath 传入的的EXCEL路径，使用绝对路径
	 */
	public static void calculateSumGradeAndExportLevel(String excelPath){
		isNumberOne = true;
		try {
			char classNumber = excelPath.substring(excelPath.lastIndexOf("\\") + 1).toCharArray()[0];
			String prefixFileName = excelPath.substring(0, excelPath.lastIndexOf("\\"));
			if(classNumber == '2' || classNumber == '3')
				levelBook = Workbook.createWorkbook(new FileOutputStream(prefixFileName + "\\小学期实训成绩10" + classNumber + ".xls"));
			else
			{
				isNumberOne = false;
				levelBook = Workbook.createWorkbook(new FileOutputStream(prefixFileName + "\\小学期实训成绩20" + classNumber + ".xls"));
			}
			gradeBook = Workbook.getWorkbook(new FileInputStream(excelPath));
			int sheetSize = gradeBook.getNumberOfSheets();
			for(int i = 0;i < sheetSize;i++)
			{
				Sheet sheet = gradeBook.getSheet(i);
				WritableSheet levelSheet = levelBook.createSheet("工作薄" + (i+1), i);
				generateHeader(levelSheet);
				int rows = sheet.getRows();
				for(int j = 1;j < rows;j++)
				{
					float dutyGrade = Float.parseFloat(sheet.getCell(10, j).getContents() == "" ? "0" : sheet.getCell(10, j).getContents());
					float dailyGrade = Float.parseFloat(sheet.getCell(11, j).getContents() == "" ? "0" : sheet.getCell(11, j).getContents());
					float answerGrade = Float.parseFloat(sheet.getCell(12, j).getContents() == "" ? "0" : sheet.getCell(12, j).getContents());
					float sumGrade = dutyGrade + dailyGrade + answerGrade;
					writeLevelSheet(sheet, levelSheet, j, sumGrade);
				}
			}
			levelBook.write();
			gradeBook.close();
			levelBook.close();
			System.out.println("小学期实训成绩" + (isNumberOne ? "10" : "20") + classNumber + ".xls导出成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从源表中取出合适列构成新的表格
	 * @param source_sheet 源成绩登记表
	 * @param dest_Sheet 输出等级表格
	 * @param rowNumber 行号
	 * @param sumGrade 总成绩
	 */
	private static void writeLevelSheet(Sheet source_sheet,WritableSheet dest_Sheet, int rowNumber,float sumGrade) throws Exception {
		dest_Sheet.addCell(new Label(0,rowNumber,source_sheet.getCell(0, rowNumber).getContents()));
		dest_Sheet.addCell(new Label(1,rowNumber,source_sheet.getCell(1, rowNumber).getContents()));
		dest_Sheet.addCell(new Label(2,rowNumber,source_sheet.getCell(2, rowNumber).getContents()));
		dest_Sheet.addCell(new Label(3,rowNumber,justifyGradeLevel(sumGrade,4)));
	}
	/**
	 * 根据总分成绩给出成绩等级
	 * @param grade 成绩分数
	 * @param addPoints 酌情加分
	 * @return 返回优良中差等级
	 */
	public static String justifyGradeLevel(float grade,float addPoints)
	{
		if(isNumberOne && grade != 0)
			grade += addPoints;
		if(grade >= 90)
			return "优";
		else if(grade >= 80)
			return "良";
		else if(grade >= 70)
			return "中";
		else if(grade >= 60)
			return "及格";
		else
		{
			if(grade == 0.0)
				return "未参加";
			else
				return "不及格";	
		}
	}
	/**
	 * 生成等级表的表头
	 * @param sheet 工作薄
	 */
	public static void generateHeader(WritableSheet sheet)
	{
		WritableFont headerFont = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
		WritableCellFormat cellFormat = new WritableCellFormat(headerFont);
		Label stu_numberLab = new Label(0, 0, "学号",cellFormat);
		Label stu_nameLab = new Label(1, 0, "姓名",cellFormat);
		Label stu_classLab = new Label(2, 0, "班级",cellFormat);
		Label stu_levelLab = new Label(3, 0, "实训成绩",cellFormat);
		try {
			sheet.addCell(stu_numberLab);
			sheet.addCell(stu_nameLab);
			sheet.addCell(stu_classLab);
			sheet.addCell(stu_levelLab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 在答辩成绩上面加分
	 * @param addPoints 加分多少
	 * @param excelPath 表格路径
	 */
	public static void addPointsInAnswer(String excelPath,float addPoints) throws Exception{
		String prefixFileName = excelPath.substring(0, excelPath.lastIndexOf("\\"));
		char classNumber = excelPath.substring(excelPath.lastIndexOf("\\") + 1).toCharArray()[0];
		gradeBook = Workbook.getWorkbook(new FileInputStream(excelPath));
		levelBook = Workbook.createWorkbook(new FileOutputStream(prefixFileName + "\\Answer" + classNumber + ".xls"));
		WritableSheet writeSheet = levelBook.createSheet("答辩成绩", 0);
		Sheet readSheet = gradeBook.getSheet(0);
		int rows = readSheet.getRows();
		for(int i = 1;i < rows;i++)
		{
			float answerGrade = 0;
			if(readSheet.getCell(12, i).getContents() != "")
				answerGrade = Float.parseFloat(readSheet.getCell(12, i).getContents()) + addPoints;
			float dutyGrade = Float.parseFloat(readSheet.getCell(10, i).getContents() == "" ? "0" : readSheet.getCell(10, i).getContents());
			float dailyGrade = Float.parseFloat(readSheet.getCell(11, i).getContents() == "" ? "0" : readSheet.getCell(11, i).getContents());
			float sumScore = answerGrade + dutyGrade + dailyGrade;
			Label answerLab = new Label(0, i-1, answerGrade == 0 ? "" : answerGrade + "");
			Label sumScoreLab = new Label(1, i-1, sumScore == 0 ? "" : sumScore + "");
			Label levelLab = new Label(2,i-1,justifyGradeLevel(sumScore, 0));
			writeSheet.addCell(answerLab);
			writeSheet.addCell(sumScoreLab);
			writeSheet.addCell(levelLab);
		}
		levelBook.write();
		levelBook.close();
		gradeBook.close();
	}
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		addPointsInAnswer("C:\\Users\\Administrator\\Desktop\\Grades\\2号机房情况.xls", 2);
		//addPointsInAnswer("C:\\Users\\Administrator\\Desktop\\Grades\\3号机房情况.xls", 2);
		System.out.println("导出成功！");
	}
}
