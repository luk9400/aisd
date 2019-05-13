package com.luq.aisd.list4;

public class Node {
  private String key;
  private Node parent;
  private Node left;
  private Node right;

  public Node(String key) {
    this.key = key;
    parent = null;
    left = null;
    right = null;
  }

  public String getKey() {
    return key;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }
}
