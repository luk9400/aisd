#include <stdio.h>
#include <stdlib.h>

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
  int size = 5;
  int* tab = malloc(size * sizeof(int));
  tab[0] = 2;
  tab[1] = 1;
  tab[2] = 1;
  tab[3] = 7;
  tab[4] = 3;

  // for (int i = 0; i < 1000; i++) {
  //   tab[i] = i;
  // }

  //select_sort(tab, size, &less);
  //insert_sort(tab, size, &less);
  //quick_sort(tab, 0, size - 1, &less);
  heap_sort(tab, size, &greater);

  print_tab(tab, size);

  return 0;
}
