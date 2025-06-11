# use g++ with C++11 support
CXX=g++
CXXFLAGS?=-Wall -pedantic -g -O0 -std=c++11
OUTFILES=compress uncompress

all: $(OUTFILES)
main: main.cpp
	$(CXX) $(CXXFLAGS) -o main main.cpp
compress: compress.cpp compress.h 
	$(CXX) $(CXXFLAGS) -o compress compress.cpp

uncompress: uncompress.cpp Helper.cpp Helper.hpp HCTree.hpp
	$(CXX) $(CXXFLAGS) -o uncompress uncompress.cpp Helper.cpp

gprof: compress.cpp uncompress.cpp
	make clean
	$(CXX) $(CXXFLAGS) -pg -o compress compress.cpp Helper.cpp
	$(CXX) $(CXXFLAGS) -pg -o uncompress uncompress.cpp Helper.cpp

clean:
	rm -f $(OUTFILES) *.o

