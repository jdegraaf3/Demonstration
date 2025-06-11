#include "compress.h"
#include <iostream>
#include <fstream>
#include <unordered_map>
#include <queue>
#include <bitset>
using namespace std;

void delete_Htree(Huffman_node* root) {
    if (root == nullptr) {
        return;
    }
    delete_Htree(root->left);
    delete_Htree(root->right);
    delete root;
}

void generate_H_Codes(Huffman_node* root, string code, unordered_map<unsigned char, string>& huffman_codes) {
    if(root == nullptr) {
        return;
    }

    if(!root->left && !root->right) {
        huffman_codes[root->data] = code;
    }
    generate_H_Codes(root->left, code + "0", huffman_codes);
    generate_H_Codes(root->right, code + "1", huffman_codes);
}

Huffman_node* build_Htree (unordered_map<unsigned char, int>& frequency) {
    priority_queue<Huffman_node*, vector<Huffman_node*>, Compare> pq;
    for(auto& pair: frequency) {
        pq.push(new Huffman_node(pair.first, pair.second));
    }
    while(pq.size() > 1) {
        Huffman_node* left = pq.top();
        pq.pop();
        Huffman_node* right = pq.top();
        pq.pop();
        Huffman_node* top = new Huffman_node(0, left->frequency + right->frequency);
        top->left = left;
        top->right = right;
        pq.push(top);
    }
    return pq.top();
}

void compress_file(string input_file, string output_file) {
    ifstream inFile(input_file, ios::binary);
    vector<unsigned char> content((istreambuf_iterator<char>(inFile)), (istreambuf_iterator<char>()));
    inFile.close();

    if (content.empty()) {
        ofstream outFile(output_file, ios::binary);
        outFile.close();
        return;
    }

    unordered_map<unsigned char, int> frequency;
    for(unsigned char c: content) {
        frequency[c]++;
    }

    Huffman_node* root = build_Htree(frequency);
    unordered_map<unsigned char, string> huffman_codes;
    generate_H_Codes(root, "", huffman_codes);

    delete_Htree(root);

    ofstream outFile(output_file, ios::binary);
    outFile << huffman_codes.size() << endl;
    for(auto& pair: huffman_codes) {
        outFile << pair.first << " " << pair.second << endl;
    }

    string encoded_string;
    for (unsigned char c : content) {
        encoded_string += huffman_codes[c];
    }

    
    // Calculate padding
    int padding = (8 - encoded_string.size() % 8) % 8; // Number of bits added for padding

    // Write header: number of padded bits
    outFile << padding << endl;

    // Pad the encoded string to make it a multiple of 8
    for (int i = 0; i < padding; ++i) {
        encoded_string += '0';
    }


    for (size_t i = 0; i < encoded_string.size(); i += 8) {
        bitset<8> byte(encoded_string.substr(i, 8));
        outFile.put(static_cast<unsigned char>(byte.to_ulong()));
    }
    outFile.close();
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        cerr << "Usage: " << argv[0] << " <input file> <output file>" << endl;
        return 1;
    }
    compress_file(argv[1], argv[2]);
    return 0;
}


// ./compress "example_files/test1.txt" "example_files/test1output.txt"

//du -sb
