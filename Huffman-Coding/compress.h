#ifndef COMPRESS_H
#define COMPRESS_H
#include <iostream>
#include <fstream>
#include <unordered_map>
#include <queue>
using namespace std;

struct Huffman_node {
    char data;
    int frequency;
    Huffman_node* left;
    Huffman_node* right;

    Huffman_node(char data, int frequency) {
        this->data = data;
        this->frequency = frequency;
        left = nullptr;
        right = nullptr;
    }
};

struct Compare {
    bool operator()(Huffman_node* left, Huffman_node* right) {
        return left->frequency > right->frequency;
    }
};





#endif //COMPRESS_H
