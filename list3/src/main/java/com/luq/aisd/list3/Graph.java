package com.luq.aisd.list3;

import java.util.ArrayList;

public class Graph {
  private ArrayList<ArrayList<Edge>> verticies = new ArrayList<>();
  private int e;
  private int v;

  public Graph(int numOfVertices) {
    v = numOfVertices;
    e = 0;
    for (int i = 0; i < v; i++) {
      verticies.add(i, new ArrayList<>());
    }
  }

  public void addEdge(int u, int v, int weight) {
    Edge edge = new Edge(u, v, weight);
    verticies.get(u).add(edge);
    e++;
  }

  public ArrayList<ArrayList<Edge>> getVerticies() {
    return verticies;
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
}
