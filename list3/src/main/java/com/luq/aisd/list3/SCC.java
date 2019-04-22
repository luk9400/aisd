package com.luq.aisd.list3;

import java.util.ArrayList;
import java.util.Arrays;

public class SCC {
  private char[] color;
  private int[] d;
  private int[] f;
  private Integer[] prev;
  private int time;
  private Graph graph;

  public SCC(Graph graph) {
    this.graph = graph;
    color = new char[graph.getV()];
    d = new int[graph.getV()];
    f = new int[graph.getV()];
    prev = new Integer[graph.getV()];
  }

  private void dfsVisit(Graph graph, int u) {
    color[u] = 'g';
    d[u] = ++time;
    for (Edge edge : graph.getVerticies().get(u)) {
      if (color[edge.getV()] == 'w') {
        prev[edge.getV()] = u;
        dfsVisit(graph, edge.getV());
      }
    }
    color[u] = 'b';
    f[u] = ++time;
  }

  private void dfsVisitVerbose(Graph graph, int u) {
    color[u] = 'g';
    System.out.print(u + " ");
    d[u] = ++time;
    for (Edge edge : graph.getVerticies().get(u)) {
      if (color[edge.getV()] == 'w') {
        prev[edge.getV()] = u;
        dfsVisit(graph, edge.getV());
      }
    }
    color[u] = 'b';
    f[u] = ++time;
  }

  public void dfs(Graph graph) {
    for (int i = 0; i < graph.getV(); i++) {
      color[i] = 'w';
      prev[i] = null;
    }
    time = 0;
    for (int i = 0; i < graph.getV(); i++) {
      if (color[i] == 'w') {
        dfsVisit(graph, i);
      }
    }
  }

  private void dfsTopo(Graph graph, ArrayList<Integer> list) {
    for (int i = 0; i < graph.getV(); i++) {
      color[i] = 'w';
      prev[i] = null;
    }
    time = 0;
    for (int i = 0; i < graph.getV(); i++) {
      if (color[i] == 'w') {
        dfsVisit(graph, i);
      }
      list.add(0, i);
    }
  }

  private void dfsTrans(Graph graph) {
    for (int i = 0; i < graph.getV(); i++) {
      color[i] = 'w';
      prev[i] = null;
    }
    time = 0;
    PriorityQueue queue = new PriorityQueue();
    for (int i = 0; i < graph.getV(); i++) {
      queue.insert(i, f[i]);
    }
    System.out.println(Arrays.toString(f));
    queue.printQueue();
    while (!queue.isEmpty()) {
      int u = queue.pop().getKey();
      if (color[u] == 'w') {
        dfsVisitVerbose(graph, u);
        System.out.println();
      }
    }
  }

  public void scc() {
    dfs(graph);
    Graph transposedGraph = graph.transpose();
    dfsTrans(transposedGraph);
  }

  public ArrayList<Integer> topologicalSort(Graph graph) {
    ArrayList<Integer> list = new ArrayList<>();
    dfsTopo(graph, list);
    return list;
  }
}
