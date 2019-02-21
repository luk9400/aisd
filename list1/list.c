#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  int key;
  struct Node* next;
} node_t;

/*
 * Appends key to the end of the list
 */
void insert(node_t* list, int key) {
  node_t* node = list;
  // moving to the end of the list
  while (node->next != NULL) {
    node = node->next;
  }
  node->next = malloc(sizeof(node_t));
  node->next->key = key;
  node->next->next = NULL;
}

/*
 * Returns pointer to the first encountered key.
 * If key is not in the list returns NULL.
 */
node_t* search(node_t* list, int key) {
  node_t* node = list;
  while (node != NULL && node->key != key) {
    node = node->next;
  }
  return(node);
}

void delete(node_t* list, int key) {

}

/*
 * Finds first encountered key and moves it to the beginning of the list.
 * If there is no such key in the list it does nothing.
 */
void find_mtf(node_t** list, int key) {
  if ((*list)->key == key) {
    return;
  }
  node_t* node = *list;
  while(node->next != NULL && node->next->key != key) {
    node = node->next;
    if (node->next == NULL) {
      node = NULL;
      break;
    }
  }
  if (node == NULL || (node == *list && node->next->key != key)) {
    return;
  }
  node_t* tmp = node->next;
  node->next = tmp->next;
  tmp->next = *list;
  *list = tmp;
}

/*
 * Prints the whole list
 */
void print_list(node_t* list) {
  node_t* node = list;
  while (node != NULL) {
    printf("%d\n", node->key);
    node = node->next;
  }
}

int main() {
  node_t* head = malloc(sizeof(node_t));
  node_t* node1 = malloc(sizeof(node_t));
  node_t* node2 = malloc(sizeof(node_t));
  head->key = 1;
  head->next = node1;
  node1->key = 2;
  node1->next = node2;
  node2->key = 3;
  node2->next = NULL;
  insert(head, 4);
  print_list(head);

  printf("Magic happens\n");

  find_mtf(&head, 3);
  print_list(head);

  return 0;
}
