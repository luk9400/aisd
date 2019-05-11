package com.luq.aisd.list3;

import java.util.Comparator;

public class Kruskal extends MST {

  public Kruskal(Graph graph) {
    super(graph);
  }

  public Graph kruskal() {
    A = new Graph(0);
    for (int i = 0; i < graph.getV(); i++) {
      makeSet(i);
    }

    for (int i = 0; i < graph.getV(); i++) {
      graph.getVerticies().get(i).sort(Comparator.comparingDouble(Edge::getWeight));
    }

    for (int i = 0; i < graph.getV(); i++) {
      for (Edge edge : graph.getVerticies().get(i)) {
        if (findSet(edge.getU()) != findSet(edge.getV())) {
          A.addVertex(edge.getU());
          A.addWeightEdge(edge.getU(), edge.getV(), edge.getWeight());
          union(edge.getU(), edge.getV());
          System.out.println("u: " + edge.getU() + ", v: " + edge.getV() + ", w: " + edge.getWeight());
        }
      }
    }
    System.out.println("Sum of weights: " + A.getSumOfWeights());
    return A;
  }
}
