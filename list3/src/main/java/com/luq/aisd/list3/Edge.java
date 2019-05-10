package com.luq.aisd.list3;

public class Edge {
  private int u;
  private int v;
  private float weight;

  public Edge(int u, int v, float weight) {
    this.u = u;
    this.v = v;
    this.weight = weight;
  }

  public Edge(int u, int v) {
    this.u = u;
    this.v = v;
  }

  public int getU() {
    return u;
  }

  public int getV() {
    return v;
  }

  public float getWeight() {
    return weight;
  }
}
