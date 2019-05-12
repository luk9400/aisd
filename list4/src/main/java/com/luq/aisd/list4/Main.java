package com.luq.aisd.list4;

public class Main {

  public static void bst() {
    BST bst = BST.load("input.txt");
    bst.inorder(bst.getRoot());
  }

  public static void main(String[] args) {
    bst();
  }
}
