package com.luq.aisd.list5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static void test(int reps) {
    try {
      FileWriter writer = new FileWriter(new File("stats.csv"));

      writer.append("i;flow;paths;time;\n");

      System.out.println("Starting tests");
      for (int i = 1; i <= 16; i++) {
        System.out.println(i);
        double avgFlow = 0;
        double avgPaths = 0;
        double avgTime = 0;

        for (int j = 0; j < reps; j++) {
          Graph graph = new Graph(i);
          long start = System.nanoTime();
          graph.edmondsKarp();
          long end = System.nanoTime();
          double time = (end - start) / 1000000000d;

          avgFlow += graph.getMaxFlow() / (reps * 1d);
          avgPaths += graph.getPaths() / (reps * 1d);
          avgTime += time / reps;
        }
        writer.append(i + ";" + avgFlow + ";" + avgPaths + ";" + avgTime + ";\n");
      }

      writer.flush();
      writer.close();
      System.out.println("Done");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    test(3);
  }
}
