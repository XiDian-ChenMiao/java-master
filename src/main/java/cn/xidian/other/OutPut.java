package cn.xidian.other;
import java.util.*;
class Result
{
	protected double aver;
	protected double warmPer;
	protected double coldPer;
	public Result(double aver, double warmPer, double coldPer) {
		super();
		this.aver = aver;
		this.warmPer = warmPer;
		this.coldPer = coldPer;
	}
	public Result(){}
}
public class OutPut {
	private int allCount = 0;
	private int warmCountLevel = 0;
	private int coldCountLevel = 0;
	private double warmPercent = 0.0;
	private double coldPercent = 0.0;
	List<Result> list = new ArrayList<Result>();
	protected void outputValue(double[] average,int[] level,double increase)
	{
		double compare = average[0] + increase;
		int start = 0;
		double sum = 0.0;
		double aver = 0.0;
		for(int i = 0;i < average.length;)
		{
			for(int j = i;j < average.length;j++)
			{
				if(average[j] <= compare)
					allCount++;
				else
					break;
			}
			while(start < allCount)
			{
				sum += average[i];
				if(level[i] == -2 || level[i] == -3)
					coldCountLevel++;
				if(level[i] == 2 || level[i] == 3)
					warmCountLevel++;
				i++;
				start++;
			}
			//平均值
			aver = sum / allCount;
			//热水平所占百分比
			warmPercent = (double)warmCountLevel / (double)allCount;
			//冷水平所占百分比
			coldPercent = (double)coldCountLevel / (double)allCount;
			//System.out.println("平均值：" + aver + ",热水平所占百分比:" + warmPercent + ",冷水平所占百分比:" + coldPercent);
			//封装到Result实体中
			Result rs = new Result(aver, warmPercent, coldPercent);
			list.add(rs);
			//重新复位
			allCount = 0;
			coldCountLevel = 0;
			warmCountLevel = 0;
			sum = 0.0;
			start = 0;
			compare += increase;
		}	
	}
	public static void main(String[] args) {
		double increase = 0.5;
		double[] average = new double[]{10.2,10.8,11.1}; 
		int[] level = new int[]{3,-3,1}; 
		OutPut out = new OutPut();
		out.outputValue(average, level, increase);
		Iterator<Result> itor = out.list.iterator();
		while(itor.hasNext())
		{
			Result rs = itor.next();
			System.out.println("平均值：" + rs.aver + ",热水平所占百分比:" + rs.warmPer + ",冷水平所占百分比:" + rs.coldPer);
		}
	}

}
