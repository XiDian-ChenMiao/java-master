package cn.other;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * 项目名称：DailyJavaTest
 * 类名称：Pager
 * 类描述：Java分页类
 * 创建时间：2015年12月2日 下午10:26:09
 * 创建人： 陈苗
 */
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 2793668811465864229L;
	//每页显示的记录数量
	private int pageSize; 
	//当前页的页号
	private int currentPage; 
	//总的记录条数
	private int totalRecord; 
	//总页数
	private int totalPage;
	//存储的信息
	private List<T> dataList; 
	/**
	 * 为List的subList方法分页准备的构造函数
	 * @param pageNum 页号
	 * @param pageSize 页面显示记录数量
	 * @param sourceList 查找到的所有的记录集合
	 */
	public Pager(int pageNum, int pageSize, List<T> sourceList){
		if(sourceList == null || sourceList.isEmpty()){
			return;
		}
		this.totalRecord = sourceList.size();
		this.pageSize = pageSize;
		this.totalPage = this.totalRecord / this.pageSize;
		if(this.totalRecord % this.pageSize !=0){
			this.totalPage = this.totalPage + 1;
		}
		this.currentPage = this.totalPage < pageNum ?  this.totalPage : pageNum;
		int fromIndex	= this.pageSize * (this.currentPage -1);
		int toIndex  = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
		this.dataList = sourceList.subList(fromIndex, toIndex);
	}
	/**
	 * 默认构造函数
	 */
	public Pager(){}
	/**
	 * 全部属性构成的构造函数
	 * @param pageSize 当前页上显示的记录数量
	 * @param currentPage 当前页号
	 * @param totalRecord 总记录数
	 * @param dataList 查找到的记录集合
	 */
	public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,	List<T> dataList) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
