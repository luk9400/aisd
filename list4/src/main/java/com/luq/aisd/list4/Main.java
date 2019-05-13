package com.luq.aisd.list4;

public class Main {

  public static void bst() {
    BST bst = BST.load("input.txt");
    bst.inorder(bst.getRoot());
  }

  public static void rbt() {
    RBT rbt = RBT.load("input.txt");
//    rbt.insert("dupa");
//    rbt.insert("problem");
//    rbt.insert("ala");
//    rbt.insert("dzida");
//    rbt.insert("rzeka");
//    rbt.inorder(rbt.getRoot());
//    System.out.println(rbt.search(rbt.getRoot(), "dupa"));
//    rbt.delete("alaasd");
    rbt.inorder(rbt.getRoot());
  }

  public static void main(String[] args) {
    //bst();
    rbt();
  }
}
