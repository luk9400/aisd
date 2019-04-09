import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

  int rand_tab[];
  int tab[];

  char type_flag = '0';
  boolean desc_flag = false;
  int stat_flag = 0;
  
  boolean compare(boolean flag, int a, int b) {
    if (desc_flag) {
      return a < b;
    } else {
      return a > b;
    }
  }

  void select_sort(int[] tab, Stats stats) {
    long start = System.nanoTime();
    for (int i = 0; i < tab.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < tab.length; j++) {
        stats.comparations++;
        if (compare(desc_flag, tab[j], tab[min])) {
          min = j;
        }
      }
      if (min != i) {
        stats.swaps++;
        int tmp = tab[i];
        tab[i] = tab[min];
        tab[min] = tmp;
      }
    }
    long end = System.nanoTime();
    stats.time = end - start;
  }

  void insert_sort(int[] tab, Stats stats) {
    long start = System.nanoTime();
    for (int i = 1; i < tab.length; i++) {
      int j = i - 1;
      int value = tab[i];
      stats.comparations++;
      for ( ; j >= 0 && compare(desc_flag, value, tab[j]); j--) {
        stats.comparations++;
        stats.swaps++;
        tab[j + 1] = tab[j];
      }
      stats.swaps++;
      tab[j + 1] = value;
    }
    long end = System.nanoTime();
    stats.time = end - start;
  }

  int partition(int[] tab, int p , int r, Stats stats) {
    int x = tab[p];
    int i = p;
    int j = r;
    while (true) {
      stats.comparations++;
      while (!compare(desc_flag, tab[j], x) && tab[j] != x) {
        stats.comparations++;
        j--;
      }
      stats.comparations++;
      while (compare(desc_flag, tab[i], x)) {
        stats.comparations++;
        i++;
      }
      if (i < j) {
        stats.swaps++;
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
        i++;
        j--;
      } else {
        return j;
      }
    }
  }

  void quick_sort(int[] tab, int p, int r, Stats stats) {
    if (p < r) {
      int q = partition(tab, p, r, stats);
      quick_sort(tab, p, q, stats);
      quick_sort(tab, q + 1, r, stats);
    }
  }

  void quick(int[] tab, int p, int r, Stats stats) {
    long start = System.nanoTime();
    quick_sort(tab, p, r, stats);
    long end = System.nanoTime();
    stats.time = end - start;
  }

  int m_partition(int[] tab, int p, int r, Stats stats) {
    int median[] = {tab[p], tab[(p + r) >> 1], tab[r]};
    insert_sort(median, stats);
    int x = median[1];
    int i = p;
    int j = r;
    while (true) {
      stats.comparations++;
      while (!compare(desc_flag, tab[j], x) && tab[j] != x) {
        stats.comparations++;
        j--;
      }
      stats.comparations++;
      while (compare(desc_flag, tab[i], x)) {
        stats.comparations++;
        i++;
      }
      if (i < j) {
        stats.swaps++;
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
        i++;
        j--;
      } else {
        return j;
      }
    }
  }

  void minsert_sort(int[] tab, int first, int size, Stats stats) {
    for (int i = first + 1; i < size; i++) {
      int j = i - 1;
      int value = tab[i];
      stats.comparations++;
      for ( ; j >= 0 && compare(desc_flag, value, tab[j]); j--) {
        stats.comparations++;
        stats.swaps++;
        tab[j + 1] = tab[j];
      }
      stats.swaps++;
      tab[j + 1] = value;
    }
  }

  void mquick_sort(int[] tab, int p, int r, Stats stats) {
    if (r - p <= 16) {
      minsert_sort(tab, p, r + 1, stats);
    } else {
      int q = m_partition(tab, p, r, stats);
      mquick_sort(tab, p, q, stats);
      mquick_sort(tab, q + 1, r, stats);
    }
  }

  void mquick(int[] tab, int p, int r, Stats stats) {
    long start = System.nanoTime();
    mquick_sort(tab, p, r, stats);
    long end = System.nanoTime();
    stats.time = end - start;
  }

  int parent(int i) {
    return i >> 1;
  }

  int left(int i) {
    return (i << 1) + 1;
  }

  int right(int i) {
    return (i << 1) + 2;
  }

  void heapify(int[] tab, int i, int size, Stats stats) {
    int l = left(i);
    int r = right(i);
    int largest = i;
    stats.comparations++;
    if (l < size && compare(desc_flag, tab[l], tab[largest])) {
      largest = l;
    }
    stats.comparations++;
    if (r < size && compare(desc_flag, tab[r], tab[largest])) {
      largest = r;
    }
    if (largest != i) {
      stats.swaps++;
      int tmp = tab[i];
      tab[i] = tab[largest];
      tab[largest] = tmp;
      heapify(tab, largest, size, stats);
    }
  }

  void build_heap(int[] tab, int size, Stats stats) {
    for (int i = (size >> 1) - 1; i >= 0; i--) {
      heapify(tab, i, size, stats);
    }
  }

  void heap_sort(int[] tab, int size, Stats stats) {
    long start = System.nanoTime();
    build_heap(tab, size, stats);
    for (int i = size - 1; i >= 0; i--) {
      stats.swaps++;
      int tmp = tab[i];
      tab[i] = tab[0];
      tab[0] = tmp;
      size--;
      heapify(tab, 0, size, stats);
    }
    long end = System.nanoTime();
    stats.time = end - start;
  }

  void printTab(int[] tab) {
    System.out.print("[");
    for (int i = 0; i < tab.length; i++) {
      System.out.print(tab[i] + ", ");
    }
    System.out.print("]\n");
  }

  public static void main(String[] args) {
    Main main = new Main();

    String fileName = "k100.csv";
    int k = 100;

    Random random = new Random();
    String[] algs = new String[]{"select", "insert", "heap", "quick", "mquick"};

    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      writer.write("n");
      for (int i = 0; i < 5; i++) {
        writer.write(";" + algs[i] + " - comparations;" + algs[i] + " - swaps;" + algs[i] + " - time;" + algs[i] + " - comparations/n;" + algs[i] + " - swaps/n");
      }
      writer.write("\n");

      for (int n = 100; n <= 10000; n+=100) {
        main.rand_tab = new int[n];
        main.tab = new int[n];

        Stats[] avg_stats = new Stats[5];
        for (int i = 0; i < 5; i++) {
          avg_stats[i] = new Stats();
        }
        for (int i = 0; i < k; i++) {
          for (int j = 0; j < n; j++) {
            main.rand_tab[j] = random.nextInt();
          }
          Stats[] stats = new Stats[5];
          for (int j = 0; j < 5; j++) {
            stats[j] = new Stats();
          }

          main.tab = main.rand_tab.clone();
          main.select_sort(main.tab, stats[0]);

          main.tab = main.rand_tab.clone();
          main.insert_sort(main.tab, stats[1]);

          main.tab = main.rand_tab.clone();
          main.heap_sort(main.tab, main.tab.length, stats[2]);

          main.tab = main.rand_tab.clone();
          main.quick(main.tab, 0, main.tab.length - 1, stats[3]);

          main.tab = main.rand_tab.clone();
          main.mquick(main.tab, 0, main.tab.length - 1, stats[4]);

          for(int j = 0; j < 5; j++) {
            avg_stats[j].comparations += stats[j].comparations / k;
            avg_stats[j].swaps += stats[j].swaps / k;
            avg_stats[j].time += stats[j].time / k;
          }
        }
        writer.write("" + n);
        for (int i = 0; i < 5; i++) {
          int c = avg_stats[i].comparations;
          int s = avg_stats[i].swaps;
          long t = avg_stats[i].time;
          int cn = c / n;
          int sn = s / n;
          writer.write(";" + c + ";" + s + ";" + t + ";" + cn + ";" + sn);
        }
        writer.write("\n");
        System.out.println(n);
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
