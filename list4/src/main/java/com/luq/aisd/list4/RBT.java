package com.luq.aisd.list4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class RBT implements BinaryTree {
  private RBNode guard = new RBNode(null);
  private RBNode root = guard;

  private void leftRotate(RBNode x) {
    RBNode y = x.getRight();
    x.setRight(y.getLeft());

    if (y.getLeft() != guard) {
      y.getLeft().setParent(x);
    }

    y.setParent(x.getParent());

    if (x.getParent() == guard) {
      root = y;
    } else if (x == x.getParent().getLeft()) {
      x.getParent().setLeft(y);
    } else {
      x.getParent().setRight(y);
    }

    y.setLeft(x);
    x.setParent(y);
  }

  private void rightRotate(RBNode x) {
    RBNode y = x.getLeft();
    x.setLeft(y.getRight());

    if (y.getRight() != guard) {
      y.getRight().setParent(x);
    }

    y.setParent(x.getParent());

    if (x.getParent() == guard) {
      root = y;
    } else if (x == x.getParent().getRight()) {
      x.getParent().setRight(y);
    } else {
      x.getParent().setLeft(y);
    }

    y.setRight(x);
    x.setParent(y);
  }

  private void insertFixup(RBNode z) {
    RBNode y;
    while (z.getParent().getColor() == RBNode.Color.RED) {
      if (z.getParent() == z.getParent().getParent().getLeft()) {
        y = z.getParent().getParent().getRight();
        if (y.getColor() == RBNode.Color.RED) {
          z.getParent().setColor(RBNode.Color.BLACK);
          y.setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
        } else {
          if (z == z.getParent().getRight()) {
            z = z.getParent();
            leftRotate(z);
          }
          z.getParent().setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
          rightRotate(z.getParent().getParent());
        }
      } else {
        y = z.getParent().getParent().getLeft();
        if (y.getColor() == RBNode.Color.RED) {
          z.getParent().setColor(RBNode.Color.BLACK);
          y.setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
        } else {
          if (z == z.getParent().getLeft()) {
            z = z.getParent();
            rightRotate(z);
          }
          z.getParent().setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
          leftRotate(z.getParent().getParent());
        }
      }
    }
    root.setColor(RBNode.Color.BLACK);
  }

  public void insert(String key) {
    RBNode z = new RBNode(key);
    RBNode y = guard;
    RBNode x = root;
    while (x != guard) {
      y = x;
      if (z.getKey().compareToIgnoreCase(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }
    z.setParent(y);

    if (y == guard) {
      root = z;
    } else if (z.getKey().compareToIgnoreCase(y.getKey()) < 0) {
      y.setLeft(z);
    } else {
      y.setRight(z);
    }

    z.setLeft(guard);
    z.setRight(guard);
    z.setColor(RBNode.Color.RED);
    insertFixup(z);
  }

  public void inorder() {
    iterativeInorder(root);
  }

  public void inorder(RBNode x) {
    if (x != guard) {
      inorder(x.getLeft());
      System.out.println(x.getKey());
      inorder(x.getRight());
    }
  }

  public void iterativeInorder(RBNode x) {
    if (x == null) {
      return;
    }

    Stack<RBNode> stack = new Stack<>();
    RBNode current = x;

    while (current != guard || stack.size() > 0) {
      while (current != guard) {
        stack.push(current);
        current = current.getLeft();
      }

      current = stack.pop();
      System.out.println(current.getKey());
      current = current.getRight();
    }
  }

  private void transplant(RBNode u, RBNode v) {
    if (u.getParent() == guard) {
      root = v;
    } else if (u == u.getParent().getLeft()) {
      u.getParent().setLeft(v);
    } else {
      u.getParent().setRight(v);
    }
    v.setParent(u.getParent());
  }

  private void deleteFixup(RBNode x) {
    RBNode w;
    while (x != root && x.getColor() == RBNode.Color.BLACK) {
      if (x == x.getParent().getLeft()) {
        w = x.getParent().getRight();
        if (w.getColor() == RBNode.Color.RED) {
          w.setColor(RBNode.Color.BLACK);
          x.getParent().setColor(RBNode.Color.RED);
          leftRotate(x.getParent());
          w = x.getParent().getRight();
        }
        if (w.getLeft().getColor() == RBNode.Color.BLACK && w.getRight().getColor() == RBNode.Color.BLACK) {
          w.setColor(RBNode.Color.RED);
          x = x.getParent();
        } else {
          if (w.getRight().getColor() == RBNode.Color.BLACK) {
            w.getLeft().setColor(RBNode.Color.BLACK);
            w.setColor(RBNode.Color.RED);
            rightRotate(w);
            w = x.getParent().getRight();
          }
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(RBNode.Color.BLACK);
          w.getRight().setColor(RBNode.Color.BLACK);
          leftRotate(x.getParent());
          x = root;
        }
      } else {
        w = x.getParent().getLeft();
        if (w.getColor() == RBNode.Color.RED) {
          w.setColor(RBNode.Color.BLACK);
          x.getParent().setColor(RBNode.Color.RED);
          rightRotate(x.getParent());
          w = x.getParent().getLeft();
        }
        if (w.getRight().getColor() == RBNode.Color.BLACK && w.getLeft().getColor() == RBNode.Color.BLACK) {
          w.setColor(RBNode.Color.RED);
          x = x.getParent();
        } else {
          if (w.getLeft().getColor() == RBNode.Color.BLACK) {
            w.getRight().setColor(RBNode.Color.BLACK);
            w.setColor(RBNode.Color.RED);
            leftRotate(w);
            w = x.getParent().getLeft();
          }
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(RBNode.Color.BLACK);
          w.getLeft().setColor(RBNode.Color.BLACK);
          rightRotate(x.getParent());
          x = root;
        }
      }
      x.setColor(RBNode.Color.BLACK);
    }
  }

  public void delete(String key) {
    RBNode z = searchNode(root, key);
    if (z != null) {
      RBNode y = z;
      RBNode.Color yOriginalColor = y.getColor();
      RBNode x;

      if (z.getLeft() == guard) {
        x = z.getRight();
        transplant(z, z.getRight());
      } else if (z.getRight() == guard) {
        x = z.getLeft();
        transplant(z, z.getLeft());
      } else {
        y = minimum(z.getRight());
        yOriginalColor = y.getColor();
        x = y.getRight();
        if (y.getParent() == z) {
          x.setParent(y);
        } else {
          transplant(y, y.getRight());
          y.setRight(z.getRight());
          y.getRight().setParent(y);
        }

        transplant(z, y);
        y.setLeft(z.getLeft());
        y.getLeft().setParent(y);
        y.setColor(z.getColor());
      }
      if (yOriginalColor == RBNode.Color.BLACK) {
        deleteFixup(x);
      }
    }
  }

  public RBNode minimum(RBNode x) {
    while (x.getLeft() != guard) {
      x = x.getLeft();
    }
    return x;
  }

  public Boolean search(RBNode x, String key) {
    while (x != guard && !key.equals(x.getKey())) {
      if (key.compareToIgnoreCase(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    return x != guard;
  }

  public RBNode searchNode(RBNode x, String key) {
    while (x != guard && !key.equals(x.getKey())) {
      if (key.compareToIgnoreCase(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    return x != guard ? x : null;
  }

  public RBNode getRoot() {
    return root;
  }
}
