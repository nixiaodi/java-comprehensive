package jiang.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/26
 * @Version 1.0
 */
public class GraphDemo {
    // 存储顶点集合
    private List<String> vertexList;

    // 存储图对应的邻接矩阵
    private int[][] edges;

    // 边的个数
    private int side;

    /**
     * 构造图
     * @param number 表示顶点的个数
     */
    public GraphDemo(int number) {
        edges = new int[number][number];
        vertexList = new ArrayList<>(number);
        // 初始化为0
        side = 0;
    }

    /**
     * 插入节点
     * @param vertex 节点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 节点 index
     * @param v2 节点 index
     * @param weight 权重
     */
    public void insertSide(int v1,int v2,int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        side++;
    }

    /**
     * 获取节点个数
     * @return
     */
    public int getVertexNum() {
        return vertexList.size();
    }

    /**
     * 获取边的个数
     * @return
     */
    public int getSide() {
        return side;
    }

    /**
     * 获取两个节点对应边的权重
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        //for (int i = 0; i < edges.length; i++) {
        //    for (int j = edges[i].length - 1; j >= 0; j--) {
        //        System.out.print(edges[i][j]);
        //        System.out.print("\t");
        //    }
        //    System.out.println();
        //}
        for (int[] edge : edges) {
            System.out.println(Arrays.asList(edge).toString());
        }
    }

    public static void main(String[] args) {

    }
}
