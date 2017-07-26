package cn.xidian.algorithm;

import java.util.Stack;

/**
 * 文件描述：图的拓扑排序测试类（基于深度优先遍历）
 * NOTE：
 *  （1）复杂度同DFS一致，即O(V+E)；
 *  （2）首先保证图是有向无环图，判断图是DAG可以使用基于DFS的算法，复杂度为O(V+E)；
 *  拓扑排序的唯一性：哈密顿路径是指一条能够对图中所有顶点正好访问一次的路径。
     *  我们先使用拓扑排序对图中的顶点进行排序。
     *  排序后，对每对相邻顶点进行检测，看看是否存在先后关系。
     *  如果每对相邻顶点都存在着一致的先后关系(在有向图中，这种先后关系以有向边的形式体现，即查看相邻顶点对之间是否存在有向边)。
     *  那么就可以确定该图中存在哈密顿路径了，反之则不存在。
 * 创建作者：陈苗
 * 创建时间：2016年6月16日 9:51
 */
public class GraphTopologicalSortTest {
    private int[] vertexStatus;
    private int[][] edgeInfo;
    private Stack<Integer> result;
    private int vertexCount;
    /**
     * 构造函数
     * @param vertexCount 节点个数
     */
    public GraphTopologicalSortTest(int vertexCount) {
        this.vertexCount = vertexCount;
        //初始化节点状态，如果被访问过则置为1，如果没有被访问过，则置为0，默认为0；
        vertexStatus = new int[vertexCount];
        //初始化边的信息，如果edgeInfo[i][j]的值为0，则表示编号为i和j的顶点之间不存在边；
        edgeInfo = new int[vertexCount][];
        for (int i = 0; i < vertexCount; i++) {
            edgeInfo[i] = new int[vertexCount];
        }
        //初始化存储拓扑排序结果的栈
        result = new Stack<Integer>();
    }

    /**
     * 建立图的函数
     * @param i 边的起始节点编号
     * @param j 边的结束节点编号
     */
    public void createGraph(int i,int j) {
        edgeInfo[i][j] = 1;
    }

    /**
     * 打印图的函数
     */
    public void displayGraph() {
        for (int i = 0; i < this.vertexCount; i++) {
            for (int j = 0; j < this.vertexCount; j++) {
                System.out.print(edgeInfo[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 调用深度优先遍历的接口函数
     */
    private void dfs() {
        for (int i = 0; i < vertexCount; i++) {
            if (vertexStatus[i] == 0)
                dfsCore(i);
        }
    }

    /**
     * 深度优先遍历的核心函数
     * @param startIndex
     */
    private void dfsCore(int startIndex) {
        vertexStatus[startIndex] = 1;
        for (int i = 0; i < vertexCount; i++) {
            if (edgeInfo[startIndex][i] == 1 && vertexStatus[i] == 0)
                dfsCore(i);
        }
        result.push(startIndex);//在即将退出dfs方法的时候，将当前顶点添加到结果集中
    }

    /**
     * 获取拓扑排序结果
     */
    public void getTopologicalResult() {
        System.out.println("拓扑排序结果：");
        dfs();
        Object[] data = result.toArray();
        for (int i = data.length - 1; i >= 1 ; i--) {
            System.out.print(data[i] + "->");
        }
        System.out.print(data[0]);
    }
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        GraphTopologicalSortTest test = new GraphTopologicalSortTest(13);
        test.createGraph(8,7);
        test.createGraph(7,6);
        test.createGraph(0,6);
        test.createGraph(0,5);
        test.createGraph(0,1);
        test.createGraph(2,0);
        test.createGraph(2,3);
        test.createGraph(3,5);
        test.createGraph(6,9);
        test.createGraph(6,4);
        test.createGraph(5,4);
        test.createGraph(9,10);
        test.createGraph(9,11);
        test.createGraph(9,12);
        test.createGraph(11,12);
        System.out.println("图的结构为：");
        test.displayGraph();
        test.getTopologicalResult();
    }
}
