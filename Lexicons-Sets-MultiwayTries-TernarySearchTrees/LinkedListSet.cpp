#include "Set.h"

/**
 * Implement the LinkedListSet methods correctly
 */
unsigned int LinkedListSet::size() {
    return linked.size();
}

void LinkedListSet::insert(string s) {
    if(!find(s)){
        linked.push_back(s);
    }
}

void LinkedListSet::remove(const string & s) {
    linked.remove(s);
}

bool LinkedListSet::find(const string & s) {
    for (const string &to_find : linked) {
        if (to_find == s) {
            return true;
        }
    }
    return false; 
}
