package com.luq.aisd.list5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static void flowTest(int reps) {
    try {
      FileWriter writer = new FileWriter(new File("statsFlow.csv"));

      writer.append("i;flow;paths;time;\n");

      System.out.println("Starting tests");
      for (int i = 1; i <= 16; i++) {
        System.out.println(i);
        double avgFlow = 0;
        double avgPaths = 0;
        double avgTime = 0;

        for (int j = 0; j < reps; j++) {
          MaxFlow maxFlow = new MaxFlow(i);
          long start = System.nanoTime();
          maxFlow.edmondsKarp();
          long end = System.nanoTime();
          double time = (end - start) / 1000000000d;

          avgFlow += maxFlow.getMaxFlow() / (reps * 1d);
          avgPaths += maxFlow.getPaths() / (reps * 1d);
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

  public static void matchTest(int reps) {
    try {
      FileWriter writerT = new FileWriter(new File("statsMatchTime.csv"));
      FileWriter writerM = new FileWriter(new File("statsMatchMatches.csv"));

      writerT.append("k;1;2;3;4;5;6;7;8;9;10;\n");
      writerM.append("k;1;2;3;4;5;6;7;8;9;10;\n");

      System.out.println("Starting tests");
      for (int k = 3; k <= 10; k++) {
        writerT.append(k + ";");
        writerM.append(k + ";");
        for (int i = 1; i <= k; i++) {
          System.out.println("k: " + k + " i: " + i);
          double avgMatches = 0;
          double avgTime = 0;

          for (int j = 0; j < reps; j++) {
            MaxMatch maxMatch = new MaxMatch(k, i);
            long start = System.nanoTime();
            int matches = maxMatch.maxMatch();
            long end = System.nanoTime();
            double time = (end - start) / 1000000000d;

            avgMatches += matches / (reps * 1d);
            avgTime += time / reps;
          }
          writerT.append(avgTime + ";");
          writerM.append(avgMatches + ";");
        }
        writerT.append("\n");
        writerM.append("\n");
      }

      writerT.flush();
      writerM.flush();
      writerT.close();
      writerM.close();
      System.out.println("Done");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    //flowTest(100);
    matchTest(100);
//    MaxMatch maxMatch = new MaxMatch(5, 5);
//    maxMatch.glpk();
//    MaxFlow maxFlow = new MaxFlow(5);
//    maxFlow.glpk();
  }
}
