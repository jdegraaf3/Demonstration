#include "BinaryTree.h"


void helperBalanceFact(unordered_map<int,int> &balanceFactors, BinaryTree::Node* node);
int helperGetHeight(BinaryTree::Node* node);

/**
 * Implement balanceFactors() correctly
 */
unordered_map<int,int> BinaryTree::balanceFactors() {
   unordered_map<int,int> balanceFactors;
   helperBalanceFact(balanceFactors, root);
   return balanceFactors;
}

int helperGetHeight(BinaryTree::Node* node){
   if(node == nullptr){
      return 0;
   }

   int left_height = helperGetHeight(node->leftChild);
   int right_height = helperGetHeight(node->rightChild);

   return 1 + max(left_height, right_height);
}

void helperBalanceFact(unordered_map<int,int> &balanceFactors, BinaryTree::Node* node){
   if(node == nullptr){
      return;
   }

   helperBalanceFact(balanceFactors, node->leftChild);
   helperBalanceFact(balanceFactors, node->rightChild);
   int left_height = helperGetHeight(node->leftChild);
   int right_height = helperGetHeight(node->rightChild);
   int balance_factor = right_height - left_height;
   balanceFactors[node->label] = balance_factor;
}
