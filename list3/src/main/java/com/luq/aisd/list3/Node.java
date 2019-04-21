package com.luq.aisd.list3;

public class Node {
  private int key;
  private int priority;

  public Node(int key, int priority) {
    this.key = key;
    this.priority = priority;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
}
