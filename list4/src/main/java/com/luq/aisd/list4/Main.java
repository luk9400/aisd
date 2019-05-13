package com.luq.aisd.list4;

public class Main {

  public static void bst() {
    BST bst = new BST();
    bst.load("input.txt");
    bst.inorder();
    bst.delete("dup");
    bst.inorder();
  }

  public static void rbt() {
    RBT rbt = new RBT();
    rbt.load("input.txt");
//    rbt.insert("dupa");
//    rbt.insert("problem");
//    rbt.insert("ala");
//    rbt.insert("dzida");
//    rbt.insert("rzeka");
//    rbt.inorder(rbt.getRoot());
//    System.out.println(rbt.search(rbt.getRoot(), "dupa"));
//    rbt.delete("alaasd");
    rbt.inorder();
  }

  public static void splay() {
    Splay splay = new Splay();
    splay.load("input.txt");
//    splay.insert("dupa");
//    splay.insert("dzida");
//    splay.insert("problem");
//    splay.inorder(splay.getRoot());
//    splay.delete("dupa");
//    System.out.println();
    splay.inorder(splay.getRoot());
  }

  public static void main(String[] args) {
    //bst();
    //rbt();
    splay();
  }
}
