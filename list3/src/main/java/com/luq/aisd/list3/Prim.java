package com.luq.aisd.list3;

public class Prim extends MST {
  private float[] keys;

  public Prim(Graph graph) {
    super(graph);
    keys = new float[graph.getV()];
  }

  public void prim(int root) {
    Boolean[] inMST = new Boolean[graph.getV()];
    PriorityQueue queue = new PriorityQueue();

    for (int i = 0; i < graph.getV(); i++) {
      keys[i] = Float.MAX_VALUE;
      queue.insert(i, keys[i]);
      inMST[i] = false;
    }

    keys[root] = 0;
    queue.priority(root, keys[root]);
    parent[root] = null;

    int sumOfWeights = 0;
    while (!queue.isEmpty()) {
      int u = queue.top().getKey();
      inMST[u] = true;
      for (Edge edge : graph.getAdj().get(u)) {
        int v = edge.getV();
        if (queue.contains(v) && !inMST[v] && edge.getWeight() < keys[v]) {
          parent[v] = edge.getU();
          keys[v] = edge.getWeight();
          queue.priority(v, keys[v]);
          System.out.println("u: " + u + ", v: " + v + ", w: " + edge.getWeight());
          sumOfWeights += edge.getWeight();
        }
      }
      queue.pop();
    }
    System.out.println("Sum of weights: " + sumOfWeights);
  }
}
