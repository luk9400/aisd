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
  insert_sort(tab, size, &greater);

  print_tab(tab, size);

  return 0;
}
