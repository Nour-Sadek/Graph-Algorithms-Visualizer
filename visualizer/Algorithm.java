package visualizer;

import algorithms.*;
public enum Algorithm {

    BFS(new BFSAlgorithm()),
    DFS(new DFSAlgorithm()),
    DIJKSTRAS(new DijkstrasAlgorithm()),
    PRIMS(new PrimsAlgorithm());

    private final GraphAlgorithm algorithmInstance;

    Algorithm(GraphAlgorithm algorithmInstance) {
        this.algorithmInstance = algorithmInstance;
    }

    public GraphAlgorithm getAlgorithmInstance() {
        return algorithmInstance;
    }

}
