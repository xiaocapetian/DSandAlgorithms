package com.itheima.datastructure.graph;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <h3>迪克斯特拉 单源最短路径算法</h3>
 */
public class DijkstraPriorityQueue {
    public static void main(String[] args) {
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

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);

        dijkstra(graph, v1);
    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
        /*
         * 注意:这里使用了优先队列,访问修改距离后又把节点加进去,(但是原来里面就有的那个不会删除,其实会造成重复的,因为必须重新添加才会重新排序,
         * 所以重复是必要的,且每轮都找头节点,然后删,所以即使重复就会直接删掉怕,也不会有影响
         */
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        source.dist = 0;
        for (Vertex v : graph) {
            queue.offer(v);
        }

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            // 3. 找一个节点作为 当前顶点
            Vertex curr = queue.peek();
            System.out.println("找一个节点"+curr+"作为 当前顶点");
            System.out.println("最初"+queue);
            // 4. 更新当前顶点邻居距离
            if(!curr.visited) {
                updateNeighboursDist(curr, queue);
                curr.visited = true;
            }
            // 5. 移除当前顶点
            queue.poll();
            System.out.println("移除头节点"+queue);
        }

        for (Vertex v : graph) {
            System.out.println(v);
        }
    }

    private static void updateNeighboursDist(Vertex curr, PriorityQueue<Vertex> queue) {
        for (Edge edge : curr.edges) {
            Vertex n = edge.linked;
            System.out.println("本轮更新的是"+n);
            if (!n.visited) {
                int dist = curr.dist + edge.weight;
                if (dist < n.dist) {
                    n.dist = dist;
                    n.prev = curr;
                    queue.offer(n);
                    System.out.println(queue);
                }
            }
        }
    }

}
