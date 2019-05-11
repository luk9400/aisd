package com.luq.aisd.list3;

import java.util.ArrayList;
import java.util.Comparator;

public class Kruskal extends MST {

  public Kruskal(Graph graph) {
    super(graph);
  }

  public Graph kruskal() {
    A = new Graph(0);
    ArrayList<Edge> edges = new ArrayList<>();

    for (int i = 0; i < graph.getV(); i++) {
      makeSet(i);
      edges.addAll(graph.getAdj().get(i));
      A.addVertex(i);
    }

    edges.sort(Comparator.comparingDouble(Edge::getWeight));

    for (Edge edge : edges) {
      if (findSet(edge.getU()) != findSet(edge.getV())) {
        A.addWeightEdge(edge.getU(), edge.getV(), edge.getWeight());
        union(edge.getU(), edge.getV());
        System.out.println("u: " + edge.getU() + ", v: " + edge.getV() + ", w: " + edge.getWeight());
      }
    }

    System.out.println("Sum of weights: " + A.getSumOfWeights());
    return A;
  }
}
