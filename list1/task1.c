#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int counter = 0;

typedef struct Node {
  int key;
  struct Node* next;
} node_t;

typedef struct List {
  node_t* head;
} List;

/**
 * Appends key to the end of the list
 */
void insert(List* list, int key) {
  if (list->head == NULL) {
    list->head = malloc(sizeof(node_t));
    list->head->key = key;
    list->head->next = NULL;
    return;
  }
  node_t* node = list->head;
  // moving to the end of the list
  while (node->next != NULL) {
    node = node->next;
  }
  node->next = malloc(sizeof(node_t));
  node->next->key = key;
  node->next->next = NULL;
}

/**
 * Returns pointer to the first encountered key.
 * If key is not in the list returns NULL.
 */
node_t* search(List* list, int key) {
  node_t* node = list->head;
  while (node != NULL && node->key != key) {
    node = node->next;
  }
  return node;
}

/**
 * Returns 1 if list is empty. Returns 0 otherwise.
 */
int is_empty(List* list) {
  return list->head == NULL;
}

/**
 * Deletes first encountered node with specified key.
 */
void delete(List* list, int key) {
  if (is_empty(list)) {
    return;
  }
  node_t* node = list->head;

  //deleting first element if key fits
  counter++;
  if (list->head->key == key) {
    list->head = node->next;
    free(node);
    return;
  }

  while(node->next != NULL && node->next->key != key) {
    counter++;
    node = node->next;
    if (node->next == NULL) {
      node = NULL;
      break;
    }
  }
  if (node == NULL) {
    return;
  }
  node_t* tmp = node->next;
  node->next = node->next->next;
  free(tmp);
}

/**
 * Finds first encountered key and moves it to the beginning of the list.
 * Then returns 1.
 * If there is no such key in the list it returns 0.
 */
int find_mtf(List* list, int key) {
  if (is_empty(list)) {
    return 0;
  }
  counter++;
  if (list->head->key == key) {
    return 1;
  }
  node_t* node = list->head;
  while(node->next != NULL && node->next->key != key) {
    counter++;
    node = node->next;
  }
  counter++;
  if (node->next == NULL) {
    return 0;
  }
  node_t* tmp = node->next;
  node->next = tmp->next;
  tmp->next = list->head;
  list->head = tmp;
  return 1;
}

/**
 * Finds first encountered key and moves it one place to the front.
 * Returns 1 if success or 0 if key wasn't found.
 */
int find_trans(List* list, int key) {
  if (is_empty(list)) {
    return 0;
  }
  // checking first node
  counter++;
  if (list->head->key == key) {
    return 1;
  }
  node_t* node = list->head;
  //checking second node
  if (node->next == NULL) {
    return 0;
  } else {
    counter++;
    if (node->next->key == key) {
      node_t* tmp = node->next;
      node->next = tmp->next;
      tmp->next = list->head;
      list->head = tmp;
      return 1;
    }
  }
  while (node->next->next != NULL) {
    counter++;
    if (node->next->next->key == key) {
      node_t* next = node->next->next->next;
      node_t* found = node->next->next;
      node->next->next->next = node->next;
      node->next->next = next;
      node->next = found;
      return 1;
    }
    node = node->next;
  }

  return 0;
}

/**
 * Prints the whole list
 */
void print_list(List* list) {
  node_t* node = list->head;
  while (node != NULL) {
    printf("%d\n", node->key);
    node = node->next;
  }
}

void test(List* list) {
  // creating tab
  int randomTab[100];
  for (int i = 0; i < 100; i++) {
    randomTab[i] = i+1;
  }
  // shuffle
  srand(time(NULL));
  for (int i = 0; i < 100; i++) {
    int randomIndex = rand() % 100;
    int tmp = randomTab[i];
    randomTab[i] = randomTab[randomIndex];
    randomTab[randomIndex] = tmp;
  }
  // inserting into the list
  for (int i = 0; i < 100; i++) {
    insert(list, randomTab[i]);
  }

  // counting trans
  counter = 0;
  while(!is_empty(list)) {
    int max;
    for (int i = 0; i < 100; i++) {
      if (find_trans(list, i + 1)) {
        max = i + 1;
      }
    }
    delete(list, max);
  }
  printf("TRANS: %d\n", counter);
  // inserting into the list
  for (int i = 0; i < 100; i++) {
    insert(list, randomTab[i]);
  }

  // counting mtf
  counter = 0;
  while(!is_empty(list)) {
    int max;
    for (int i = 0; i < 100; i++) {
      if (find_mtf(list, i + 1)) {
        max = i + 1;
      }
    }
    delete(list, max);
  }
  printf("MTF: %d\n", counter);
}

int main() {

  List list = {NULL};
  test(&list);
  //printf("%d\n", is_empty(&list));
  //insert(&list, 1);
  //insert(&list, 2);
  //insert(&list, 3);
  //insert(&list, 4);
  //insert(&list, 5);
  //print_list(&list);
  //printf("%d\n", is_empty(&list));
  //find_trans(&list, 5);
  //find_mtf(&list, 2);
  //printf("%d\n", find_mtf(&list, 5));
  //delete(&list, 1);
  //printf("%d\n", is_empty(&list));
  //print_list(&list);

  return 0;
}
