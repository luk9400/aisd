#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include <string.h>

char type_flag = '0';
int desc_flag = 0;
int stat_flag = 0;

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

int select_sort(int* tab, int size, int (*compare)(int, int)) {
  // comparations and swaps
  int stats[] = {0, 0};

  for (int i = 0; i < size - 1; i++) {
    int min = i;
    for (int j = i + 1; j < size; j++) {
      stats[0]++;
      if ((*compare)(tab[j], tab[min])) {
        min = j;
      }
    }
    if (min != i) {
      stats[1]++;
      int tmp = tab[i];
      tab[i] = tab[min];
      tab[min] = tmp;
    }
  }
  //return stats;
}

int insert_sort(int* tab, int size, int (*compare)(int, int)) {
  for (int i = 1; i < size; i++) {
    int j = i - 1;
    int value = tab[i];
    for ( ; j >= 0 && (*compare)(value, tab[j]); j--) {
      tab[j + 1] = tab[j];
    }
    tab[j + 1] = value;
  }
}

int partition(int* tab, int p , int r, int (*compare)(int, int)) {
  int x = tab[p];
  int i = p;
  int j = r;
  while (1) {
    while (!(*compare)(tab[j], x) && tab[j] != x) {
      j--;
    }
    while ((*compare)(tab[i], x)) {
      i++;
    }
    if (i < j) {
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

int quick_sort(int* tab, int p, int r, int (*compare)(int, int)) {
  if (p < r) {
    int q = partition(tab, p, r, (*compare));
    quick_sort(tab, p, q, (*compare));
    quick_sort(tab, q + 1, r, (*compare));
  }
}

int mquick_sort(int* tab, int p, int r, int (*compare)(int, int)) {

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

int heapify(int* tab, int i, int size, int (*compare)(int, int)) {
  int l = left(i);
  int r = right(i);
  int largest = i;
  if (l < size && (*compare)(tab[l], tab[largest])) {
    largest = l;
  }
  if (r < size && (*compare)(tab[r], tab[largest])) {
    largest = r;
  }
  if (largest != i) {
    int tmp = tab[i];
    tab[i] = tab[largest];
    tab[largest] = tmp;
    heapify(tab, largest, size, (*compare));
  }
}

int build_heap(int* tab, int size, int (*compare)(int, int)) {
  for (int i = (size >> 1) - 1; i >= 0; i--) {
    heapify(tab, i, size, (*compare));
  }
}

int heap_sort(int* tab, int size, int (*compare)(int, int)) {
  build_heap(tab, size, (*compare));
  for (int i = size - 1; i >= 0; i--) {
    int tmp = tab[i];
    tab[i] = tab[0];
    tab[0] = tmp;
    size--;
    heapify(tab, 0, size, (*compare));
  }
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

int main(int argc, char** argv) {

  int c;
  int option_index;
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
        // TODO
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
    // TODO
  } else {
    int size;
    printf("How many elements do you wish to sort?\n");
    scanf("%d", &size);
    int* tab = malloc(size * sizeof(int));
    printf("What elements?\n");
    for (int i = 0; i < size; i++) {
      scanf("%d", &tab[i]);
    }
    int* stats;
    switch (type_flag) {
      case 's': {
        if (desc_flag) {
          select_sort(tab, size, &greater);
        } else {
          select_sort(tab, size, &less);
        }
        break;
      }
      case 'i': {
        if (desc_flag) {
          insert_sort(tab, size, &greater);
        } else {
          insert_sort(tab, size, &less);
        }
        break;
      }
      case 'q': {
        if (desc_flag) {
          quick_sort(tab, 0, size - 1, &greater);
        } else {
          quick_sort(tab, 0, size - 1, &less);
        }
        break;
      }
      case 'h': {
        if (desc_flag) {
          heap_sort(tab, size, &less);
        } else {
          heap_sort(tab, size, &greater);
        }
        break;
      }
      case 'm': {
        if (desc_flag) {
          mquick_sort(tab, 0, size - 1, &greater);
        } else {
          mquick_sort(tab, 0, size - 1, &less);
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
    //printf("Comparations: %d, swaps: %d\n", stats[0], stats[1]);
  }

  return 0;
}
