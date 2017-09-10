package cn.xidian.algorithm.course;

/**
 * 文件描述：关于图的深度优先遍历
 * 创建作者：陈苗
 * 创建时间：2016年6月15日 16:10
 */
public class GraphDFSTest {
    private int[] vertexStatus;
    private int[][] edgeInfo;
    private int vertexCount;
    /**
     * 构造函数
     * @param vertexCount 节点个数
     */
    public GraphDFSTest(int vertexCount) {
        this.vertexCount = vertexCount;
        //初始化节点状态，如果被访问过则置为1，如果没有被访问过，则置为0，默认为0；
        vertexStatus = new int[vertexCount];
        //初始化边的信息，如果edgeInfo[i][j]的值为0，则表示编号为i和j的顶点之间不存在边；
        edgeInfo = new int[vertexCount][];
        for (int i = 0; i < vertexCount; i++) {
            edgeInfo[i] = new int[vertexCount];
        }
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
    public void dfs() {
        System.out.println("深度优先遍历：");
        for (int i = 0; i < vertexCount; i++) {
            dfsCore(i);
        }
        System.out.println();
    }

    /**
     * 深度优先遍历的核心函数
     * @param startIndex
     */
    private void dfsCore(int startIndex) {
        if (vertexStatus[startIndex] != 0)
            return;
        System.out.print(startIndex + " ");
        vertexStatus[startIndex] = 1;
        for (int i = 0; i < vertexCount; i++) {
            if (edgeInfo[startIndex][i] == 1)
                dfsCore(i);
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        GraphDFSTest test = new GraphDFSTest(5);
        test.createGraph(0,3);
        test.createGraph(0,4);
        test.createGraph(3,1);
        test.createGraph(4,1);
        test.createGraph(3,2);
        System.out.println("图的结构为：");
        test.displayGraph();
        test.dfs();
    }
}
