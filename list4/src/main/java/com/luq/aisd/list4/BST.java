package com.luq.aisd.list4;

import java.util.Stack;

public class BST extends BinaryTree{
  protected Node root;

  public BST() {
    root = null;
  }

  public void insert(String key) {
    insert(new Node(key));
    inserts++;
    numberOfElements++;
    if (numberOfElements > maxNumberOfElements) {
      maxNumberOfElements = numberOfElements;
    }
  }

  protected Node insert(Node z) {
    Node y = null;
    Node x = root;

    comparations++;
    while (x != null) {
      comparations += 2;
      y = x;
      if (z.getKey().compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    modifications++;
    z.setParent(y);

    comparations++;
    if (y == null) {
      root = z;
    } else if (z.getKey().compareTo(y.getKey()) < 0) {
      comparations++;
      modifications++;
      y.setLeft(z);
    } else {
      comparations++;
      modifications++;
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
    //System.out.println(search(root, key));
    search(root, key);
    searches++;
  }

  /**
   * Iterative approach of searching
   * @param x
   * @param key
   * @return
   */
  public Boolean search(Node x, String key) {
    comparations += 2;
    while (x != null && !key.equals(x.getKey())) {
      comparations += 3;
      if (key.compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    return x != null;
  }

  public Node searchNode(Node x, String key) {
    comparations += 2;
    while (x != null && !key.equals(x.getKey())) {
      comparations += 3;
      if (key.compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    return x;
  }

  public Node minimum(Node x) {
    comparations++;
    while (x.getLeft() != null) {
      comparations++;
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
    comparations++;
    if (u.getParent() == null) {
      root = v;
    } else if (u == u.getParent().getLeft()) {
      comparations++;
      u.getParent().setLeft(v);
    } else {
      comparations++;
      u.getParent().setRight(v);
    }
    comparations++;
    if (v != null) {
      v.setParent(u.getParent());
      modifications++;
    }
  }

  public void delete(String key) {
    delete(searchNode(root, key));
    searches++;
    deletes++;
  }

  protected Node delete(Node z) {
    Node y;
    comparations++;
    if (z != null) {
      comparations++;
      if (z.getLeft() == null) {
        transplant(z, z.getRight());
      }
      else if (z.getRight() == null){
        comparations++;
        transplant(z, z.getLeft());
      } else {
        comparations++;
        y = minimum(z.getRight());
        comparations++;
        if (y.getParent() != z) {
          transplant(y, y.getRight());
          y.setRight(z.getRight());
          y.getRight().setParent(y);
          modifications += 2;
        }
        transplant(z, y);
        y.setLeft(z.getLeft());
        y.getLeft().setParent(y);
        modifications += 2;
      }
      numberOfElements--;
    }

    return z;
  }

  public Node getRoot() {
    return root;
  }
}
