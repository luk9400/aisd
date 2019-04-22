package com.luq.aisd.list3;

import java.util.Comparator;

public class Kruskal extends MST {

  public Kruskal(WeightGraph graph) {
    super(graph);
  }

  public WeightGraph kruskal() {
    A = new WeightGraph(0);
    for (int i = 0; i < graph.getV(); i++) {
      makeSet(i);
    }

    //sorting edges (dunno in what order xD)
    for (int i = 0; i < graph.getV(); i++) {
      graph.getVerticies().get(i).sort(Comparator.comparingInt(Edge::getWeight));
    }

    for (int i = 0; i < graph.getV(); i++) {
      for (Edge edge : graph.getVerticies().get(i)) {
        if (findSet(edge.getU()) != findSet(edge.getV())) {
          A.addVertex(edge.getU());
          A.addEdge(edge.getU(), edge.getV(), edge.getWeight());
          union(edge.getU(), edge.getV());
          System.out.println("u: " + edge.getU() + ", v: " + edge.getV());
        }
      }
    }
    System.out.println("Sum of weights: " + A.getSumOfWeights());
    return A;
  }
}
