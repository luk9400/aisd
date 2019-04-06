#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <sys/time.h>

char type_flag = '0';
int desc_flag = 0;
int stat_flag = 0;

typedef struct Stats {
  int comparations;
  int swaps;
  double time;
} Stats;

int greater(int a, int b) {
  return a > b;
}

int less(int a,int b) {
  return a < b;
}

int greater_or_equal(int a, int b) {
  return a >= b;
}

int less_or_equal(int a, int b) {
  return a <= b;
}

void select_sort(int* tab, int size, int (*compare)(int, int), Stats* stats) {
  struct timeval begin;
  struct timeval end;
  gettimeofday(&begin, NULL);
  for (int i = 0; i < size - 1; i++) {
    int min = i;
    for (int j = i + 1; j < size; j++) {
      stats->comparations++;
      if ((*compare)(tab[j], tab[min])) {
        min = j;
      }
    }
    if (min != i) {
      stats->swaps++;
      int tmp = tab[i];
      tab[i] = tab[min];
      tab[min] = tmp;
    }
  }
  gettimeofday(&end, NULL);
  stats->time = (end.tv_sec - begin.tv_sec) + (end.tv_usec - begin.tv_usec) / 1000000.0;
}

void insert_sort(int* tab, int size, int (*compare)(int, int), Stats* stats) {
  struct timeval begin;
  struct timeval end;
  gettimeofday(&begin, NULL);
  for (int i = 1; i < size; i++) {
    int j = i - 1;
    int value = tab[i];
    stats->comparations++;
    for ( ; j >= 0 && (*compare)(value, tab[j]); j--) {
      stats->comparations++;
      stats->swaps++;
      tab[j + 1] = tab[j];
    }
    stats->swaps++;
    tab[j + 1] = value;
  }
  gettimeofday(&end, NULL);
  stats->time = (end.tv_sec - begin.tv_sec) + (end.tv_usec - begin.tv_usec) / 1000000.0;
}

int partition(int* tab, int p , int r, int (*compare)(int, int), Stats* stats) {
  int x = tab[p];
  int i = p;
  int j = r;
  while (1) {
    stats->comparations++;
    while (!(*compare)(tab[j], x) && tab[j] != x) {
      stats->comparations++;
      j--;
    }
    stats->comparations++;
    while ((*compare)(tab[i], x)) {
      stats->comparations++;
      i++;
    }
    if (i < j) {
      stats->swaps++;
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

void quick_sort(int* tab, int p, int r, int (*compare)(int, int), Stats* stats) {
  struct timeval begin;
  struct timeval end;
  gettimeofday(&begin, NULL);
  if (p < r) {
    int q = partition(tab, p, r, (*compare), stats);
    quick_sort(tab, p, q, (*compare), stats);
    quick_sort(tab, q + 1, r, (*compare), stats);
  }
  gettimeofday(&end, NULL);
  stats->time = (end.tv_sec - begin.tv_sec) + (end.tv_usec - begin.tv_usec) / 1000000.0;
}

int m_partition(int* tab, int p, int r, int (*compare)(int, int), Stats* stats) {
  int median[] = {tab[p], tab[(p + r) >> 1], tab[r]};
  insert_sort(median, 3, &less, stats);
  int x = median[1];
  int i = p;
  int j = r;
  while (1) {
    stats->comparations++;
    while (!(*compare)(tab[j], x) && tab[j] != x) {
      stats->comparations++;
      j--;
    }
    stats->comparations++;
    while ((*compare)(tab[i], x)) {
      stats->comparations++;
      i++;
    }
    if (i < j) {
      stats->swaps++;
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

void minsert_sort(int* tab, int first, int size, int (*compare)(int, int), Stats* stats) {
  for (int i = first + 1; i < size; i++) {
    int j = i - 1;
    int value = tab[i];
    stats->comparations++;
    for ( ; j >= 0 && (*compare)(value, tab[j]); j--) {
      stats->comparations++;
      stats->swaps++;
      tab[j + 1] = tab[j];
    }
    stats->swaps++;
    tab[j + 1] = value;
  }
}

void mquick_sort(int* tab, int p, int r, int (*compare)(int, int), Stats* stats) {
  struct timeval begin;
  struct timeval end;
  gettimeofday(&begin, NULL);

  if (r - p <= 16) {
    minsert_sort(tab, p, r + 1, (*compare), stats);
  } else {
    int q = m_partition(tab, p, r, (*compare), stats);
    mquick_sort(tab, p, q, (*compare), stats);
    mquick_sort(tab, q + 1, r, (*compare), stats);
  }

  gettimeofday(&end, NULL);
  stats->time = (end.tv_sec - begin.tv_sec) + (end.tv_usec - begin.tv_usec) / 1000000.0;
}

static inline int parent(int i) {
  return i >> 1;
}

static inline int left(int i) {
  return (i << 1) + 1;
}

static inline int right(int i) {
  return (i << 1) + 2;
}

void heapify(int* tab, int i, int size, int (*compare)(int, int), Stats* stats) {
  int l = left(i);
  int r = right(i);
  int largest = i;
  stats->comparations++;
  if (l < size && (*compare)(tab[l], tab[largest])) {
    largest = l;
  }
  stats->comparations++;
  if (r < size && (*compare)(tab[r], tab[largest])) {
    largest = r;
  }
  if (largest != i) {
    stats->swaps++;
    int tmp = tab[i];
    tab[i] = tab[largest];
    tab[largest] = tmp;
    heapify(tab, largest, size, (*compare), stats);
  }
}

void build_heap(int* tab, int size, int (*compare)(int, int), Stats* stats) {
  for (int i = (size >> 1) - 1; i >= 0; i--) {
    heapify(tab, i, size, (*compare), stats);
  }
}

void heap_sort(int* tab, int size, int (*compare)(int, int), Stats* stats) {
  struct timeval begin;
  struct timeval end;
  gettimeofday(&begin, NULL);
  build_heap(tab, size, (*compare), stats);
  for (int i = size - 1; i >= 0; i--) {
    stats->swaps++;
    int tmp = tab[i];
    tab[i] = tab[0];
    tab[0] = tmp;
    size--;
    heapify(tab, 0, size, (*compare), stats);
  }
  gettimeofday(&end, NULL);
  stats->time += (end.tv_sec - begin.tv_sec) + (end.tv_usec - begin.tv_usec) / 1000000.0;
}

void print_tab(int* tab, int size) {
  printf("[");
  for (int i = 0; i < size; i++) {
    if (i == size - 1) {
      printf("%d", tab[i]);
    } else {
      printf("%d, ", tab[i]);
    }
  }
  printf("]\n");
}

int is_sorted(int* tab, int size, int (*compare)(int, int)) {
  for (int i = 0; i < size - 1; i++) {
    if (!(*compare)(tab[i], tab[i + 1])) {
      return 0;
    }
  }
  return 1;
}

void init_stats(Stats* stats) {
  stats->comparations = 0;
  stats->swaps = 0;
  stats->time = 0;
}

void print_stats(Stats* stats) {
  printf("Comparations: %d\nSwaps: %d\nTime: %f\n", stats->comparations, stats->swaps, stats->time);
}

int main(int argc, char** argv) {

  srand(time(NULL));
  int c;
  int option_index;
  char* file_name;
  FILE* f;
  int k;
  static struct option long_options[] = {
    {"asc", no_argument, 0, 'a'},
    {"desc", no_argument, 0, 'd'},
    {"type", required_argument, 0, 't'},
    {"stat", required_argument, 0, 's'},
    {0, 0, 0, 0}
  };

  while ((c = getopt_long(argc, argv, "ts", long_options, &option_index)) != -1) {
    option_index = 0;

    switch (c) {
      case 'a': {
        desc_flag = 0;
        break;
      }
      case 'd': {
        desc_flag = 1;
        break;
      }
      case 's': {
        stat_flag = 1;
        file_name = strdup(argv[optind - 1]);
        k = atoi(strdup(argv[optind]));
        break;
      }
      case 't': {
        if (strcmp(optarg, "select") == 0) {
          type_flag = 's';
        } else if (strcmp(optarg, "insert") == 0) {
          type_flag = 'i';
        } else if (strcmp(optarg, "quick") == 0) {
          type_flag = 'q';
        } else if (strcmp(optarg, "heap") == 0) {
          type_flag = 'h';
        } else if (strcmp(optarg, "mquick") == 0) {
          type_flag = 'm';
        }
        break;
      }
      default: {
        abort();
      }
    }
  }

  if (stat_flag) {
    f = fopen(file_name, "w");
    fprintf(f, "n");
    char* algorithms[5] = {"select", "insert", "heap", "quick", "mquick"};
    for (int i = 0; i < 5; i++) {
      fprintf(f, ";%s - comparations;%s - swaps;%s - time;%s - comparations/n;%s - swaps/n", algorithms[i], algorithms[i], algorithms[i], algorithms[i], algorithms[i]);
    }
    fprintf(f , "\n");
    Stats* stats = malloc(5 * sizeof(Stats));
    Stats* avg_stats = malloc(5 * sizeof(Stats));
    for (int n = 100; n <= 10000; n = n + 100) {
      int* random_array = malloc(n * sizeof(int));
      int* array = malloc(n * sizeof(int));
      for (int i = 0; i < 5; i++) {
        init_stats(&avg_stats[i]);
      }
      for (int i = 0; i < k; i++) {
        for (int j = 0; j < n; j++) {
          random_array[j] = rand() % 100;
        }
        for (int j = 0; j < 5; j++) {
          init_stats(&stats[i]);
        }

        memcpy(array, random_array, n);
        select_sort(array, n, &less, &stats[0]);

        memcpy(array, random_array, n);
        insert_sort(array, n, &less, &stats[1]);

        memcpy(array, random_array, n);
        heap_sort(array, n, &greater, &stats[2]);

        memcpy(array, random_array, n);
        quick_sort(array, 0, n - 1, &less, &stats[3]);

        memcpy(array, random_array, n);
        mquick_sort(array, 0, n - 1, &less, &stats[4]);

        for (int j = 0; j < 5; j++) {
          avg_stats[j].comparations += stats[j].comparations;
          avg_stats[j].swaps += stats[j].swaps;
          avg_stats[j].time += stats[j].time;
        }
      }
      fprintf(f, "%d", n);
      for (int i = 0; i < 5; i++) {
        int c = avg_stats[i].comparations / k;
        int s = avg_stats[i].swaps / k;
        double t = avg_stats[i].time / k;
        int cn = c / n;
        int sn = s / n;
        fprintf(f, ";%d;%d;%f;%d;%d", c, s, t, cn, sn);
      }
      fprintf(f, "\n");

      free(random_array);
      free(array);
    }
    free(stats);
    free(avg_stats);
    fclose(f);
  } else if (type_flag != '0'){
    int size;
    printf("How many elements do you wish to sort?\n");
    scanf("%d", &size);
    int* tab = malloc(size * sizeof(int));
    printf("What elements?\n");
    for (int i = 0; i < size; i++) {
      scanf("%d", &tab[i]);
    }
    Stats stats = {0, 0, 0};
    switch (type_flag) {
      case 's': {
        if (desc_flag) {
          select_sort(tab, size, &greater, &stats);
        } else {
          select_sort(tab, size, &less, &stats);
        }
        break;
      }
      case 'i': {
        if (desc_flag) {
          insert_sort(tab, size, &greater, &stats);
        } else {
          insert_sort(tab, size, &less, &stats);
        }
        break;
      }
      case 'q': {
        if (desc_flag) {
          quick_sort(tab, 0, size - 1, &greater, &stats);
        } else {
          quick_sort(tab, 0, size - 1, &less, &stats);
        }
        break;
      }
      case 'h': {
        if (desc_flag) {
          heap_sort(tab, size, &less, &stats);
        } else {
          heap_sort(tab, size, &greater, &stats);
        }
        break;
      }
      case 'm': {
        if (desc_flag) {
          mquick_sort(tab, 0, size - 1, &greater, &stats);
        } else {
          mquick_sort(tab, 0, size - 1, &less, &stats);
        }
        break;
      }
    }
    if (desc_flag) {
      printf("Is sorted: %d\n", is_sorted(tab, size, &greater_or_equal));
    } else {
      printf("Is sorted: %d\n", is_sorted(tab, size, &less_or_equal));
    }
    printf("Sorted %d elements:\n", size);
    print_tab(tab, size);
    print_stats(&stats);
  }

  return 0;
}
