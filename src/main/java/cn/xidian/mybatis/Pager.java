package cn.xidian.mybatis;
import java.util.List;

/**
 * 文件描述：分页接收类
 * 创建作者：陈苗
 * 创建时间：2016年7月2日 17:02
 */
public class Pager<E> {
    /**
     * 当前页的编号
     */
    private int pageNum;
    /**
     * 每页显示的信息记录数目
     */
    private int pageSize;
    /**
     * 分页的行的起始编号
     */
    private int startRow;
    /**
     * 分页的行的终止编号
     */
    private int endRow;
    /**
     * 总的记录数目
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 获取的分页的记录信息集合
     */
    private List<E> result;

    public Pager(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }
}