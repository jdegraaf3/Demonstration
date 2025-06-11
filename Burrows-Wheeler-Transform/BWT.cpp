#include <string>
#include <algorithm>
#include <vector>
#include "BWT.h"
using namespace std;

bool compare(const string & s, unsigned int i, unsigned int j) {
    return s.substr(i) < s.substr(j);
}

/**
 * Implement suffix_array() correctly
 */
vector<unsigned int> suffix_array(const string & s) {
    vector<unsigned int> suffix_array(s.length());
    for(unsigned int i = 0; i < s.length(); ++i) {
        suffix_array[i] = i;
    }
    std::sort(suffix_array.begin(), suffix_array.end(), [&s](int i, int j) {
        return compare(s, i, j);
    });
    return suffix_array;
}
/**
 * Implement bwt() correctly
 */
string bwt(const string &s) {
 int size = s.length();
 string result(size, ' ');
 vector<unsigned int> suffix = suffix_array(s);
 for(int i = 0; i < size; ++i) {
  int j = suffix[i]-1;
  if(j < 0) {
   j += size;
  }
  result[i] = s[j];
 }
 return result;
}
