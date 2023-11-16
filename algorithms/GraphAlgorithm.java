package algorithms;

import visualizer.Edge;
import visualizer.Vertex;
import java.util.*;

public interface GraphAlgorithm {
    String run(Map<Vertex, List<Edge>> graph, Vertex start);

    default String processVertex(Vertex vertex) {
        return vertex.getId() + " -> ";
    }
}
