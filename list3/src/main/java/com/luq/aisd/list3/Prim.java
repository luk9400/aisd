package com.luq.aisd.list3;

public class Prim extends MST {
  private float[] keys;

  public Prim(Graph graph) {
    super(graph);
    keys = new float[graph.getV()];
  }

  public void prim(int root) {
    PriorityQueue queue = new PriorityQueue();

    for (int i = 0; i < graph.getV(); i++) {
      keys[i] = Float.MAX_VALUE;
      queue.insert(i, keys[i]);
    }

    keys[root] = 0;
    queue.priority(root, keys[root]);
    parent[root] = null;

    while (!queue.isEmpty()) {
      int u = queue.pop().getKey();
      for (Edge edge : graph.getAdj().get(u)) {
        int v = edge.getV();
        if (queue.contains(v) && edge.getWeight() < keys[v]) {
          parent[v] = edge.getU();
          keys[v] = edge.getWeight();
          queue.priority(v, keys[v]);
        }
      }
    }
    printMST();
  }

  private void printMST() {
    float sumOfWeights = 0;
    for (int i = 0; i < graph.getV(); i++) {
      if (parent[i] != null) {
        float weight = graph.getEdgeWeight(parent[i], i);
        System.out.println("u: " + parent[i] + ", v: " + i + ", w: " + weight);
        sumOfWeights += weight;
      }
    }
    System.out.println("Sum of weights: " + sumOfWeights);
  }
}
