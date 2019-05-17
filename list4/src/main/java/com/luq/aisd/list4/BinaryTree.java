package com.luq.aisd.list4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public abstract class BinaryTree {
  protected long comparations = 0;
  protected long modifications = 0;
  protected int numberOfElements = 0;
  protected int maxNumberOfElements = 0;
  protected int searches = 0;
  protected int inserts = 0;
  protected int deletes = 0;

  abstract void insert(String key);
  abstract void delete(String key);
  abstract void search(String key);
  abstract void inorder();
  public void load(String filename) {
    try {
      Scanner scanner = new Scanner(new FileReader("./" + filename));

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] strings = line.split("[ ,;:]");

        for (String str : strings) {
          if (str != null && !str.equals("")) {
            insert(StringReducer.reduceString(str));
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not found!");
    }
  }

  public void printStats() {
    System.out.println("Comparations: " + comparations);
    System.out.println("Modifications: " + modifications);
    System.out.println("Size: " + numberOfElements);
    System.out.println("Max size: " + maxNumberOfElements);
    System.out.println("Searches: " + searches);
    System.out.println("Inserts: " + inserts);
    System.out.println("Deletes: " + deletes);
  }
}
