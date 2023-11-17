package algorithms;

import visualizer.Edge;
import visualizer.Vertex;
import java.util.*;

public class DijkstrasAlgorithm implements GraphAlgorithm {
    @Override
    public String run(Map<Vertex, List<Edge>> graph, Vertex start) {

        // Initialize the map that will store the Vertex: Weight pairs
        Map<Vertex, Double> outputMap = new TreeMap<>();

        // Initialize distances with infinity for all vertices except the source
        Map<Vertex, Double> distances = new HashMap<>();
        for (Vertex vertex: graph.keySet()) {
            if (!vertex.equals(start)) distances.put(vertex, Double.POSITIVE_INFINITY);
        }

        // Mark all vertices except source as unprocessed
        Set<Vertex> unprocessedVertices = new HashSet<>(distances.keySet());

        // Find the edges of the start Vertex
        List<Edge> startVertexEdges = graph.get(start);

        // Update distances to unprocessed neighbors of start vertex
        for (Edge edge: startVertexEdges) {
            Vertex neighbor = edge.getVertex2();
            int weight = edge.getWeight();
            if (unprocessedVertices.contains(neighbor)) {
                double newDistance = (double) weight;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                }
            }
        }

        // Dijkstra's algorithm
        while (!unprocessedVertices.isEmpty()) {
            // Find the Vertex with the smallest distance
            Vertex current = findSmallestDistanceVertex(unprocessedVertices, distances);

            // Add the current vertex with its weight to the outputMap and mark it as processed
            outputMap.put(current, distances.get(current));
            unprocessedVertices.remove(current);

            // Find the edges of the current Vertex
            List<Edge> currentVertexEdges = graph.get(current);

            // Update distances to unprocessed neighbors
            for (Edge edge: currentVertexEdges) {
                Vertex neighbor = edge.getVertex2();
                double weight = (double) edge.getWeight();
                if (unprocessedVertices.contains(neighbor)) {
                    double newDistance = distances.get(current) + weight;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                    }
                }
            }
        }

        String shortestPaths = processDistances(outputMap);
        return shortestPaths.substring(0, shortestPaths.length() - 2);
    }

    private static Vertex findSmallestDistanceVertex(Set<Vertex> unprocessedVertices, Map<Vertex, Double> distances) {
        Vertex smallestVertex = null;
        double smallestDistance = Double.POSITIVE_INFINITY;

        for (Vertex vertex: unprocessedVertices) {
            if (distances.get(vertex) <= smallestDistance) {
                smallestVertex = vertex;
                smallestDistance = distances.get(vertex);
            }
        }

        return smallestVertex;
    }

    private String processDistances(Map<Vertex, Double> map) {
        String shortestPaths = "";
        for (Vertex vertex: map.keySet()) {
            Double weight = map.get(vertex);
            if (weight == Double.POSITIVE_INFINITY) {
                shortestPaths += vertex.getId() + "=" + weight + ", ";
            } else {
                shortestPaths += vertex.getId() + "=" + weight.intValue() + ", ";
            }
        }
        return shortestPaths;
    }


    /*
    public static void main(String[] args) {
        Vertex vertexB = new Vertex(0, 0, "B");
        Vertex vertexA = new Vertex(0, 0, "A");
        Vertex vertexC = new Vertex(0, 0, "C");
        Vertex vertexD = new Vertex(0, 0, "D");

        Edge edgeBA = new Edge(vertexB, vertexA, 1);
        Edge edgeAB = new Edge(vertexA, vertexB, 1);
        Edge edgeBC = new Edge(vertexB, vertexC, 4);
        Edge edgeCB = new Edge(vertexC, vertexB, 4);
        Edge edgeDA = new Edge(vertexD, vertexA, 2);
        Edge edgeAD = new Edge(vertexA, vertexD, 2);
        Edge edgeCD = new Edge(vertexC, vertexD, 2);
        Edge edgeDC = new Edge(vertexD, vertexC, 2);

        Map<Vertex, List<Edge>> map = new HashMap<>();
        map.put(vertexA, Arrays.asList(edgeAB, edgeAD));
        map.put(vertexB, Arrays.asList(edgeBA, edgeBC));
        map.put(vertexC, Arrays.asList(edgeCB, edgeCD));
        map.put(vertexD, Arrays.asList(edgeDC, edgeDA));

        DijkstrasAlgorithm algo = new DijkstrasAlgorithm();
        System.out.println(algo.run(map, vertexB));
    }

     */
}
