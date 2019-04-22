package com.luq.aisd.list3;

import java.util.ArrayList;

public class Dijkstra {
  private ArrayList<Integer> set = new ArrayList<>();
  private PriorityQueue queue = new PriorityQueue();
  private Integer[] prev;
  private Integer[] distance;
  private Graph graph;

  public Dijkstra(Graph graph) {
    this.graph = graph;
    prev = new Integer[graph.getV()];
    distance = new Integer[graph.getV()];
  }

  public void dijkstra(int start) {
    initializeSingleSource(start);

    for (int i = 0; i < graph.getV(); i++) {
      queue.insert(i, distance[i]);
    }

    while (!queue.isEmpty()) {
      int u = queue.top().getKey();
      set.add(u);
      for (Edge edge : graph.getVerticies().get(u)) {
        relax(edge);
      }
      queue.pop();
      System.out.println("Id: " + u + ", weight: " + distance[u]);
    }
  }

  private void initializeSingleSource(int start) {
    for (int i = 0; i < graph.getV(); i++) {
      distance[i] = Integer.MAX_VALUE;
      prev[i] = null;
    }
    distance[start] = 0;
  }

  private void relax(Edge edge) {
    int u = edge.getU();
    int v = edge.getV();
    if (distance[v] > distance[u] + edge.getWeight()) {
      distance[v] = distance[u] + edge.getWeight();
      queue.priority(v, distance[v]);
      prev[v] = u;
    }
  }
}
