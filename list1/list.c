#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  int key;
  struct Node* next;
} node_t;

/*
 * Appends key to the end of the list
 */
void insert(node_t** list, int key) {
  if (*list == NULL) {
    *list = malloc(sizeof(node_t));
    (*list)->key = key;
    (*list)->next = NULL;
    return;
  }
  node_t* node = *list;
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
  return node;
}

/*
 * Returns 1 if list is empty. Returns 0 otherwise.
 */
int is_empty(node_t* list) {
  return list == NULL ? 1 : 0;
}

/*
 * Deletes first encountered node with specified key.
 */
void delete(node_t** list, int key) {
  if (is_empty(*list)) {
    return;
  }
  node_t* node = *list;

  //deleting first element if key fits
  if ((*list)->key == key) {
    *list = node->next;
    free(node);
    return;
  }

  while(node->next != NULL && node->next->key != key) {
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

/*
 * Finds first encountered key and moves it to the beginning of the list.
 * If there is no such key in the list it does nothing.
 */
void find_mtf(node_t** list, int key) {
  if (is_empty(*list)) {
    return;
  }
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

  node_t* head = NULL;
  //printf("%d\n", is_empty(head));
  insert(&head, 1);
  insert(&head, 2);
  insert(&head, 3);
  insert(&head, 4);
  insert(&head, 5);
  //find_mtf(&head, 2137);
  print_list(head);

  delete(&head, 3);
  print_list(head);

  return 0;
}
