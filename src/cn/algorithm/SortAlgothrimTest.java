package cn.algorithm;
import java.util.Arrays;

import org.junit.Test;
/**
 * 打印接口
 * @ClassName: Printer 
 * @author 陈苗 
 * @date 2016年3月26日 上午10:30:50
 */
interface Printer {
	void printData(int[] data);
}
/**
 * 插入排序算法
 * 特点：稳定排序并且是不需要额外空间的排序算法，最优复杂度为O（n），在数据源已倒序排列的情况下产生最坏情况，复杂度为O（n^2）；
 * @ClassName: InsertSort 
 * @author 陈苗 
 * @date 2016年3月26日 上午9:54:52
 */
class InsertSort implements Printer{
	/**
	 * 插入排序逻辑代码
	 */
	public void insertSort(){
		int array[] = {4,1,2,5,6,3};
		for(int i = 1;i < 6;++i){
			int j = i - 1;
			int key = array[i];
			while(j >= 0 && array[j] > key){
				array[j + 1] = array[j];
				--j;
			}
			array[j + 1] = key;
		}
		printData(array);
	}
	/**
	 * 实现打印函数
	 */
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
	}
}
/**
 * 冒泡排序算法
 * 特点：稳定排序并且是不需要额外空间的排序算法，运行时间为O（n^2）。经优化后，最佳运行时间可为O（n）；
 * @ClassName: BubbleSort 
 * @author 陈苗 
 * @date 2016年3月26日 上午10:44:52
 */
class BubbleSort implements Printer {
	/**
	 * 冒泡排序算法逻辑
	 */
	public void bubbleSort(){
		int array[] = {2,4,1,5,6,2};
		for(int i = 5;i > 0;--i){//外循环比较5次
			for(int j = 0;j < i;++j)
			{
				//从大到小排列
				if(array[j] < array[j + 1]){
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		printData(array);
	}
	/**
	 * 优化过之后的冒泡算法逻辑，最优时间复杂度可为O（n）
	 */
	public void betterBubbleSort(){
		int array[] = {2,4,1,5,6,2};
		boolean hasOrder = false;//增加标志位，可在给定数据有序的情况下，通过一次循环来判断实际是否有序，从而减少循环次数
		for(int i = 5;i > 0;--i){//外循环比较5次
			if(hasOrder)
				return;
			hasOrder = true;
			for(int j = 0;j < i;++j)
			{
				//从大到小排列
				if(array[j] < array[j + 1]){
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					hasOrder = false;
				}
			}
		}
		printData(array);
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
}
/**
 * 选择排序算法（不稳定排序方式）
 * @ClassName: SelectSort 
 * @author 陈苗 
 * @date 2016年3月26日 上午11:42:09
 */
class SelectSort implements Printer {
	/**
	 * 选择排序算法逻辑
	 */
	public void selectSort(){
		int array[] = {1,2,3,4,2,1};
		for(int i = 0;i < 5;i++){
			int min = i;//初始化最小下标索引
			for(int j = i + 1;j < 6;j++){
				if(array[min] > array[j])//从小到大排列
					min = j;
			}
			int temp = array[i];
			array[i] = array[min];
			array[min] = temp;
		}
		printData(array);
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
}
/**
 * 归并排序算法（稳定排序）
 * 特点：采用分治法解决问题，其为稳定排序但为需要额外空间的排序算法，最坏和最好情况皆为O（nlgn）；
 * @ClassName: MergeSort 
 * @author 陈苗 
 * @date 2016年3月26日 上午11:54:31
 */
class MergeSort implements Printer {
	/**
	 * 归并
	 */
	public void mergeSort(){
		int array[] = {2,4,1,3,5,7};
		recursiveMergeSort(array,0,5);
		printData(array);
	}
	/**
	 * 递归调用归并方法
	 * @param array 数据源
	 * @param startIndex 开始索引
	 * @param endIndex 结束索引
	 */
	private void recursiveMergeSort(int[] array,int startIndex,int endIndex){
		if(startIndex >= endIndex)
			return;
		int middle = (startIndex + endIndex) / 2;
		recursiveMergeSort(array, startIndex, middle);
		recursiveMergeSort(array, middle + 1, endIndex);
		merge(array,startIndex,middle,endIndex);
	}
	/**
	 * 合并逻辑代码
	 * @param array 数据源
	 * @param startIndex 起始索引
	 * @param middleIndex 中间索引
	 * @param endIndex 结束索引
	 */
	private void merge(int[] array,int startIndex,int middleIndex,int endIndex){
		int[] tempArray = new int[array.length];//创建临时数组
		int middle = middleIndex + 1;//右数组的第一个元素索引
		int index = startIndex;//记录临时数组的索引
		int tempIndex = startIndex;//缓存左数组的第一个元素的索引
		//从两个数组中取出最小的放入临时数组
		while(startIndex <= middleIndex && middle <= endIndex){
			if(array[startIndex] <= array[middle])
				tempArray[index++] = array[startIndex++];
			else
				tempArray[index++] = array[middle++];
		}
		//剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
		while(middle <= endIndex)
			tempArray[index++] = array[middle++];
		while(startIndex <= middleIndex)
			tempArray[index++] = array[startIndex++];
		//将临时数组中的内容拷贝回原数组中
		while(tempIndex <= endIndex)
			array[tempIndex] = tempArray[tempIndex++];
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
}
/**
 * 快速排序算法
 * 特点：快速排序算法不是稳定但不需要额外存储空间的算法，最佳运行时间为O（nlgn），思想为分治法；
 * @ClassName: QuickSort 
 * @author 陈苗 
 * @date 2016年3月26日 下午3:41:16
 */
class QuickSort implements Printer {
	/**
	 * 交换函数
	 * @param array 数组
	 * @param left 左边下标
	 * @param right 右边下标
	 */
	private void exchange(int array[],int left,int right){
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	/**
	 * 快速排序调用函数
	 */
	public void quickSort(){
		int array[] = {2,4,1,3,5,7};
		recursiveQuickSort(array,0,5);
		printData(array);
	}
	/**
	 * 优化过之后的快速排序调用函数
	 */
	public void randomQuickSort(){
		int array[] = {2,4,1,3,5,7};
		randomRecursiveQuickSort(array,0,5);
		printData(array);
	}
	/**
	 * 快速排序算法逻辑
	 * @param data 数据源
	 * @param startIndex 起始索引
	 * @param endIndex 终止索引
	 */
	private void recursiveQuickSort(int data[],int startIndex,int endIndex){
		if(startIndex > endIndex)
			return;
		int middle = partition(data, startIndex, endIndex);
		recursiveQuickSort(data,startIndex,middle - 1);
		recursiveQuickSort(data, middle + 1, endIndex);
	}
	/**
	 * 快速排序中的分割函数
	 * @param data 数据源
	 * @param left 数组起始索引
	 * @param right 数组终止索引
	 * @return 返回一个数字满足左边的数比它小，右边的数字比它大
	 */
	private int partition(int data[],int left,int right){
		int pivot = data[left];
		while(left < right){
			while(left < right && data[right] >= pivot)
				right--;
			if(left < right)
				data[left++] = data[right];
			while(left < right && data[left] <= pivot)
				left++;
			if(left < right)
				data[right--] = data[left];
		}
		data[left] = pivot;
		return left;
	}
	/**
	 * 更现代的划分方法
	 * @param data
	 * @param left
	 * @param right
	 * @return
	 */
	@SuppressWarnings("unused")
	private int anotherPartition(int data[],int left,int right){
		int endValue = data[right];
		int i = left - 1;
		for(int j = left;j < right;j++){
			if(data[j] <= endValue){
				i++;
				exchange(data, i, j);
			}
		}
		exchange(data, i + 1, right);
		return i + 1;
	}
	/**
	 * 优化过之后的划分算法
	 * @param data
	 * @param left
	 * @param right
	 * @return
	 */
	private int randomPartition(int data[],int left,int right){
		int random = (int) (Math.random() * (right - left)) + left;
		exchange(data, random, right);
		return partition(data, left, right);
	}
	/**
	 * 优化过之后的快排逻辑
	 * @param data
	 * @param startIndex
	 * @param endIndex
	 */
	private void randomRecursiveQuickSort(int data[],int startIndex,int endIndex){
		if(startIndex > endIndex)
			return;
		int middle = randomPartition(data, startIndex, endIndex);
		randomRecursiveQuickSort(data,startIndex,middle - 1);
		randomRecursiveQuickSort(data, middle + 1, endIndex);
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
}
/**
 * 堆排序算法
 * 特点：非稳定但不需要额外空间的算法，最好和最坏情况皆为O（nlgn）；
 * @ClassName: HeapSort 
 * @author 陈苗 
 * @date 2016年3月26日 下午4:20:23
 */
class HeapSort implements Printer {
	/**
	 * 堆排序调用方法
	 */
	public void heapSort() {
		int array[] = {2,4,1,3,5,7};
		buildMaxHeap(array);
		for(int i = array.length - 1;i >= 1;--i){
			//交换数组第一个元素与最后一个元素，逻辑上可从数组第一个元素处可取走最大元素
			array[0] = array[i] ^ array[0];
			array[i] = array[i] ^ array[0];
			array[0] = array[i] ^ array[0];
			//重新调整堆以满足大顶堆的性质
			maxHeapify(array,i,0);
		}
		printData(array);
	}
	/**
	 * 将堆按照大顶堆性质化
	 * @param data 数据源
	 * @param heapSize 堆元素的个数
	 * @param index 位置
	 */
	private void maxHeapify(int data[],int heapSize,int index){
		int largest = index;
		if(leftChild(index) <= heapSize && data[largest] < data[leftChild(index)])
			largest = leftChild(index);
		else if(rightChild(index) <= heapSize && data[largest] < data[rightChild(index)])
			largest = rightChild(index);
		if(largest != index){
			int temp = data[largest];
			data[largest] = data[index];
			data[index] = temp;
			maxHeapify(data,heapSize,largest);
		}
	}
	/**
	 * 创建一个大顶堆
	 * @param data 数据源
	 */
	private void buildMaxHeap(int data[]){
		if(data == null || data.length <= 1)
			return;
		for(int i = data.length / 2;i >= 0;--i)
			maxHeapify(data,data.length,i);
	}
	/**
	 * 获取左孩子节点索引
	 * @param index 父节点索引
	 * @return 左孩子索引
	 */
	private int leftChild(int index){
		return 2 * index + 1;
	}
	/**
	 * 获取右孩子节点索引
	 * @param index 父节点索引
	 * @return 右孩子索引
	 */
	private int rightChild(int index){
		return 2 * index + 2;
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
}
/**
 * 计数排序算法
 * 特点：计数排序算法为稳定但需要额外空间的算法，最坏和最好情况皆为O（n+k）；其中当k = O（n）时，时间复杂度为：O(n)；
 * @ClassName: CounterSort 
 * @author 陈苗 
 * @date 2016年3月27日 下午3:16:31
 */
class CounterSort implements Printer {
	/**
	 * 计数排序调用
	 */
	public void counterSort(){
		int array[] = {2,4,1,3,5,7};
		countSort(array,7);
		printData(array);
	}
	/**
	 * 计数排序算法逻辑
	 * @param data 数据源
	 * @param maxValue 最大值
	 */
	private void countSort(int[] data,int maxValue){
		int length = data.length;
		int[] B = new int[length];
		int[] C = new int[maxValue + 1];
		for(int i = 0;i <= maxValue;i++)
			C[i] = 0;//计数数组元素都初始化为0
		for(int i = 0;i < length;i++)
			C[data[i]]++;//对原数组元素分别计数
		for(int i = 1;i <= maxValue;i++)
			C[i] = C[i] + C[i - 1];
		for(int i = length - 1;i >= 0;--i)
		{
			B[C[data[i]] - 1] = data[i];
			C[data[i]]--;
		}
		for(int i = 0;i < length;i++)
			data[i] = B[i];
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
}
/**
 * 希尔排序算法
 * @ClassName: ShellSort 
 * @author 陈苗 
 * @date 2016年3月27日 下午8:54:26
 */
class ShellSort implements Printer {
	/**
	 * 希尔排序算法调用
	 */
	public void shellSort(){
		int array[] = {2,4,1,3,5,7};
		shellSort(array, array.length);
		printData(array);
	}
	/**
	 * 希尔排序算法逻辑
	 * @param data
	 * @param length
	 */
	private void shellSort(int data[],int length){
		int i,j,gap;//其中，gap代表步长
		for(gap = length / 2;gap > 0;gap /= 2){
			for(i = 0;i < gap;i++){
				for(j = i + gap;j < length;j += gap){
					if(data[j] < data[j - gap]){
						int temp = data[j];
						int k = j - gap;
						while(k >= 0 && data[k] > temp){
							data[k + gap] = data[k];
							k -= gap;
						}
						data[k + gap] = temp;
					}
				}
			}
		}
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
	
}
/**
 * 基数排序
 * 特点：稳定的排序，但需要依托其他排序方式的排序算法；
 * @ClassName: RadixSort 
 * @author 陈苗 
 * @date 2016年3月28日 下午4:30:00
 */
class RadixSort implements Printer {
	/**
	 * 基数排序调用
	 */
	public void radixSort(){
		int array[] = {24,400,11,35,5,78};
		radixSort(array, 10, 3);
		printData(array);
	}
	/**
	 * 基数调用算法逻辑
	 * @param data 数组
	 * @param radix 基数
	 * @param count 最大位数
	 */
	private void radixSort(int data[],int radix,int count){
		int temp[] = new int[data.length];//缓存数组
		int buckets[] = new int[radix];//buckets数组定义了桶，且用于记录待排元素的信息
		for(int i = 1,rate = 1;i <= count;i++){
			Arrays.fill(buckets, 0);//重置计数数组，开始统计下一个关键字
			System.arraycopy(data, 0, temp, 0, data.length);//将data中的元素完全复制到临时数组中
			//计算每个待排数据的子关键字
			for(int j = 0;j < data.length;j++){
				int subKey = (temp[j] / rate) % radix;
				buckets[subKey]++;
			}
			for(int j = 1;j < radix;j++)
				buckets[j] = buckets[j] + buckets[j - 1];
			for(int m = data.length - 1;m >= 0;m--){
				int subKey = (temp[m] / rate) % radix;
				data[--buckets[subKey]] = temp[m];
			}
			rate *= radix;
		}
	}
	@Override
	public void printData(int[] data) {
		System.out.println("排序后数据为：");
		for(int value : data)
			System.out.print(value + " ");
		System.out.println();
	}
}
/**
 * 排序算法测试类
 * @ClassName: SortAlgothrimTest 
 * @author 陈苗 
 * @date 2016年3月26日 上午9:49:09
 */
public class SortAlgothrimTest {
	@Test
	public void insertSort(){
		InsertSort insertSort = new InsertSort();
		insertSort.insertSort();
	}
	@Test
	public void bubbleSort(){
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.bubbleSort();
		bubbleSort.betterBubbleSort();
	}
	@Test
	public void selectSort(){
		SelectSort selectSort = new SelectSort();
		selectSort.selectSort();
	}
	@Test
	public void mergeSort(){
		MergeSort mergeSort = new MergeSort();
		mergeSort.mergeSort();
	}
	@Test
	public void quickSort(){
		QuickSort quickSort = new QuickSort();
		quickSort.quickSort();
		quickSort.randomQuickSort();
	}
	@Test
	public void heapSort(){
		HeapSort heapSort = new HeapSort();
		heapSort.heapSort();
	}
	@Test
	public void countSort(){
		CounterSort counterSort = new CounterSort();
		counterSort.counterSort();
	}
	@Test
	public void shellSort(){
		ShellSort shellSort = new ShellSort();
		shellSort.shellSort();
	}
	@Test
	public void radixSort(){
		RadixSort radixSort = new RadixSort();
		radixSort.radixSort();
	}
}
