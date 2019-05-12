package com.luq.aisd.list3;

import java.io.File;
import java.util.Scanner;

public class Main {

  private static Graph loadGraphFromFile() {
    try {
      Scanner scanner = new Scanner(new File("./3graph.txt"));

      int numOfVerticies = scanner.nextInt();
      int e = scanner.nextInt();

      Graph graph = new Graph(numOfVerticies);
      scanner.nextLine();

      for (int i = 0; i < e; i++) {
        String input  = scanner.nextLine();
        String[] line = input.split(" ");

        int u = Integer.parseInt(line[0]);
        int v = Integer.parseInt(line[1]);
        float w = Float.parseFloat(line[2]);
        graph.addWeightEdge(u, v, w);
      }

      return graph;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void dijkstra() {
    Graph graph = loadGraphFromFile();
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
    Graph graph = loadGraphFromFile();

    Kruskal kruskal = new Kruskal(graph);

    kruskal.kruskal();
  }

  public static void prim() {
    Graph graph = loadGraphFromFile();

    Prim prim = new Prim(graph);

    prim.prim(0);
  }

  public static void ssc() {
    Graph graph = loadGraphFromFile();
    SCC ssc = new SCC(graph);

    ssc.scc();
  }

  public static void main(String[] args) {
    //dijkstra();
    //priorityQueue();
    kruskal();
    //prim();
    //ssc();
  }
}
