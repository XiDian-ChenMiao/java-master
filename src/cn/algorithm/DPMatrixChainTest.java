package cn.algorithm;

import java.util.Arrays;


/**
 * 动态规划算法之矩阵链乘
 * @ClassName: DPMatrixChainTest 
 * @author 陈苗 
 * @date 2016年5月6日 上午10:58:48
 */
public class DPMatrixChainTest {

	private int[] rowCount;//一维数组模拟的二维数组的行列数
	private int[][] min;//记录链乘需要的最少数乘次数
	private int[][] breakLoc;//记录最优乘法的断开位置
	/**
	 * 默认构造函数
	 */
	public DPMatrixChainTest() {}
	/**
	 * 传参构造函数
	 * @param _rowCount 各矩阵的行数
	 * @param matrixCount 矩阵的个数
	 */
	public DPMatrixChainTest(int[] _rowCount, int matrixCount) {
		rowCount = Arrays.copyOf(_rowCount, _rowCount.length);
		initMinMatrix(matrixCount);
		initBreakMatrix(matrixCount);
	}
	/**
	 * 初始化存放最小值结果的矩阵
	 * @param _matrixCount
	 */
	private void initMinMatrix(int _matrixCount) {
		min = new int[_matrixCount][];
		for(int i = 0;i < _matrixCount; ++i)
			min[i] = new int[_matrixCount];
	}
	/**
	 * 初始化存放划分位置的矩阵
	 * @param _matrixCount
	 */
	private void initBreakMatrix(int _matrixCount) {
		breakLoc = new int[_matrixCount][];
		for(int i = 0;i < _matrixCount; ++i)
			breakLoc[i] = new int[_matrixCount];
	}
	/**
	 * 执行矩阵链乘运算
	 * 该算法的时间复杂度上界为O（n^3），占用的空间复杂度显然为O（n^2）
	 */
	public void executeMatrixChain() {
		int t = 0,temp = 0;
		for(int i = 2;i < this.rowCount.length; ++i) {
			for(int j = 0;j < this.rowCount.length - i; ++j) {
				t = j + i - 1;
				min[j][t] = Integer.MAX_VALUE;
				for(int k = j;k < t;k++) {
					temp = min[j][k] + min[k + 1][t] + rowCount[j] * rowCount[k + 1] * rowCount[t + 1];
					if(temp < min[j][t]) {
						min[j][t] = temp;
						breakLoc[j][t] = k;
					}
				}
			}
		}
		System.out.println("计算最小结果为：" + min[0][this.rowCount.length - 2]);
	}
	/**
	 * 打印划分结果的函数
	 * @param i 矩阵前区间
	 * @param j 矩阵后区间
	 */
	public void printDivideResult(int i,int j) {
		if(i == j)
		{
			System.out.print((char)('A' + i));
			return;
		}
		else
		{
			System.out.print("(");
			printDivideResult(i, this.breakLoc[i][j]);
			printDivideResult(this.breakLoc[i][j] + 1, j);
			System.out.print(")");
		}
	}
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		int[] _rowCount = {30,35,15,5,10,20,25};
		DPMatrixChainTest chainTest = new DPMatrixChainTest(_rowCount, _rowCount.length - 1);
		chainTest.executeMatrixChain();
		chainTest.printDivideResult(0,_rowCount.length - 2);
	}
}
