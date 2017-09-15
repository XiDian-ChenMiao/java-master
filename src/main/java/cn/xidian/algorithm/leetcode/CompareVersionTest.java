package cn.xidian.algorithm.leetcode;

/**
 * 文件描述：比较版本号程序
 * 创建作者：陈苗
 * 创建时间：2017/9/12 21:35
 */
public class CompareVersionTest {
    /**
     * 比较版本号
     * @param a
     * @param b
     * @return
     */
    public int compareVersion(String a, String b) {
        if (!a.contains(".") && !b.contains("."))
            return Integer.compare(Integer.valueOf(a), Integer.valueOf(b));
        else if (a.contains(".") && !b.contains(".")) {
            return justify(a, b);
        } else if (!a.contains(".") && b.contains(".")) {
            return -1 * justify(b, a);
        } else {
            return compare(a, b);
        }
    }

    private int compare(String a, String b) {
        String[] ainfo = a.split("\\.");
        String[] binfo = b.split("\\.");
        int minlen = Math.min(ainfo.length, binfo.length);
        for (int i = 0; i < minlen; i++) {
            int va = Integer.valueOf(ainfo[i]);
            int vb = Integer.valueOf(binfo[i]);
            if (va > vb)
                return 1;
            else if (va < vb)
                return -1;
        }
        if (ainfo.length != binfo.length) {
            if (ainfo.length > binfo.length) {
                for (int i = minlen; i < ainfo.length; i++) {
                    if (Integer.valueOf(ainfo[i]) != 0)
                        return 1;
                }
            } else {
                for (int i = minlen; i < binfo.length; i++) {
                    if (Integer.valueOf(binfo[i]) != 0)
                        return -1;
                }
            }
        }
        return 0;
    }

    private int justify(String a, String b) {
        String[] info = a.split("\\.");
        if (Integer.valueOf(info[0]) == Integer.valueOf(b)) {
            for (int i = 1; i < info.length; i++) {
                if (Integer.valueOf(info[i]) != 0)
                    return 1;
            }
            return 0;
        } else
            return Integer.compare(Integer.valueOf(info[0]), Integer.valueOf(b));
    }

    public static void main(String[] args) {
        System.out.println(new CompareVersionTest().compareVersion("10.6.5", "10.6"));
    }
}
