package cn.xidian.algorithm;

import org.junit.Test;

/**
 * 0-1背包问题类
 * 其中，c[i,w]表示到第i个元素为止，在限制总重量为w的情况下所能获得的最优解
 *                    | 0                            if i = 0 或者 w = 0,
 * 最优子结构 c[i,w] = | c[i-1,w]                     if Wi > w
 *                    | max(Vi+c[i-1,w-Wi],c[i-1,w]) if i > 0 且 w > Wi
 */
class PacketProblem01 {
    private int[] weight;
    private int[] value;
    private int[][] cost;
    private int length;
    private int capacity;

    /**
     * 构造函数
     *
     * @param _weight   物品的重量数组
     * @param _value    物品对应的价值数组
     * @param _length   物品的种类
     * @param _capacity 背包的容量
     */
    public PacketProblem01(int[] _weight, int[] _value, int _length, int _capacity) {
        cost = new int[_length + 1][_capacity + 1];
        length = _length;
        weight = new int[_length + 1];
        value = new int[_length + 1];
        weight[0] = 0;
        value[0] = 0;
        for (int i = 0; i < _length; i++) {
            value[i + 1] = _value[i];
            weight[i + 1] = _weight[i];
        }
        capacity = _capacity;
    }

    /**
     * 外部调用的处理方法
     */
    public int solve() {
        for (int i = 0; i < length + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (i == 0 || j == 0)
                    cost[i][j] = 0;
                else if (weight[i] > j)
                    cost[i][j] = cost[i - 1][j];
                else
                    cost[i][j] = Math.max(cost[i - 1][j - weight[i]] + value[i],cost[i - 1][j]);
            }
        }
        return cost[length][capacity];
    }
}

/**
 * 分数背包
 * 要求传入的参数为按照性价比从高到低的顺序
 */
class PacketPoint {
    private int[] weight;
    private int[] value;
    private int capacity;
    private int length;

    /**
     * 构造函数
     * @param weight
     * @param value
     * @param capacity
     * @param length
     */
    public PacketPoint(int[] weight, int[] value, int capacity, int length) {
        this.weight = weight;
        this.value = value;
        this.capacity = capacity;
        this.length = length;
    }

    public double solve() {
        int sum = 0,i;
        double maxValue = 0.0;
        for (i = 0; i < length; i++) {
            if (sum + weight[i] < capacity) {
                sum += weight[i];
                maxValue += value[i];
            } else
                break;
        }
        if (i < length && sum < capacity) {
            maxValue += (double)(capacity - sum) / weight[i] * value[i];
        }
        return maxValue;
    }
}

/**
 * 文件描述：背包问题解决
 * NOTE：
 * 背包问题可以分为两类，即0-1背包问题和分数背包问题。
 * 0-1背包问题的解决方式是动态规划；
 * 分数背包问题的解决方式是贪心算法；
 * 创建作者：陈苗
 * 创建时间：2016年6月24日 16:17
 */
public class PacketProblemTest {
    @Test
    public void testPacket01() {
        int[] weight = {1,2,3};
        int[] value = {60,100,120};
        System.out.println(new PacketProblem01(weight,value,3,5).solve());
    }
    @Test
    public void testPointPacket() {
        int[] weight = {1,2,3};
        int[] value = {60,100,120};
        System.out.println(new PacketPoint(weight,value,5,3).solve());
    }
}
