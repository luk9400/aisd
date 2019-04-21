package com.luq.aisd.list3;

public class Main {

  public static void main(String[] args) {
    PriorityQueue queue = new PriorityQueue();

    queue.insert(6, 6);
    queue.insert(1, 0);
    queue.insert(2, 2);
    queue.insert(3, 3);
    queue.insert(4, 4);
    queue.insert(5, 5);
    queue.insert(7, 2.5f);


    queue.printQueue();

    System.out.println("Decrease 7 to 1");
    queue.priority(7, 1);
    queue.printQueue();

    queue.pop();
    queue.printQueue();

    System.out.println("Decrease 5 to 0");
    queue.priority(5, 0);
    queue.printQueue();

    queue.pop();
    queue.printQueue();

    queue.pop();
    queue.printQueue();

    queue.pop();
    queue.printQueue();

    queue.pop();
    queue.printQueue();
  }
}
