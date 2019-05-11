package com.luq.aisd.list3;

import java.util.ArrayList;

public class Graph {
  private ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
  private int e;
  private int v;

  public Graph(int numOfVertices) {
    v = numOfVertices;
    e = 0;
    for (int i = 0; i < v; i++) {
      adj.add(i, new ArrayList<>());
    }
  }

  public void addWeightEdge(int u, int v, float weight) {
    Edge edge = new Edge(u, v, weight);
    adj.get(u).add(edge);
    e++;
  }

  public void addEdge(int u, int v) {
    Edge edge = new Edge(u, v);
    adj.get(u).add(edge);
    e++;
  }

  public void addVertex(int u) {
    adj.add(u, new ArrayList<>());
    v++;
  }

  public float getEdgeWeight(int u, int v) {
    for (Edge edge : adj.get(u)) {
      if (edge.getV() == v) {
        return edge.getWeight();
      }
    }
    return -1;
  }

  public ArrayList<ArrayList<Edge>> getAdj() {
    return adj;
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
      for (Edge edge : adj.get(i)) {
        sum += edge.getWeight();
      }
    }
    return sum;
  }

  public Graph transpose() {
    Graph newGraph = new Graph(v);
    for (int i = 0; i < v; i++) {
      for (Edge edge : adj.get(i)) {
        newGraph.addEdge(edge.getV(), edge.getU());
      }
    }
    return newGraph;
  }
}
