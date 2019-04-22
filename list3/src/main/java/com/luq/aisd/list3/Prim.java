package com.luq.aisd.list3;

public class Prim extends MST {
  private int[] keys;

  public Prim(Graph graph) {
    super(graph);
    keys = new int[graph.getV()];
  }

  public void prim(int root) {
    PriorityQueue queue = new PriorityQueue();
    for (int i = 0; i < graph.getV(); i++) {
      keys[i] = Integer.MAX_VALUE;
      queue.insert(i, keys[i]);
    }
    keys[root] = 0;
    queue.priority(root, keys[root]);
    parent[root] = null;
    int sumOfWeights = 0;
    while (!queue.isEmpty()) {
      int u = queue.top().getKey();
      for (Edge edge : graph.getVerticies().get(u)) {
        if (queue.contains(edge.getV()) && edge.getWeight() < keys[edge.getV()]) {
          parent[edge.getV()] = edge.getU();
          keys[edge.getV()] = edge.getWeight();
          queue.priority(edge.getV(), keys[edge.getV()]);
          System.out.println("u: " + u + ", v: " + edge.getV() + ", w: " + edge.getWeight());
          sumOfWeights += edge.getWeight();
        }
      }
      queue.pop();
    }
    System.out.println("Sum of weights: " + sumOfWeights);
  }
}
