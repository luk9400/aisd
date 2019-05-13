package com.luq.aisd.list4;

public class RBNode {
  public enum Color {BLACK, RED}

  private Color color;
  private String key;
  private RBNode parent;
  private RBNode left;
  private RBNode right;

  public RBNode(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public RBNode getParent() {
    return parent;
  }

  public void setParent(RBNode parent) {
    this.parent = parent;
  }

  public RBNode getLeft() {
    return left;
  }

  public void setLeft(RBNode left) {
    this.left = left;
  }

  public RBNode getRight() {
    return right;
  }

  public void setRight(RBNode right) {
    this.right = right;
  }


  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
