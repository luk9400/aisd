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

int select_sort(int* tab, int size, int (*compare)(int, int)) {
  for (int i = 0; i < size - 1; i++) {
    int min = i;
    for (int j = i + 1; j < size; j++) {
      if ((*compare)(tab[j], tab[min])) {
        min = j;
      }
    }
    if (min != i) {
      int tmp = tab[i];
      tab[i] = tab[min];
      tab[min] = tmp;
    }
  }
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
  for (int i = 0; i < size; i++) {
    printf("%d\n", tab[i]);
  }
}

int main() {
  // int size = 5;
  // int* tab = malloc(size * sizeof(int));
  // tab[0] = 2;
  // tab[1] = 1;
  // tab[2] = 1;
  // tab[3] = 7;
  // tab[4] = 3;

  int c;
  int option_index;
  struct option long_options[] = {
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
        break;
      }
    }

    if (stat_flag) {

    } else {
      int size;
      printf("How many elements do you wish to sort?\n");
      scanf("%d\n", &size);
      int* tab = malloc(size * sizeof(int));
      printf("What elements?\n");
      for (int i = 0; i < size; i++) {
        scanf("%d\n", tab[i]);
      }

      switch (type_flag) {
        case 's': {
          if (desc_flag) {
            select_sort(tab, size, &less);
          } else {
            select_sort(tab, size, &greater);
          }
          break;
        }
        case 'i': {
          if (desc_flag) {
            insert_sort(tab, size, &less);
          } else {
            insert_sort(tab, size, &greater);
          }
          break;
        }
        case 's': {
          if (desc_flag) {
            select_sort(tab, size, &less);
          } else {
            select_sort(tab, size, &greater);
          }
          break;
        }
        case 's': {
          if (desc_flag) {
            select_sort(tab, size, &less);
          } else {
            select_sort(tab, size, &greater);
          }
          break;
        }
        case 's': {
          if (desc_flag) {
            select_sort(tab, size, &less);
          } else {
            select_sort(tab, size, &greater);
          }
          break;
        }
      }
      print_tab(tab, size);
    }

  }
  // for (int i = 0; i < 1000; i++) {
  //   tab[i] = i;
  // }

  //select_sort(tab, size, &less);
  //insert_sort(tab, size, &less);
  //quick_sort(tab, 0, size - 1, &less);
  //heap_sort(tab, size, &greater);

  //print_tab(tab, size);

  return 0;
}
