package com.itheima.datastructure.graph;

import java.util.List;

/**
 * <h3>Bellman-Ford 算法，可以处理负边</h3>
 * l784v3 l784v4这2题就这么做的
 */
public class G7_2_BellmanFord {
    /**
     *
     * @param graph 所有点的集合
     * @param source 出发点
     */
    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;
        int size = graph.size();
        // 1. 进行 顶点个数-1 轮处理 (n个节点就循环n-1次)
        for (int i = 0; i < size - 1; i++) {
            // 2. 遍历所有的边(在这种情况下,其实不用考虑怎么存储图了,很多题目输入不就是输入每个边吗!
            // 比如说输入数组 `int[] flights` ， `flights[i] = [fromi, toi, pricei]` ，表示起点,终点,距离   那我直接遍历这个数组就完了,很方便啊)
            // 2.1 遍历每个节点
            for (Vertex start : graph) {
                //2.2 遍历每个节点所有的边
                for (Edge edge : start.edges) {
                    // 3. 处理每一条边
                    Vertex end = edge.linked;
                    if (start.dist != Integer.MAX_VALUE && start.dist + edge.weight < end.dist) {
                        //起点距离+权重<终点距离
                        end.dist = start.dist + edge.weight;
                        end.prev = start;
                    }
                }
            }
        }
        for (Vertex v : graph) {
            System.out.println(v);
        }
    }
    public static void main(String[] args) {
        test1();
        // 负边情况
        /*Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 1));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);*/

        // 负环情况
       /* Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2));
        v2.edges = List.of(new Edge(v3, -4));
        v3.edges = List.of(new Edge(v4, 1), new Edge(v1, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);*/
    }
    private static void test1(){
        // 正常情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        List<Vertex> graph = List.of(v4, v5, v6, v1, v2, v3);
        bellmanFord(graph, v1);
    }

}
