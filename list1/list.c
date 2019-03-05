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
 * Then returns 1.
 * If there is no such key in the list it returns 0.
 */
int find_mtf(node_t** list, int key) {
  if (is_empty(*list)) {
    return 0;
  }
  if ((*list)->key == key) {
    return 1;
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
    return 0;
  }
  node_t* tmp = node->next;
  node->next = tmp->next;
  tmp->next = *list;
  *list = tmp;
  return 1;
}

/*
 * Finds first encountered key and moves it one place to the front.
 * Returns 1 if success or 0 if key wasn't found.
 */
int find_trans(node_t** list, int key) {
  if (is_empty(*list)) {
    return 0;
  }
  // checking first node
  if ((*list)->key == key) {
    return 1;
  }
  node_t* node = *list;
  //checking second node
  if (node->next == NULL) {
    return 0;
  } else {
    if (node->next->key == key) {
      node_t* tmp = node->next;
      node->next = tmp->next;
      tmp->next = *list;
      *list = tmp;
      return 1;
    }
  }
  while (node->next->next != NULL) {
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
  find_trans(&head, 6);

  //delete(&head, 3);
  print_list(head);

  return 0;
}
