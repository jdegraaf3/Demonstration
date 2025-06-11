#include "Graph.h"

#include <fstream>
#include <sstream>
#include <iostream>
#include <queue>
#include <limits>
#include <set>
#include <stack>
#include <algorithm>


// returns index of found node, or creates one and returns new index
int Graph::getOrCreateNode(string node_label) {
    vector<string>::iterator found = std::find(nodeLabels.begin(), nodeLabels.end(), node_label);
    if (found == nodeLabels.end()) {
        // not found
        nodeLabels.push_back(node_label);
        return nodeLabels.size() - 1;
    }
    // found
    return found - nodeLabels.begin();
}


int Graph::getNodeIdx(const string& label) {
    // which element of nodeLabels has node_label
    // node_label == nodeLabels[nodeIdx]
    return std::find(nodeLabels.begin(), nodeLabels.end(), label) - nodeLabels.begin();
}


Graph::Graph(const char* const & edgelist_csv_fn) {
    std::ifstream my_file(edgelist_csv_fn);      // open the file
    string line;                     // helper var to store current line
    // std::cout << edgelist_csv_fn;
    while(getline(my_file, line)) {  // read one line from the file
        // std::cout << edgelist_csv_fn;
        std::istringstream ss(line);      // create istringstream of current line
        // node label, node label, weight
        string first, second, third; // helper vars
        getline(ss, first, ',');     // store first column in "first"
        getline(ss, second, ',');    // store second column in "second"
        getline(ss, third, '\n');    // store third column column in "third"
        
        string node1Label = first, node2Label = second;
        double weight = stod(third);
        
        // std::cout << weight << " ";
        
        int idx1 = getOrCreateNode(node1Label);
        int idx2 = getOrCreateNode(node2Label);
        
        Edge edge { idx1, idx2, weight };
        edges.push_back(edge);
        // edges.emplace_back(idx1, idx2, weight);
    }
    my_file.close();
    
    // sort edges by order of increasing weight
    std::sort(edges.begin(), edges.end(), [](const Edge &a, const Edge &b) { 
        return a.weight < b.weight;
    });
}



unsigned int Graph::num_nodes() {
    return nodeLabels.size();
}

vector<string> Graph::nodes() {
    return nodeLabels;
}

unsigned int Graph::num_edges() {
    return edges.size();
}

unsigned int Graph::num_neighbors(string const & node_label) {
    int nodeIdx = getNodeIdx(node_label);
    int num_neighbors = 0;
    // edge.node1Idx
    // for each edges
    //     if edge.node1 == nodeIdx
    for(const auto& edge : edges){
        // Edge& x = edges[i];
        // x++;
        // edges[i]++;
        // x.weight++;
        if(edge.node1Idx == nodeIdx || edge.node2Idx == nodeIdx){
            num_neighbors++;
        }
    }
    return num_neighbors;
}

// return weight or -1 if edge uv does not exist
double Graph::edge_weight(string const & u_label, string const & v_label) {
    int uIdx = getNodeIdx(u_label);
    int vIdx = getNodeIdx(v_label);
    for(auto edge : edges) {
        if (
            (edge.node1Idx == uIdx && edge.node2Idx == vIdx) ||
            (edge.node1Idx == vIdx && edge.node2Idx == uIdx)
        ) {
            return edge.weight;
        }
    }
    return -1.0;
}

vector<string> Graph::neighbors(string const & node_label) {
    int nodeIdx = getNodeIdx(node_label);
    vector<string> neighbors;
    // edge.node1Idx
    // for each edges
    //     if edge.node1 == nodeIdx
    for(const auto& edge : edges){
        // Edge& x = edges[i];
        // x++;
        // edges[i]++;
        // x.weight++;
        if(edge.node1Idx == nodeIdx){
            neighbors.push_back(nodeLabels[edge.node2Idx]);
        }
        if(edge.node2Idx == nodeIdx){
            neighbors.push_back(nodeLabels[edge.node1Idx]);
        }
    }
    return neighbors;
}


vector<int> Graph::neighbors(int nodeIdx) {
    vector<int> neighbors;
    for(const auto& edge : edges){
        if(edge.node1Idx == nodeIdx){
            neighbors.push_back(edge.node2Idx);
        }
        if(edge.node2Idx == nodeIdx){
            neighbors.push_back(edge.node1Idx);
        }
    }
    return neighbors;
}

// returns edge indexes
vector<int> Graph::neighboringEdges(int nodeIdx) {
    vector<int> neighboringEdges;
    for(size_t i = 0; i < edges.size(); i++){
        if(edges[i].node1Idx == nodeIdx || edges[i].node2Idx == nodeIdx){
            neighboringEdges.push_back(i);
        }
    }
    return neighboringEdges;
}



vector<string> Graph::shortest_path_unweighted(string const & start_label, string const & end_label) {
    
    std::queue<int> queueNext;
    
    int startNodeIdx = getNodeIdx(start_label);
    int endNodeIdx = getNodeIdx(end_label);
    
    queueNext.push(startNodeIdx);
    
    // source[nodeIdx] = idx of prev node on shortest path to node
    // fill with -1 initially meaning not visited
    vector<int> source(nodeLabels.size(), -1);
    
    // source[D] = A
    // source[B] = D
    // source[F] = D
    // source[G] = F
    
    // A - D,E
    // A: 0, A
    // D: 1, A
    // E: 1, A
    // B: 2, D
    // F: 2, D
    // G: 2, E
    
    
    while (!queueNext.empty()) {
        int currNodeIdx = queueNext.front();
        queueNext.pop();
        // visited[currNodeIdx] = true;
        vector<int> neighborIdxs = neighbors(currNodeIdx);
        for (int neighbor : neighborIdxs) {
            // if neighbor doesn't have a shortest path yet:
            if (source[neighbor] == -1) {
                source[neighbor] = currNodeIdx;
                if (neighbor == endNodeIdx) {
                    // find path through source array
                    vector<string> path;
                    
                    int prevIdx = endNodeIdx;
                    while (prevIdx != -1) {
                        path.insert(path.begin(), nodeLabels[prevIdx]);
                        if (prevIdx == startNodeIdx) {
                            return path;
                        }
                        prevIdx = source[prevIdx];
                    }
                }
                queueNext.push(neighbor);
            }
        }
        
    }
    
    // no path
    return vector<string>();
    
}



// static bool compare(int )


vector<tuple<string,string,double>> Graph::shortest_path_weighted(string const & start_label, string const & end_label) {
    int startNodeIdx = getNodeIdx(start_label);
    int endNodeIdx = getNodeIdx(end_label);
    
    if (startNodeIdx == endNodeIdx) {
        // vector<tuple<string,string,double>> path{}
        return {{nodeLabels[startNodeIdx], nodeLabels[startNodeIdx], -1}};
    }
    
    struct Source {
        int sourceEdgeIdx;
        double distance;
    };
    // fill with sourceNodeIdx = -1 initially meaning not visited, and distance = infinity
    vector<Source> nodeData(nodeLabels.size(), { -1, std::numeric_limits<double>::infinity()});
    
    nodeData[startNodeIdx].distance = 0.0;
    
    
    // comparison function for priority_queue to get distance from node index
    auto compare = [&nodeData](int n1Idx, int n2Idx) {
        double dist1 = nodeData[n1Idx].distance;
        double dist2 = nodeData[n2Idx].distance;
        return dist1 > dist2;
    };
    
    std::priority_queue<int, vector<int>, decltype(compare)> queueNext(compare);
    
    queueNext.push(startNodeIdx);
    
    while (!queueNext.empty()) {
        // get closest available node
        int currNodeIdx = queueNext.top();
        queueNext.pop();
        
        if (currNodeIdx == endNodeIdx) {
            // found shortest path
            // find path through sourceNodeIdx in array
            vector<tuple<string,string,double>> path;
            int prevEdgeIdx = nodeData[endNodeIdx].sourceEdgeIdx;
            int prevNodeIdx = endNodeIdx;
            while (prevEdgeIdx != -1) {
                const Edge& edge = edges[prevEdgeIdx];
                int currNodeIdx = prevNodeIdx;
                prevNodeIdx = edge.node1Idx == currNodeIdx ? edge.node2Idx : edge.node1Idx;
                path.insert(path.begin(), {
                    nodeLabels[prevNodeIdx],
                    nodeLabels[currNodeIdx],
                    edge.weight,
                });
                if (prevNodeIdx == startNodeIdx) {
                    return path;
                }
                prevEdgeIdx = nodeData[prevNodeIdx].sourceEdgeIdx;
            }
        }
        
        vector<int> edgeIdxs = neighboringEdges(currNodeIdx);
        for (int edgeIdx : edgeIdxs) {
            const Edge& edge = edges[edgeIdx];
            int otherNodeIdx = edge.node1Idx == currNodeIdx ? edge.node2Idx : edge.node1Idx;
            double weight = edge.weight;
            
            double distToOther = nodeData[currNodeIdx].distance + weight;
            if (distToOther < nodeData[otherNodeIdx].distance) {
                nodeData[otherNodeIdx] = {
                    edgeIdx,
                    distToOther,
                };
                queueNext.push(otherNodeIdx);
            }
        }
    }
    
    // no path
    return vector<tuple<string,string,double>>();
}




// precondition: nodeIdx is already in set reachable
void Graph::traverseDFS(int nodeIdx, double threshold, std::set<int>& reachable) {
    
    vector<int> edgeIdxs = neighboringEdges(nodeIdx);
    
    for (int edgeIdx : edgeIdxs) {
        const Edge& edge = edges[edgeIdx];
        if (edge.weight <= threshold) {
            int otherNodeIdx = edge.node1Idx == nodeIdx ? edge.node2Idx : edge.node1Idx;
            
            if (reachable.find(otherNodeIdx) == reachable.end()) {
                // only recurse if otherNodeIdx not already in set 
                reachable.insert(otherNodeIdx);
                // check all of otherNodeIdx neighbors
                traverseDFS(otherNodeIdx, threshold, reachable);
            }
        }
    }
}



vector<vector<string>> Graph::connected_components(double const & threshold) {
    
    vector<std::set<int>> reachableComponents;
    // std::set<int> reachableFromA;
    // std::set<int> reachableFromE;
    
    // A   DFS  add to current set
    // B is in any sets? C, D,
    // E not in any set -> push new set
    
    
    // int startIdx = 0; // "A"
    
    for (unsigned int currNodeIdx = 0; currNodeIdx < num_nodes(); currNodeIdx++) {
        // is currNodeIdx in any existing components?
        
        bool currNodeFound = std::any_of(
            reachableComponents.begin(),
            reachableComponents.end(),
            [currNodeIdx](const std::set<int>& component) {
                // is currNodeIdx in this component?
                return component.find(currNodeIdx) != component.end();
        });
        
        if (!currNodeFound) {
            // add new empty set
            reachableComponents.push_back({});
            // get reference to set "owned" by vector
            std::set<int>& currComponent = *(reachableComponents.end()-1);
            currComponent.insert(currNodeIdx);
            traverseDFS(currNodeIdx, threshold, currComponent);
        }
        
    }
    
    // now reachableComponents has all components as sets of indexes
    // component is set of nodes(indexes)
    // vector<std::set<int>> reachableComponents
    
    
    vector<vector<string>> componentsReturn; // [component, component]
    // componentIdxs = set(1,2)
    // componentStrings = ["A", "B"]
    
    for(std::set<int>& componentIdxs : reachableComponents){
        // build component strings vector out of component set of ints
        componentsReturn.push_back(vector<string>());
        vector<string>& componentStrings = *(componentsReturn.end()-1);
        for(int nodeIdx : componentIdxs){
            componentStrings.push_back(nodeLabels[nodeIdx]);
        }
        
    }

    return componentsReturn;
    
}

double Graph::smallest_connecting_threshold_old(string const & start_label, string const & end_label) {
    int startNodeIdx = getNodeIdx(start_label);
    int endNodeIdx = getNodeIdx(end_label);
    
    // keep track of best way to reach a certain node,
    // storing smallest threshold needed to reach it
    struct Source {
        int sourceEdgeIdx;
        double maxWeight;
    };
    // fill with sourceNodeIdx = -1 initially meaning not visited, and maxWeight = infinity
    vector<Source> nodeData(nodeLabels.size(), { -1, std::numeric_limits<double>::infinity()});
    
    nodeData[startNodeIdx].maxWeight = 0.0;
    
    
    // comparison function for priority_queue to get maxWeight from node index
    auto compare = [&nodeData](int n1Idx, int n2Idx) {
        double dist1 = nodeData[n1Idx].maxWeight;
        double dist2 = nodeData[n2Idx].maxWeight;
        return dist1 > dist2;
    };
    
    std::priority_queue<int, vector<int>, decltype(compare)> queueNext(compare);
    
    queueNext.push(startNodeIdx);
    
    while (!queueNext.empty()) {
        // get closest available node
        int currNodeIdx = queueNext.top();
        queueNext.pop();
        
        if (currNodeIdx == endNodeIdx) {
            // found min threshold path
            return nodeData[endNodeIdx].maxWeight;
        }
        
        vector<int> edgeIdxs = neighboringEdges(currNodeIdx);
        for (int edgeIdx : edgeIdxs) {
            const Edge& edge = edges[edgeIdx];
            int otherNodeIdx = edge.node1Idx == currNodeIdx ? edge.node2Idx : edge.node1Idx;
            double weight = edge.weight;
            
            double otherMaxWeight = std::max(nodeData[currNodeIdx].maxWeight, weight);
            if (otherMaxWeight < nodeData[otherNodeIdx].maxWeight) {
                nodeData[otherNodeIdx] = {
                    edgeIdx,
                    otherMaxWeight,
                };
                queueNext.push(otherNodeIdx);
            }
        }
    }
    
    return -1;
    
}


double Graph::smallest_connecting_threshold(string const & start_label, string const & end_label) {
    int startNodeIdx = getNodeIdx(start_label);
    int endNodeIdx = getNodeIdx(end_label);
    
    // node 0 belongs to set 0, node 1 belongs to set 1
    vector<int> nodeSets; // = [0, 1, 2, 3]
    for (int i = 0; i < num_nodes(); i++) {
        nodeSets.push_back(i);
    }
    
    if (startNodeIdx == endNodeIdx) {
        return 0;
    }
    
    // loop over every edge in increasing order of weight
    for (auto& edge : edges) {
        // for edge [A,B], make B and its connected nodes belong to A's set
        // nodeSets[nodeIdx] == node's set identifier number
        int set1ID = nodeSets[edge.node1Idx];
        int set2ID = nodeSets[edge.node2Idx];
        // for every node in set2ID, make it be in set1ID instead to union them
        for (int nodeIdx = 0; nodeIdx < num_nodes(); nodeIdx++) {
            if (nodeSets[nodeIdx] == set2ID) {
                nodeSets[nodeIdx] = set1ID;
            }
        }
        
        // are start and end connected now?
        if (nodeSets[startNodeIdx] == nodeSets[endNodeIdx]) {
            // edge.weight is what was "needed" to make this happen
            return edge.weight;
        }
    }
    
    
    // start and end are disconnected
    return -1;
}



