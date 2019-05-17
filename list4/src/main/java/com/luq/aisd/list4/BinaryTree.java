package com.luq.aisd.list4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public interface BinaryTree {
  void insert(String key);
  void delete(String key);
  void search(String key);
  void inorder();
  default void load(String filename) {
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
}
