#include "Set.h"
void clear(MultiwayTrieSet::Node* node);

void clear(MultiwayTrieSet::Node* node) {
    for (auto node2 = node->children.begin(); node2 != node->children.end(); ++node2) {
        clear(node2->second);
    }
    delete node;
}

/**
 * Implement the MultiwayTrieSet constructor
 */
MultiwayTrieSet::MultiwayTrieSet() {
    root = new Node(); 
    numElements = 0;
}

/**
 * Implement the MultiwayTrieSet destructor
 */
MultiwayTrieSet::~MultiwayTrieSet() {
    clear(root);
}

/**
 * Implement the MultiwayTrieSet methods correctly
 */
unsigned int MultiwayTrieSet::size() {
    return numElements;
}

void MultiwayTrieSet::insert(string s) {
    Node* current = root;
    for (char c : s) {
        if (current->children.find(c) == current->children.end()) {
            current->children[c] = new Node();
        }
        current = current->children[c];
    }

    if (!current->isWord) {
        current->isWord = true;
        numElements++;
    }
}

void MultiwayTrieSet::remove(const string & s) {
    Node* current = root;
    for (char c : s) {
        if (current->children.find(c) == current->children.end()) {
            return;
        }
        current = current->children[c];
    }
    if (current->isWord) {
        current->isWord = false;
        numElements--;
    }
}

bool MultiwayTrieSet::find(const string & s) {
    Node* current = root;
    for (char c : s) {
        if (current->children.find(c) == current->children.end()) {
            return false;
        }
        current = current->children[c];
    }
    return current->isWord;
}
