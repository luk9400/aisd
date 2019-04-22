package com.luq.aisd.list3;

public class MST {
  protected int[] rank;
  protected Integer[] parent;
  protected WeightGraph A;
  protected WeightGraph graph;

  protected MST(WeightGraph graph) {
    this.graph = graph;
    rank = new int[graph.getV()];
    parent = new Integer[graph.getV()];
  }

  private void link(int x, int y) {
    if (rank[x] > rank[y]) {
      parent[y] = x;
    } else {
      parent[x] = y;
      if (rank[x] == rank[y]) {
        rank[y]++;
      }
    }
  }

  protected void makeSet(int x) {
    parent[x] = x;
    rank[x] = 0;
  }

  protected int findSet(int x) {
    if (x != parent[x]) {
      parent[x] = findSet(parent[x]);
    }
    return parent[x];
  }

  protected void union(int x, int y) {
    link(findSet(x), findSet(y));
  }
}
