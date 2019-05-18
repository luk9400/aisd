package com.luq.aisd.list4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class BinaryTree {
  public long getComparations() {
    return comparations;
  }

  public long getModifications() {
    return modifications;
  }

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
      BufferedReader reader = new BufferedReader(new FileReader("./" + filename));

      String line;
      while ((line = reader.readLine()) != null) {
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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void searchLoaded(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("./" + filename));

      String line;
      while ((line = reader.readLine()) != null) {
        String[] strings = line.split("[ ,;:]");

        for (String str : strings) {
          if (str != null && !str.equals("")) {
            search(StringReducer.reduceString(str));
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not found!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void deleteLoaded(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("./" + filename));

      String line;
      while ((line = reader.readLine()) != null) {
        String[] strings = line.split("[ ,;:]");

        for (String str : strings) {
          if (str != null && !str.equals("")) {
            delete(StringReducer.reduceString(str));
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not found!");
    } catch (IOException e) {
      e.printStackTrace();
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

  public void resetCounters() {
    comparations = 0;
    modifications = 0;
  }
}
