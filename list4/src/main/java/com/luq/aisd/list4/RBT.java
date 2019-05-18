package com.luq.aisd.list4;

import java.util.Stack;

public class RBT extends BinaryTree {
  private RBNode guard = new RBNode(null);
  private RBNode root = guard;

  private void leftRotate(RBNode x) {
    RBNode y = x.getRight();
    x.setRight(y.getLeft());
    modifications++;

    comparations++;
    if (y.getLeft() != guard) {
      y.getLeft().setParent(x);
      modifications++;
    }

    y.setParent(x.getParent());
    modifications++;

    comparations++;
    if (x.getParent() == guard) {
      root = y;
    } else if (x == x.getParent().getLeft()) {
      comparations++;
      x.getParent().setLeft(y);
      modifications++;
    } else {
      comparations++;
      x.getParent().setRight(y);
      modifications++;
    }

    y.setLeft(x);
    x.setParent(y);
    modifications += 2;
  }

  private void rightRotate(RBNode x) {
    RBNode y = x.getLeft();
    x.setLeft(y.getRight());
    modifications++;

    comparations++;
    if (y.getRight() != guard) {
      y.getRight().setParent(x);
      modifications++;
    }

    y.setParent(x.getParent());
    modifications++;

    comparations++;
    if (x.getParent() == guard) {
      root = y;
    } else if (x == x.getParent().getRight()) {
      comparations++;
      x.getParent().setRight(y);
      modifications++;
    } else {
      comparations++;
      x.getParent().setLeft(y);
      modifications++;
    }

    y.setRight(x);
    x.setParent(y);
    modifications += 2;
  }

  private void insertFixup(RBNode z) {
    RBNode y;

    comparations++;
    while (z.getParent().getColor() == RBNode.Color.RED) {
      comparations += 2;
      if (z.getParent() == z.getParent().getParent().getLeft()) {
        y = z.getParent().getParent().getRight();
        comparations++;
        if (y.getColor() == RBNode.Color.RED) {
          z.getParent().setColor(RBNode.Color.BLACK);
          y.setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
          z = z.getParent().getParent();
          // fix ^
          modifications += 3;
        } else {
          comparations++;
          if (z == z.getParent().getRight()) {
            z = z.getParent();
            leftRotate(z);
          }
          z.getParent().setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
          rightRotate(z.getParent().getParent());
          modifications += 2;
        }
      } else {
        y = z.getParent().getParent().getLeft();
        comparations++;
        if (y.getColor() == RBNode.Color.RED) {
          z.getParent().setColor(RBNode.Color.BLACK);
          y.setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
          z = z.getParent().getParent();
          // fix ^
          modifications += 3;
        } else {
          comparations++;
          if (z == z.getParent().getLeft()) {
            z = z.getParent();
            rightRotate(z);
          }
          z.getParent().setColor(RBNode.Color.BLACK);
          z.getParent().getParent().setColor(RBNode.Color.RED);
          leftRotate(z.getParent().getParent());
          modifications += 2;
        }
      }
    }
    root.setColor(RBNode.Color.BLACK);
    modifications++;
  }

  public void insert(String key) {
    inserts++;
    numberOfElements++;
    if (numberOfElements > maxNumberOfElements) {
      maxNumberOfElements = numberOfElements;
    }

    RBNode z = new RBNode(key);
    RBNode y = guard;
    RBNode x = root;

    comparations++;
    while (x != guard) {
      comparations++;
      y = x;
      comparations++;
      if (z.getKey().compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }
    z.setParent(y);
    modifications++;

    comparations++;
    if (y == guard) {
      root = z;
    } else if (z.getKey().compareTo(y.getKey()) < 0) {
      comparations++;
      y.setLeft(z);
      modifications++;
    } else {
      comparations++;
      y.setRight(z);
      modifications++;
    }

    z.setLeft(guard);
    z.setRight(guard);
    z.setColor(RBNode.Color.RED);
    insertFixup(z);
    modifications += 3;
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
    comparations++;
    if (u.getParent() == guard) {
      root = v;
    } else if (u == u.getParent().getLeft()) {
      comparations++;
      u.getParent().setLeft(v);
      modifications++;
    } else {
      comparations++;
      u.getParent().setRight(v);
      modifications++;
    }
    v.setParent(u.getParent());
    modifications++;
  }

  private void deleteFixup(RBNode x) {
    comparations += 2;
    while (x != this.root && x.getColor() == RBNode.Color.BLACK) {
      comparations += 3;
      if (x == x.getParent().getLeft()) {
        RBNode w = x.getParent().getRight();
        comparations++;
        if (w.getColor() == RBNode.Color.RED) {
          modifications += 2;
          w.setColor(RBNode.Color.BLACK);
          x.getParent().setColor(RBNode.Color.RED);
          leftRotate(x.getParent());
          w = x.getParent().getRight();
        }
        comparations += 2;
        if (w.getLeft().getColor() == RBNode.Color.BLACK && w.getRight().getColor() == RBNode.Color.BLACK) {
          modifications++;
          w.setColor(RBNode.Color.RED);
          x = x.getParent();
        } else {
          comparations++;
          if (w.getRight().getColor() == RBNode.Color.BLACK) {
            modifications += 2;
            w.getLeft().setColor(RBNode.Color.BLACK);
            w.setColor(RBNode.Color.RED);
            rightRotate(w);
            w = x.getParent().getRight();
          }
          modifications += 3;
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(RBNode.Color.BLACK);
          w.getRight().setColor(RBNode.Color.BLACK);
          leftRotate(x.getParent());
          x = this.root;
        }
      } else {
        RBNode w = x.getParent().getLeft();
        comparations++;
        if (w.getColor() == RBNode.Color.RED) {
          modifications += 2;
          w.setColor(RBNode.Color.BLACK);
          x.getParent().setColor(RBNode.Color.RED);
          rightRotate(x.getParent());
          w = x.getParent().getLeft();
        }
        comparations++;
        if (w.getRight().getColor() == RBNode.Color.BLACK && w.getLeft().getColor() == RBNode.Color.BLACK) {
          modifications++;
          w.setColor(RBNode.Color.RED);
          x = x.getParent();
        } else {
          comparations++;
          if (w.getLeft().getColor() == RBNode.Color.BLACK) {
            modifications += 2;
            w.getRight().setColor(RBNode.Color.BLACK);
            w.setColor(RBNode.Color.RED);
            leftRotate(w);
            w = x.getParent().getLeft();
          }
          modifications += 3;
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(RBNode.Color.BLACK);
          w.getLeft().setColor(RBNode.Color.BLACK);
          rightRotate(x.getParent());
          x = this.root;
        }
      }
    }
    modifications++;
    x.setColor(RBNode.Color.BLACK);
  }

  public void delete(String key) {
    RBNode z = searchNode(root, key);
    searches++;
    deletes++;

    comparations++;
    if (z != null) {
      RBNode y = z;
      RBNode.Color yOriginalColor = y.getColor();
      RBNode x;

      comparations++;
      if (z.getLeft() == guard) {
        x = z.getRight();
        transplant(z, z.getRight());
      } else if (z.getRight() == guard) {
        comparations++;
        x = z.getLeft();
        transplant(z, z.getLeft());
      } else {
        comparations++;
        y = minimum(z.getRight());
        yOriginalColor = y.getColor();
        x = y.getRight();
        comparations++;
        if (y.getParent() == z) {
          x.setParent(y);
          modifications++;
        } else {
          transplant(y, y.getRight());
          y.setRight(z.getRight());
          y.getRight().setParent(y);
          modifications += 2;
        }

        transplant(z, y);
        y.setLeft(z.getLeft());
        y.getLeft().setParent(y);
        y.setColor(z.getColor());
        modifications += 3;
      }
      comparations++;
      if (yOriginalColor == RBNode.Color.BLACK) {
        deleteFixup(x);
      }
    }
  }

  public RBNode minimum(RBNode x) {
    comparations++;
    while (x.getLeft() != guard) {
      comparations++;
      x = x.getLeft();
    }
    return x;
  }

  public void search(String key) {
    //System.out.println(search(root, key));
    search(root, key);
    searches++;
  }

  public Boolean search(RBNode x, String key) {
    comparations += 2;
    while (x != guard && !key.equals(x.getKey())) {
      comparations += 3;
      if (key.compareTo(x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }

    return x != guard;
  }

  public RBNode searchNode(RBNode x, String key) {
    comparations += 2;
    while (x != guard && !key.equals(x.getKey())) {
      comparations += 3;
      if (key.compareTo(x.getKey()) < 0) {
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
