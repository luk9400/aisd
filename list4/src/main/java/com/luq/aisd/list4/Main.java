package com.luq.aisd.list4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void bst() {
    BST bst = new BST();
    bst.load("lotr.txt");
    System.out.println("done");
    bst.inorder();
  }

  public static void rbt() {
    RBT rbt = new RBT();
    rbt.load("lotr.txt");
    System.out.println("done");
    rbt.inorder();
  }

  public static void splay() {
    Splay splay = new Splay();
    splay.load("KJB.txt");
    System.out.println("done");
    splay.inorder();
  }

  public static void program(BinaryTree tree) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Number of operations:");
    int numOfOps = scanner.nextInt();
    System.out.println("Operations:");
    for (int i = 0; i < numOfOps; i++) {
      String str = scanner.next();
      switch (str) {
        case "insert": {
          String key = scanner.next();
          tree.insert(key);
          break;
        }
        case "delete": {
          String key = scanner.next();
          tree.delete(key);
          break;
        }
        case "search": {
          String key = scanner.next();
          tree.search(key);
          break;
        }
        case "load": {
          String key = scanner.next();
          tree.load(key);
          break;
        }
        case "inorder": {
          tree.inorder();
          break;
        }
        case "test": {
          System.out.println("Number of tests: ");
          int num = scanner.nextInt();
          System.out.println("Starting tests");
          try {
            runTests(tree, num);
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        }
        default: {
          System.out.println("No such command");
        }
      }
    }
  }

  public static void runTests(BinaryTree tree, int numberOfTests) throws IOException {
    String treeType = tree.getClass().getSimpleName();
    String[] fileNames = {"aspell_wordlist", "kjb", "lotr", "sample"};
    for (String fileName : fileNames) {
      System.out.println(fileName);

      FileWriter writer = new FileWriter(new File(treeType + "_" + fileName + ".csv"));
      writer.append("insert_time;insert_comparations;insert_modifications;" +
              "search_time;search_comparations;search_modifications;" +
              "delete_time;delete_comparations;delete_modifications\n");
      long start;
      long end;

      for (int i = 0; i < numberOfTests; i++) {

        System.out.println("Loading");
        tree.resetCounters();
        start = System.nanoTime();
        tree.load(fileName + ".txt");
        end = System.nanoTime();

        writer.append(String.valueOf((end - start) / 1000000000d)).append(";");
        writer.append(String.valueOf(tree.getComparations())).append(";");
        writer.append(String.valueOf(tree.getModifications())).append(";");

        System.out.println("Searching");
        tree.resetCounters();
        start = System.nanoTime();
        tree.searchLoaded(fileName + ".txt");
        end = System.nanoTime();

        writer.append(String.valueOf((end - start) / 1000000000d)).append(";");
        writer.append(String.valueOf(tree.getComparations())).append(";");
        writer.append(String.valueOf(tree.getModifications())).append(";");

        System.out.println("Deleting");
        tree.resetCounters();
        start = System.nanoTime();
        tree.deleteLoaded(fileName + ".txt");
        end = System.nanoTime();

        writer.append(String.valueOf((end - start) / 1000000000d)).append(";");
        writer.append(String.valueOf(tree.getComparations())).append(";");
        writer.append(String.valueOf(tree.getModifications())).append(";");

        writer.append("\n");
      }
      writer.flush();
      writer.close();
    }
    System.out.println("Tests done");
  }

  public static void main(String[] args) {

    if (args.length > 1) {
      if (args[0].equals("--type")) {
        switch (args[1]) {
          case "bst": {
            BST bst = new BST();
            program(bst);
            bst.printStats();
            break;
          }
          case "rbt": {
            RBT rbt = new RBT();
            program(rbt);
            rbt.printStats();
            break;
          }
          case "splay": {
            Splay splay = new Splay();
            program(splay);
            splay.printStats();
            break;
          }
          default: {
            System.out.println("There is no such tree");
          }
        }
      } else {
        System.out.println("Wrong argument. Try '--type'");
      }
    } else {
      System.out.println("You need to specify tree type");
    }
  }
}
