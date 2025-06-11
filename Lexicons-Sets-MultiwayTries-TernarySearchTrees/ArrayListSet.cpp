#include "Set.h"
#include <algorithm>


/**
 * Implement the ArrayListSet methods correctly
 */
unsigned int ArrayListSet::size() {
    return arr.size();
}

void ArrayListSet::insert(string s) {
    if(!find(s)){
        arr.push_back(s);
    }
}

void ArrayListSet::remove(const string & s) {
    auto to_remove = std::find(arr.begin(), arr.end(), s);
    if(to_remove != arr.end()){
        arr.erase(to_remove);
    }
}

bool ArrayListSet::find(const string & s) {
    return std::find(arr.begin(), arr.end(), s) != arr.end();
}
