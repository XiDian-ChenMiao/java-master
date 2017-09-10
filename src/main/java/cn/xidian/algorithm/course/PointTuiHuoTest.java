package cn.xidian.algorithm.course;

import java.util.Scanner;

/**
 * 文件描述：给n个点，找出一个点，使这个点到其他所有点的距离之和最小，也就是求费马点；
 * 使用模拟退火算法
 * 创建作者：陈苗
 * 创建时间：2017/4/4 12:21
 */
public class PointTuiHuoTest {
    private final static int N = 1005;
    private final static double EPS = 1E-8;/*搜索停止条件阈值*/
    private final static int INF = Integer.MAX_VALUE;
    private final static double DELTA = 0.98;/*温度下降速度*/
    private final static int T = 100;/*初始化温度*/

    private final static Integer[] dx = new Integer[4];
    private final static Integer[] dy = new Integer[4];

    static {
        dx[0] = 0;
        dx[1] = 0;
        dx[2] = -1;
        dx[3] = 1;
        dy[0] = -1;
        dy[1] = 1;
        dy[2] = 0;
        dy[3] = 0;
    }

    private static class Point {
        double x;
        double y;
    }

    public static double dist(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public static double getSum(Point[] points, int n, Point t) {
        double ans = 0;
        while (n-- > 0)
            ans += dist(points[n], t);
        return ans;
    }

    public static double serach(Point[] points, int n) {
        Point s = points[0];
        double t = T;
        double ans = INF;
        while (t > EPS) {
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int i = 0; i < 4; ++i) {
                    Point z = new Point();
                    z.x = s.x + dx[i] * t;
                    z.y = s.y + dy[i] * t;
                    double tp = getSum(points, n, z);
                    if (ans > tp) {
                        ans = tp;
                        s = z;
                        flag = true;
                    }
                }
            }
            t *= DELTA;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; ++i) {
            points[i].x = scanner.nextDouble();
            points[i].y = scanner.nextDouble();
        }

        System.out.println(serach(points, n));
    }
}
