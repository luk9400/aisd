package com.luq.aisd.list4;

public class Main {

  public static void bst() {
    BST bst = new BST();
    bst.load("lotr.txt");
    System.out.println("done");
    bst.inorder();
  }

  public static void rbt() {
    RBT rbt = new RBT();
    rbt.load("lotr.txt");
    System.out.println("done");
    rbt.inorder();
  }

  public static void splay() {
    Splay splay = new Splay();
    splay.load("KJB.txt");
    System.out.println("done");
    splay.inorder();
  }

  public static void main(String[] args) {
    //bst();
    //rbt();
    splay();
  }
}
