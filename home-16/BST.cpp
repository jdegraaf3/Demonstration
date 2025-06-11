#include "BST.h"

/**
 * Implement the BST constructor
 */
BST::BST() {
    numElements = 0;
    root = nullptr;
}

/**
 * Implement the BST destructor
 */
BST::~BST() {
    clear();
}

/**
 * Implement size() correctly
 */
unsigned int BST::size() const {
    return numElements;
}

void clearHelper(BST::Node* node) {
    if (node) {
        clearHelper(node->leftChild);
        clearHelper(node->rightChild);
        delete node;
    }
}

/**
 * Implement clear() correctly without memory leaks
 */
void BST::clear() {
    clearHelper(root);
    root = nullptr;
    numElements = 0;
}



/**
 * Implement insert() correctly
 */
bool BST::insert(int element) {
    Node** currentNode = &root;
    Node* parentNode = nullptr;
    // Find insert location
    while(*currentNode != nullptr){
        parentNode = *currentNode;
        if(element < (*currentNode)->data){
            currentNode = &((*currentNode)->leftChild);
        }
        else if(element > (*currentNode)->data){
            currentNode = &((*currentNode)->rightChild);
        }
        else{
            return false;
        }
    }
    *currentNode = new Node(element);
    (*currentNode)->parent = parentNode;
    numElements++;
    return true;
}

/**
 * Implement find() correctly
 */
bool BST::find(const int & query) const {
    Node* currentNode = root;
    while(currentNode != nullptr){
        if(query < currentNode->data){
            currentNode = currentNode->leftChild;
        }
        else if(query > currentNode->data){
            currentNode = currentNode->rightChild;
        }
        else{
            return true;
        }
    }
    return false;
}

/**
 * Implement the getLeftMostNode() function correctly
 */
BST::Node* BST::getLeftMostNode() {
    if(root == nullptr){
        return nullptr;
    }
    Node* currentNode = root;
    while(currentNode->leftChild != nullptr){
        currentNode = currentNode->leftChild;
    }
    return currentNode;
}

/**
 * Implement the BST::Node successor function correctly
 */
BST::Node* BST::Node::successor() {
    if(this->rightChild != nullptr){
        Node* successor = this->rightChild;
        while(successor->leftChild != nullptr){
            successor = successor->leftChild;
        }
        return successor;
    }

    Node* successor = this->parent;
    Node* currentNode = this;
    while(successor != nullptr && currentNode == successor->rightChild){
        currentNode = successor;
        successor = successor->parent;
    }
    return successor;
}
