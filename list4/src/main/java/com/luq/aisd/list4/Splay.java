package com.luq.aisd.list4;

public class Splay extends BST {

  public Splay() {
    super();
  }

  private void splay(Node node) {
    Node parent = node.getParent();
    Node grandParent = (parent != null) ? parent.getParent() : null;

    if (parent != null && parent == root) {
      grandParent = parent.getParent();
      // zig
      root = node;
      node.setParent(null);

      if (parent != null) {
        if (node == parent.getLeft()) {
          parent.setLeft(node.getRight());
          if (node.getRight() != null) {
            node.getRight().setParent(parent);
          }

          node.setRight(parent);
          parent.setParent(node);
        } else {
          parent.setRight(node.getLeft());
          if (node.getLeft() != null) {
            node.getLeft().setParent(parent);
          }

          node.setLeft(parent);
          parent.setParent(node);
        }
      }
      return;
    }

    if (parent != null && grandParent != null) {
      Node greatGrandParent = grandParent.getParent();
      if (greatGrandParent != null && greatGrandParent.getLeft() == grandParent) {
        greatGrandParent.setLeft(node);
        node.setParent(greatGrandParent);
      } else if (greatGrandParent != null && greatGrandParent.getRight() == grandParent) {
        greatGrandParent.setRight(node);
        node.setParent(greatGrandParent);
      } else {
        root = node;
        node.setParent(null);
      }

      if ((node == parent.getLeft() && parent == grandParent.getLeft()) || (node == parent.getRight() && parent == grandParent.getRight())) {
        // zig-zig
        if (node == parent.getLeft()) {
          Node nodeRight = node.getRight();
          node.setRight(parent);
          parent.setParent(node);
          parent.setLeft(nodeRight);

          if (nodeRight != null) {
            nodeRight.setParent(parent);
          }

          Node parentRight = parent.getRight();
          parent.setRight(grandParent);
          grandParent.setParent(parent);
          grandParent.setLeft(parentRight);

          if (parentRight != null) {
            parentRight.setParent(grandParent);
          }
        } else {
          Node nodeLeft = node.getLeft();
          node.setLeft(parent);
          parent.setParent(node);
          parent.setRight(nodeLeft);

          if (nodeLeft != null) {
            nodeLeft.setParent(parent);
          }

          Node parentLeft = parent.getLeft();
          parent.setLeft(grandParent);
          grandParent.setParent(parent);
          grandParent.setRight(parentLeft);

          if (parentLeft != null) {
            parentLeft.setParent(grandParent);
          }
        }
        return;
      }

      // zig-zag
      if (node == parent.getLeft()) {
        Node nodeLeft = node.getRight();
        Node nodeRight = node.getLeft();

        node.setRight(parent);
        parent.setParent(node);
        node.setLeft(grandParent);
        grandParent.setParent(node);
        parent.setLeft(nodeLeft);

        if (nodeLeft != null) {
          nodeLeft.setParent(parent);
        }

        grandParent.setRight(nodeRight);

        if (nodeRight != null) {
          nodeRight.setParent(grandParent);
        }

        return;
      }

      Node nodeLeft = node.getLeft();
      Node nodeRight = node.getRight();

      node.setLeft(parent);
      parent.setParent(node);
      node.setRight(grandParent);
      grandParent.setParent(node);
      parent.setRight(nodeLeft);

      if (nodeLeft != null) {
        nodeLeft.setParent(parent);
      }

      grandParent.setLeft(nodeRight);

      if (nodeRight != null) {
        nodeRight.setParent(grandParent);
      }
    }
  }

  @Override
  protected Node insert(Node z) {
    Node nodeToReturn = super.insert(z);
    Node nodeAdded = nodeToReturn;

    if (nodeAdded != null) {
      while (nodeAdded.getParent() != null) {
        splay(nodeAdded);
      }
    }

    return nodeToReturn;
  }

  @Override
  protected Node delete(Node z) {
    Node nodeToDelete = super.delete(z);

    if (nodeToDelete != null && nodeToDelete.getParent() != null) {
      Node nodeParent = nodeToDelete.getParent();

      while (nodeParent.getParent() != null) {
        splay(nodeParent);
      }
    }

    return nodeToDelete;
  }

  @Override
  public Node searchNode(Node x, String key) {
    Node node = super.searchNode(x, key);
    if (node != null) {
      while (node.getParent() != null) {
        splay(node);
      }
    }

    return node;
  }
}
