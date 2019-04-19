package com.luq.aisd.list3;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  Priority queue implemented with binary heap
 */
public class PriorityQueue {
  private ArrayList<Node> list = new ArrayList<Node>();
  private int size = 0;

  public ArrayList<Node> getQueue() {
    return list;
  }

  public int getSize() {
    return size;
  }

  private int parent(int i) {
    return i >> 1;
  }

  private int left(int i) {
    return i << 1;
  }

  private int right(int i) {
    return (i << 1) + 1;
  }

  private void heapify(int i) {
    int l = left(i);
    int r = right(i);
    int smallest = i;
    if (l < size && list.get(l).getPriority() < list.get(smallest).getPriority()) {
      smallest = l;
    }
    if (r < size && list.get(r).getPriority() < list.get(smallest).getPriority()) {
      smallest = r;
    }
    if (smallest != i) {
      Collections.swap(list, i, smallest);
      heapify(smallest);
    }
  }

  public Node pop() {
    if (size < 1) {
      System.out.println("Pop: null");
      return null;
    }
    Node max = list.get(0);
    list.set(0, list.get(size - 1));
    list.remove(size - 1);
    size--;
    heapify(0);
    System.out.println("Pop: " + max.getKey() + " " + max.getPriority());
    return max;
  }

  public void insert(int key, float priority) {
    Node node = new Node(key, priority);
    size++;
    int i = size - 1;
    while (i > 0 && list.get(parent(i)).getPriority() > node.getPriority()) {
      Collections.swap(list, i - 1, parent(i));
      i = parent(i);
    }
    list.add(i, node);
  }

  public Node top() {
    if (size == 0) {
      System.out.println("Top: null");
      return null;
    } else {
      System.out.println("Top: " + list.get(0).getKey() + " " + list.get(0).getPriority());
      return list.get(0);
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void printQueue() {
    for (Node i : list) {
      System.out.print("(" + i.getKey() + ", " + i.getPriority() + ") ");
    }
    System.out.println();
  }
}
