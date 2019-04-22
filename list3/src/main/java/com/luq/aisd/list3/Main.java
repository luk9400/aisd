package com.luq.aisd.list3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  private static Graph getGraph() {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("./graph2.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    //System.out.println("Number of verticies:");
    int numOfVerticies = scanner.nextInt();
    //System.out.println("Number of edges:");
    int e = scanner.nextInt();

    Graph graph = new Graph(numOfVerticies);

    for (int i = 0; i < e; i++) {
      int u, v, w;
      u = scanner.nextInt();
      v = scanner.nextInt();
      w = scanner.nextInt();
      graph.addWeightEdge(u, v, w);
      //graph.addEdge(u, v);
    }

    return graph;
  }

  private static void dijkstra() {
    Graph graph = getGraph();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Starting vertex:");
    int start = scanner.nextInt();

    Dijkstra dijkstra = new Dijkstra(graph);

    dijkstra.dijkstra(start);
  }

  private static void priorityQueue() {
    Scanner scanner = new Scanner(System.in);
    PriorityQueue queue = new PriorityQueue();
    System.out.println("Number of operations:");
    int numOfOps = scanner.nextInt();
    System.out.println("Operations:");
    for (int i = 0; i < numOfOps; i++) {
      String str = scanner.next();
      switch (str) {
        case "insert": {
          int x = scanner.nextInt();
          int p = scanner.nextInt();
          queue.insert(x, p);
          break;
        }
        case "empty": {
          System.out.println(queue.isEmpty());
          break;
        }
        case "top": {
          queue.top();
          break;
        }
        case "pop": {
          queue.pop();
          break;
        }
        case "priority": {
          int x = scanner.nextInt();
          int p = scanner.nextInt();
          queue.priority(x, p);
          break;
        }
        case "print": {
          queue.printQueue();
          break;
        }
        default: {
          System.out.println("Error");
          break;
        }
      }
    }
  }

  public static void kruskal() {
    Graph graph = getGraph();

    Kruskal kruskal = new Kruskal(graph);

    kruskal.kruskal();
  }

  public static void prim() {
    Graph graph = getGraph();

    Prim prim = new Prim(graph);

    prim.prim(0);
  }

  public static void ssc() {
    Graph graph = getGraph();
    SCC ssc = new SCC(graph);

    ssc.scc();

  }

  public static void main(String[] args) {
    //dijkstra();
    //priorityQueue();
    //kruskal();
    //prim();
    ssc();
  }
}
