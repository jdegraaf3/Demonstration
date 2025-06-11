#include <limits>
#include "CountMinSketch.h"
#include "HashFunctions.h"

/**
 * Implement increment() correctly
 */
void CountMinSketch::increment(const string & s) {
    for(unsigned int i = 0; i < K; i++) {
        unsigned int hash = hash_functions[i](s) % M;
        count[i][hash]++;
    }
}

/**
 * Implement find() correctly
 */
unsigned int CountMinSketch::find(const string & s) {
    unsigned int est = std::numeric_limits<unsigned int>::max();
    for(unsigned int i = 0; i < K; i++) {
        unsigned int hash = hash_functions[i](s) % M;
        unsigned int curr = count[i][hash];
        if(curr < est){
            est = curr;
        }
    }
    return est;
}

