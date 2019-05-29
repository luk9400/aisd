package com.luq.aisd.list5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Graph {
  private int k;
  private int[][] capacity;
  private int[][] flow;
  private int[] pi;
  private Random rand;
  private int paths = 0;
  private int maxFlow = 0;

  public int getPaths() {
    return paths;
  }

  public int getMaxFlow() {
    return maxFlow;
  }

  public Graph(int k) {
    this.k = k;
    capacity = new int[1 << k][k];
    rand = new Random();

    for (int i = 0; i < (1 << k); i++) {
      for (int j = 0; j < k; j++) {
        capacity[i][j] = ((i & (1 << j)) == 0) ? randomFlow(i, j) : 0;
      }
    }

    flow = new int[1 << k][k];
    pi = new int[1 << k];
  }

  private int maxHZ(int n) {
    n = n - ((n >> 1) & 0x55555555);
    n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
    n = (((n + (n >> 4)) & 0x0f0f0f0f) * 0x01010101) >> 24;
    return Math.max(n, k - n);
  }

  private int randomFlow(int number, int index) {
    return rand.nextInt(1 << (Math.max(maxHZ(number), maxHZ(number + 1 << index)))) + 1;
  }

  private int bfs() {
    for (int i = 0; i < (1 << k); i++) {
      pi[i] = -1;
    }
    pi[0] = -2;
    int[] M = new int[1 << k];
    M[0] = Integer.MAX_VALUE;
    Queue<Integer> q = new LinkedList<>();
    q.add(0);
    while (!q.isEmpty()) {
      int u = q.poll();
      for (int v = 0; v < k; v++) {
        int p = (u & (1 << v)) == 0 ? u + (1 << v) : u - (1 << v);
        if (capacity[u][v] > flow[u][v] && pi[p] == -1) {
          pi[p] = u;
          M[p] = Math.min(M[u], capacity[u][v] - flow[u][v]);
          if (p != ((1 << k) - 1)) {
            q.add(p);
          } else {
            return M[(1 << k) - 1];
          }
        }
      }
    }
    return 0;
  }

  public int edmondsKarp() {
    while (true) {
      int m = bfs();
      if (m == 0) {
        break;
      }
      paths++;
      maxFlow += m;
      int v = (1 << k) - 1;
      while (v != 0) {
        int u = pi[v];
        int diff = binlog(u ^ v);
        flow[u][diff] += m;
        flow[v][diff] -= m;
        v = u;
      }
    }
    return maxFlow;
  }

  private int binlog(int bits) {
    int log = 0;
    if ((bits & 0xffff0000) != 0) {
      bits >>>= 16;
      log = 16;
    }
    if (bits >= 256) {
      bits >>>= 8;
      log += 8;
    }
    if (bits >= 16) {
      bits >>>= 4;
      log += 4;
    }
    if (bits >= 4) {
      bits >>>= 2;
      log += 2;
    }
    return log + (bits >>> 1);
  }
}
