package com.luq.aisd.list4;

public class Splay extends BST {

  public Splay() {
    super();
  }

  private void splay(Node node) {
    Node parent = node.getParent();
    Node grandParent = (parent != null) ? parent.getParent() : null;

    comparations += 2;
    if (parent != null && parent == root) {
      grandParent = parent.getParent();
      // zig
      root = node;
      node.setParent(null);
      modifications++;

      comparations++;
      if (parent != null) {
        comparations++;
        if (node == parent.getLeft()) {
          parent.setLeft(node.getRight());
          modifications++;
          comparations++;
          if (node.getRight() != null) {
            node.getRight().setParent(parent);
            modifications++;
          }

          node.setRight(parent);
          parent.setParent(node);
          modifications += 2;
        } else {
          parent.setRight(node.getLeft());
          modifications++;
          comparations++;
          if (node.getLeft() != null) {
            node.getLeft().setParent(parent);
            modifications++;
          }

          node.setLeft(parent);
          parent.setParent(node);
          modifications += 2;
        }
      }
      return;
    }

    comparations += 2;
    if (parent != null && grandParent != null) {
      Node greatGrandParent = grandParent.getParent();

      comparations += 2;
      if (greatGrandParent != null && greatGrandParent.getLeft() == grandParent) {
        greatGrandParent.setLeft(node);
        node.setParent(greatGrandParent);
        modifications += 2;
      } else if (greatGrandParent != null && greatGrandParent.getRight() == grandParent) {
        comparations += 2;
        greatGrandParent.setRight(node);
        node.setParent(greatGrandParent);
        modifications += 2;
      } else {
        comparations += 2;
        root = node;
        node.setParent(null);
        modifications++;
      }

      comparations += 4;
      if ((node == parent.getLeft() && parent == grandParent.getLeft()) || (node == parent.getRight() && parent == grandParent.getRight())) {
        // zig-zig
        comparations++;
        if (node == parent.getLeft()) {
          Node nodeRight = node.getRight();
          node.setRight(parent);
          parent.setParent(node);
          parent.setLeft(nodeRight);
          modifications += 3;

          comparations++;
          if (nodeRight != null) {
            nodeRight.setParent(parent);
            modifications++;
          }

          Node parentRight = parent.getRight();
          parent.setRight(grandParent);
          grandParent.setParent(parent);
          grandParent.setLeft(parentRight);
          modifications += 3;

          comparations++;
          if (parentRight != null) {
            parentRight.setParent(grandParent);
            modifications++;
          }
        } else {
          Node nodeLeft = node.getLeft();
          node.setLeft(parent);
          parent.setParent(node);
          parent.setRight(nodeLeft);
          modifications += 3;

          comparations++;
          if (nodeLeft != null) {
            nodeLeft.setParent(parent);
            modifications++;
          }

          Node parentLeft = parent.getLeft();
          parent.setLeft(grandParent);
          grandParent.setParent(parent);
          grandParent.setRight(parentLeft);
          modifications += 3;

          comparations++;
          if (parentLeft != null) {
            parentLeft.setParent(grandParent);
            modifications++;
          }
        }
        return;
      }

      // zig-zag
      comparations++;
      if (node == parent.getLeft()) {
        Node nodeLeft = node.getRight();
        Node nodeRight = node.getLeft();

        node.setRight(parent);
        parent.setParent(node);
        node.setLeft(grandParent);
        grandParent.setParent(node);
        parent.setLeft(nodeLeft);
        modifications += 5;

        comparations++;
        if (nodeLeft != null) {
          nodeLeft.setParent(parent);
          modifications++;
        }

        grandParent.setRight(nodeRight);
        modifications++;

        comparations++;
        if (nodeRight != null) {
          nodeRight.setParent(grandParent);
          modifications++;
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
      modifications += 5;

      comparations++;
      if (nodeLeft != null) {
        nodeLeft.setParent(parent);
        modifications++;
      }

      grandParent.setLeft(nodeRight);
      modifications++;

      comparations++;
      if (nodeRight != null) {
        nodeRight.setParent(grandParent);
        modifications++;
      }
    }
  }

  @Override
  protected Node insert(Node z) {
    Node nodeToReturn = super.insert(z);
    Node nodeAdded = nodeToReturn;

    comparations++;
    if (nodeAdded != null) {
      comparations++;
      while (nodeAdded.getParent() != null) {
        comparations++;
        splay(nodeAdded);
      }
    }

    return nodeToReturn;
  }

  @Override
  protected Node delete(Node z) {
    Node nodeToDelete = super.delete(z);

    comparations += 2;
    if (nodeToDelete != null && nodeToDelete.getParent() != null) {
      Node nodeParent = nodeToDelete.getParent();

      comparations++;
      while (nodeParent.getParent() != null) {
        comparations++;
        splay(nodeParent);
      }
    }

    return nodeToDelete;
  }

  @Override
  public Node searchNode(Node x, String key) {
    Node node = super.searchNode(x, key);
    comparations++;
    if (node != null) {
      comparations++;
      while (node.getParent() != null) {
        comparations++;
        splay(node);
      }
    }

    return node;
  }

  @Override
  public Boolean search(Node x, String key) {
    Node node = super.searchNode(x, key);
    comparations++;
    if (node != null) {
      comparations++;
      while (node.getParent() != null) {
        comparations++;
        splay(node);
      }
    }

    return node != null;
  }
}
