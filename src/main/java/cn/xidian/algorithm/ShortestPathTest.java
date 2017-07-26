package cn.xidian.algorithm;

import org.junit.Test;

/**
 * 最短路径策略基类
 */
abstract class ShortestPathStrategy {
    protected int graph[][];
    protected int start;
    protected ShortestPathStrategy() {}
    protected ShortestPathStrategy(int[][] graph,int start) {
        this.graph = graph;
        this.start = start;
    }
    protected abstract void shortestPath();
}
/**
 * 迪杰斯特拉最短路径算法类
 * NOTE:其运行时间为O(V²)
 */
class DijkstraAlgothrim extends ShortestPathStrategy{
    protected DijkstraAlgothrim(int[][] graph, int start) {
        super(graph, start);
    }

    @Override
    protected void shortestPath() {
        int nodeCount = graph.length;
        int[] result = new int[nodeCount];//从起始节点到其他节点的最短路径值
        boolean[] visited = new boolean[nodeCount];//节点是否被访问过的判断数组
        //初始化操作
        for (int i = 0; i < nodeCount; i++) {
            result[i] = (start == i) ? 0 : ShortestPathTest.MAX_VALUE;
            visited[i] = (start == i) ? true : false;
        }

        for (int count = 1; count < nodeCount; count++) {
            int loc = -1;//选出一个距离起始顶点start最近的未标记顶点
            int dmin = Integer.MAX_VALUE;
            for (int i = 0; i < nodeCount; i++) {
                if(visited[i] == false && graph[start][i] < dmin) {
                    dmin = graph[start][i];
                    loc = i;
                }
            }
            result[loc] = dmin;
            visited[loc] = true;
            for (int i = 0; i < nodeCount; i++) {
                if (visited[i] == false && graph[loc][i] != ShortestPathTest.MAX_VALUE) {
                    if(graph[start][i] == ShortestPathTest.MAX_VALUE || dmin + graph[loc][i] < graph[start][i]) {
                        graph[start][i] = dmin + graph[loc][i];
                    }
                }
            }
        }

        System.out.println(start + "节点到其他节点的最短路径分别为：");
        for (int i = 0; i < nodeCount; i++) {
            System.out.println(start + "到" + i + "节点的最短路径为：" + result[i]);
        }
    }
}
/**
 * 贝尔曼福特最短路径算法类
 */
class BellmanFordAlgothrim extends ShortestPathStrategy{
    private boolean hasNegativeCircle = false;//是否存在负环的标志
    private int[] result;//从起始节点到其他节点的最短路径值
    public BellmanFordAlgothrim(int vertexCount,int start) {
        super();
        graph = new int[vertexCount][vertexCount];
        this.start = start;
        initizeGraph();
    }
    /**
     * 初始化图
     */
    private void initizeGraph() {
        int vertexCount = graph.length;
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
        graph[start][start] = 0;
    }

    /**
     * 创建边的函数
     * @param start 边的起点
     * @param end 边的终点
     * @param weight 边的权重
     */
    public void createGraph(int start,int end,int weight) {
        graph[start][end] = weight;
        graph[end][start] = weight;
    }

    /**
     * 外部调用是否存在负环路的接口函数
     * NOTE:其运行时间为O(VE)
     * @return
     */
    public boolean hasNegativeCircle() {
        return hasNegativeCircle;
    }
    @Override
    protected void shortestPath() {
        int vertexCount = graph.length;
        result = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++)
            result[i] = (start == i) ? 0 : ShortestPathTest.MAX_VALUE;

        for (int i = 1; i < vertexCount; i++) {/*执行V-1次松弛操作*/
            for (int j = 0; j < vertexCount; j++) {
                for (int k = 0; k < vertexCount; k++) {
                    if (graph[j][k] != Integer.MAX_VALUE)
                        relax(result,j,k);
                }
            }
        }

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (graph[i][j] != Integer.MAX_VALUE) {
                    if (result[i] + graph[i][j] < result[j])
                        hasNegativeCircle = true;
                }
            }
        }
    }

    /**
     * 打印结果函数
     */
    public void printResult() {
        int nodeCount = this.graph.length;
        shortestPath();
        System.out.println(start + "节点到其他节点的最短路径分别为：");
        for (int i = 0; i < nodeCount; i++) {
            System.out.println(start + "到" + i + "节点的最短路径为：" + result[i]);
        }
    }
    /**
     * 松弛操作
     * @param result
     * @param start
     * @param end
     */
    private void relax(int[] result,int start,int end) {
        if (result[start] + graph[start][end] < result[end])
            result[end] = result[start] + graph[start][end];
    }
}
/**
 * 文件描述：最短路径问题
 * 创建作者：陈苗
 * 创建时间：2016年5月23日 19:10
 */
public class ShortestPathTest {
    public static final int MAX_VALUE = 9999999;
    private static int[][] data = {
        {MAX_VALUE,10,MAX_VALUE,30,100},
        {10,MAX_VALUE,50,MAX_VALUE,MAX_VALUE},
        {MAX_VALUE,50,MAX_VALUE,20,10},
        {30,MAX_VALUE,20,MAX_VALUE,60},
        {100,MAX_VALUE,10,60,MAX_VALUE},
    };
    /**
     * 测试迪杰斯特拉最短路径算法
     */
    @Test
    public void testDijkstra() {
        ShortestPathStrategy strategy = new DijkstraAlgothrim(data,0);
        strategy.shortestPath();
    }
    /**
     * 测试贝尔曼福特最短路径算法
     */
    @Test
    public void testBellmanFord() {
        BellmanFordAlgothrim bellmanFordAlgothrim = new BellmanFordAlgothrim(5,0);
        bellmanFordAlgothrim.createGraph(0,3,30);
        bellmanFordAlgothrim.createGraph(0,4,100);
        bellmanFordAlgothrim.createGraph(0,1,10);
        bellmanFordAlgothrim.createGraph(1,2,50);
        bellmanFordAlgothrim.createGraph(2,4,10);
        bellmanFordAlgothrim.createGraph(2,3,20);
        bellmanFordAlgothrim.createGraph(4,3,60);
        if (bellmanFordAlgothrim.hasNegativeCircle()) {
            System.out.println("图中存在负环路");
        } else {
            bellmanFordAlgothrim.printResult();
        }
    }
}
