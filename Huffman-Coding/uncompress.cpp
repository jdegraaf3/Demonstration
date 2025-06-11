#include "compress.h"
#include <iostream>
#include <fstream>
#include <unordered_map>
#include <bitset>
using namespace std;

void uncompress_file(string input_file, string output_file) {
    ifstream inFile(input_file, ios::binary);
    ofstream outFile(output_file);
    
    int num_codes;
    inFile >> num_codes;
    inFile.get(); // consume the newline character after the number of codes
    
    unordered_map<string, char> huffman_codes;
    for (int i = 0; i < num_codes; ++i) {
        char c;
        string code;
        inFile.get(c);
        inFile >> code;
        inFile.get(); // consume the newline character after the code
        huffman_codes[code] = c;
    }

    int padding_bits;
    inFile >> padding_bits;
    inFile.get(); // consume the newline character after padding bits

    string encoded_string;
    char byte;
    while (inFile.get(byte)) {
        bitset<8> bits(byte);
        encoded_string += bits.to_string();
    }
    inFile.close();


    // remove padding
    encoded_string = encoded_string.substr(0, encoded_string.size() - padding_bits);
    

    string current_code;
    for (char bit : encoded_string) {
        current_code += bit;
        if (huffman_codes.find(current_code) != huffman_codes.end()) {
            outFile.put(huffman_codes[current_code]);
            current_code.clear();
        }
    }

    outFile.close();
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        cerr << "Usage: " << argv[0] << " <input file> <output file>" << endl;
        return 1;
    }
    uncompress_file(argv[1], argv[2]);
    return 0;
}

