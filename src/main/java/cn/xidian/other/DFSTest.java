package cn.xidian.other;

/**
 * 文件描述：子集和问题
 * 创建作者：陈苗
 * 创建时间：2017/8/30 11:46
 */
public class DFSTest {

    public int sum(boolean[] visited, int[] data) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            if (visited[i])
                sum += data[i];
        }
        return sum;
    }

    public void get(boolean[] visited, int[] data, int step, int sum, int k) {
        if (step == data.length) {
            if (sum(visited, data) == sum) {
                for (int i = 0; i < data.length; i++) {
                    if (visited[i])
                        System.out.print(data[i] + " ");
                }
                System.out.println();
            }
            return;
        }
        visited[step] = true;
        get(visited, data, step + 1, sum, k - 1);
        visited[step] = false;
        get(visited, data, step + 1, sum, k);
    }

    public static void main(String[] args) {
        int[] data = {1, 3, 4, 2, 6, 8, 7};
        boolean[] visited = new boolean[data.length];
        for (int i = 0; i < data.length; i++) {
            visited[i] = false;
        }
        new DFSTest().get(visited, data, 0, 8, 2);
    }
}
