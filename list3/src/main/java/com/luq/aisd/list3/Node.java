package com.luq.aisd.list3;

public class Node {
  private int key;
  private float priority;

  public Node(int key, float priority) {
    this.key = key;
    this.priority = priority;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public float getPriority() {
    return priority;
  }

  public void setPriority(float priority) {
    this.priority = priority;
  }
}
