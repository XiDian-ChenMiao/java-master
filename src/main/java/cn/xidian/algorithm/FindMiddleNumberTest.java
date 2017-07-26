package cn.xidian.algorithm;

/**
 * 寻找两个有序数组的中位数
 * @ClassName: FindMiddleNumberTest 
 * @author 陈苗 
 * @date 2016年5月11日 上午11:31:31
 */
public class FindMiddleNumberTest {
	private Integer[] a;
	private Integer[] b;
	/**
	 * 构造函数
	 * @param a 有序数组
	 * @param b 有序数组
	 */
	public FindMiddleNumberTest(Integer[] a, Integer[] b) {
		this.a = a;
		this.b = b;
	}
	/**
	 * 寻找中位数的方法
	 * @return 找到的中位数
	 */
	public Float findMiddleNumber() {
		int totalCount = a.length + b.length;
		if(totalCount % 2 == 1) {//如果两个数组的长度和为奇数
			int middleLoc = totalCount / 2 + 1;
			return findKthElm(a,0,a.length - 1,b,0,b.length - 1,middleLoc) * 1.0f;
		} else {//如果两个数组的长度和为偶数
			int middlePrevious = totalCount / 2;
			int middleNext = middlePrevious + 1;
			return (findKthElm(a,0,a.length - 1,b,0,b.length - 1,middlePrevious) + findKthElm(a,0,a.length - 1,b,0,b.length - 1,middleNext)) * 1.0f / 2;
		}
	}
    /**
     * 在两个有序数组中找寻第k大的数
     * @param A
     * @param aBeg
     * @param aEnd
     * @param B
     * @param bBeg
     * @param bEnd
     * @param k
     * @return
     */
    public int findKthElm(Integer A[], int aBeg, int aEnd, Integer B[], int bBeg, int bEnd, int k)
    {
        if (aBeg > aEnd)
            return B[bBeg + k - 1];
        if (bBeg > bEnd)
            return A[aBeg + k - 1];
        //取中间位置
        int aMid = aBeg + (aEnd - aBeg)/2;
        int bMid = bBeg + (bEnd - bBeg)/2;
        //从A和B的开始位置到两个数组中间位置的元素个数
        int halfLen = aMid - aBeg + bMid - bBeg + 2;
        if (A[aMid] < B[bMid])
        {
            if (halfLen > k)
            {
                // 此时在合并的数组中A[aBeg...aMid]和元素一定在B[bMid]的左侧，
                // 即此时第k大的元素一定比B[bMid]这个元素小（严格来说不大于）
                // 故以后没有必要搜索 B[bMid...bEnd]这些元素
                return findKthElm(A, aBeg, aEnd, B, bBeg, bMid - 1, k);
            }
            else
            {
                // 此时在合并的数组中A[aBeg...aMid]元素一定在B[bMid]的左侧，
                // 所以前K个元素中一定包含A[aBeg...aMid]（可以使用反证法来证明这点）。
                // 但是无法判断A[amid+1...aEnd]与B[bBeg...bEnd]之间的关系，帮需要对他们进行判断
                // 此时K就剩下除去A[aBeg...aMid]这些元素，个数为k - (aMid - aBeg + 1)
                return findKthElm(A, aMid + 1, aEnd, B, bBeg, bEnd, k - (aMid - aBeg + 1));
            }
        }
        else
        {
            if (halfLen > k)
                return findKthElm(A, aBeg, aMid - 1, B, bBeg, bEnd, k);
            else
                return findKthElm(A, aBeg, aEnd, B, bMid + 1, bEnd, k - (bMid - bBeg + 1));
        }
    }
    /**
	 * 主函数调用
	 * @param args
	 */
	public static void main(String[] args) {
        Integer[] a = {1, 2, 4, 6, 6, 9};
        Integer[] b = {2, 3, 5, 6, 6, 8};
        FindMiddleNumberTest test = new FindMiddleNumberTest(a, b);
        for (int i = 1; i <= a.length + b.length; ++i)
            System.out.println("第" + i + "小的数为：" + test.findKthElm(a, 0, a.length - 1, b, 0, b.length - 1, i));
        System.out.println("中位数为：" + test.findMiddleNumber());
    }
}
