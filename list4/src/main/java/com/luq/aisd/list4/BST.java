package com.luq.aisd.list4;

import java.util.Stack;

public class BST implements BinaryTree{
  protected Node root;

  public BST() {
    root = null;
  }

  public void insert(String key) {
    insert(new Node(key));
  }

  protected Node insert(Node z) {
    Node y = null;
    Node x = root;

    while (x != null) {
      y = x;
      if (z.getKey().compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    z.setParent(y);

    if (y == null) {
      root = z;
    } else if (z.getKey().compareTo(y.getKey()) < 0) {
      y.setLeft(z);
    } else {
      y.setRight(z);
    }

    return z;
  }

  public void inorder() {
    iterativeInorder(root);
  }

  public void inorder(Node x) {
    if (x != null) {
      inorder(x.getLeft());
      System.out.println(x.getKey());
      inorder(x.getRight());
    }
  }

  public void iterativeInorder(Node x) {
    if (x == null) {
      return;
    }

    Stack<Node> stack = new Stack<>();
    Node current = x;

    while (current != null || stack.size() > 0) {
      while (current != null) {
        stack.push(current);
        current = current.getLeft();
      }

      current = stack.pop();
      System.out.println(current.getKey());
      current = current.getRight();
    }
  }

  public void search(String key) {
    System.out.println(search(root, key));
  }

  /**
   * Iterative approach of searching
   * @param x
   * @param key
   * @return
   */
  public Boolean search(Node x, String key) {
    while (x != null && !key.equals(x.getKey())) {
      if (key.compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    return x != null;
  }

  public Node searchNode(Node x, String key) {
    while (x != null && !key.equals(x.getKey())) {
      if (key.compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    return x;
  }

  public Node minimum(Node x) {
    while (x.getLeft() != null) {
      x = x.getLeft();
    }
    return x;
  }

  public Node successor(Node x) {
    if (x.getRight() != null) {
      return minimum(x.getRight());
    }
    Node y = x.getParent();
    while (y != null && x == y.getRight()) {
      x = y;
      y = y.getParent();
    }
    return y;
  }

  private void transplant(Node u, Node v) {
    if (u.getParent() == null) {
      root = v;
    } else if (u == u.getParent().getLeft()) {
      u.getParent().setLeft(v);
    } else {
      u.getParent().setRight(v);
    }
    if (v != null) {
      v.setParent(u.getParent());
    }
  }

  public void delete(String key) {
    delete(searchNode(root, key));
  }

  protected Node delete(Node z) {
    Node y;
    if (z != null) {
      if (z.getLeft() == null) {
        transplant(z, z.getRight());
      }
      else if (z.getRight() == null){
        transplant(z, z.getLeft());
      } else {
        y = minimum(z.getRight());
        if (y.getParent() != z) {
          transplant(y, y.getRight());
          y.setRight(z.getRight());
          y.getRight().setParent(y);
        }
        transplant(z, y);
        y.setLeft(z.getLeft());
        y.getLeft().setParent(y);
      }
    }

    return z;
  }

  public Node getRoot() {
    return root;
  }
}
