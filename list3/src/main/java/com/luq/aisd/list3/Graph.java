package com.luq.aisd.list3;

import java.util.ArrayList;

public class Graph {
  private ArrayList<ArrayList<Edge>> vertices = new ArrayList<>();
  private int e;
  private int v;

  public Graph(int numOfVertices) {
    v = numOfVertices;
    e = 0;
    for (int i = 0; i < v; i++) {
      vertices.add(i, new ArrayList<>());
    }
  }

  public void addWeightEdge(int u, int v, float weight) {
    Edge edge = new Edge(u, v, weight);
    vertices.get(u).add(edge);
    e++;
  }

  public void addEdge(int u, int v) {
    Edge edge = new Edge(u, v);
    vertices.get(u).add(edge);
    e++;
  }

  public void addVertex(int u) {
    vertices.add(u, new ArrayList<>());
    v++;
  }

  public ArrayList<ArrayList<Edge>> getVertices() {
    return vertices;
  }

  public int getE() {
    return e;
  }

  public void setE(int e) {
    this.e = e;
  }

  public int getV() {
    return v;
  }

  public float getSumOfWeights() {
    float sum = 0;
    for (int i = 0; i < v; i++) {
      for (Edge edge : vertices.get(i)) {
        sum += edge.getWeight();
      }
    }
    return sum;
  }

  public Graph transpose() {
    Graph newGraph = new Graph(v);
    for (int i = 0; i < v; i++) {
      for (Edge edge : vertices.get(i)) {
        newGraph.addEdge(edge.getV(), edge.getU());
      }
    }
    return newGraph;
  }
}
