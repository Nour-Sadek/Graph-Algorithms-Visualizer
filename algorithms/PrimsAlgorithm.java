package algorithms;

import visualizer.Edge;
import visualizer.Vertex;

import java.util.*;

public class PrimsAlgorithm implements GraphAlgorithm {
    @Override
    public String run(Map<Vertex, List<Edge>> graph, Vertex start) {

        // If the chosen vertex doesn't have any edges, then just return it
        if (graph.get(start).isEmpty()) {
            return start.getId();
        }

        // Initialize a list that will store the edges of the MST (Minimum Spanning Tree)
        List<Edge> edgesOfMST = new ArrayList<>();

        // Initialize a set that will store the vertices belonging to the MST and add the start Vertex to it
        Set<Vertex> verticesOfMST = new HashSet<>();
        verticesOfMST.add(start);

        // Find all directly and indirectly connected vertices of the Start Vertex
        Set<Vertex> connectedVertices = findConnectedVertices(graph, start);

        // Prim's Algorithm
        while (verticesOfMST.size() != connectedVertices.size()) {
            // Find the edge originating from the Vertices already in MST with the lowest weight
            Edge lowestWeightEdge = findLowestEdgeWeight(graph, verticesOfMST);

            // Add it to the edgesOfMST list and mark the target Vertex as processed
            edgesOfMST.add(lowestWeightEdge);
            verticesOfMST.add(lowestWeightEdge.getVertex2());
        }

        return processEdgesOfMST(edgesOfMST);
    }

    private static Edge findLowestEdgeWeight(Map<Vertex, List<Edge>> graph, Set<Vertex> verticesOfMST) {
        Edge correctEdge = null;
        double smallestWeight = Double.POSITIVE_INFINITY;

        for (Vertex vertex: verticesOfMST) {
            for (Edge edge: graph.get(vertex)) {
                double weight = (double) edge.getWeight();
                Vertex target = edge.getVertex2();
                if (weight < smallestWeight && !verticesOfMST.contains(target)) {
                    smallestWeight = weight;
                    correctEdge = edge;
                }
            }
        }

        return correctEdge;

    }

    /*
    Uses the Breadth-First Search Algorithm to find all reachable Vertices from a start Vertex
     */
    private static Set<Vertex> findConnectedVertices(Map<Vertex, List<Edge>> graph, Vertex start) {
        // Initialize the set that will contain the reachable Vertices
        Set<Vertex> reachableVertices = new HashSet<>();

        // Initialize a set to keep track of visited Vertices
        Set<Vertex> visited = new HashSet<>();

        // Initialize a queue for BFS
        Queue<Vertex> queue = new LinkedList<>();

        // Mark the start Vertex as visited and enqueue it
        visited.add(start);
        queue.offer(start);

        // BFS traversal
        while (!queue.isEmpty()) {
            // Dequeue a Vertex from the queue
            Vertex current = queue.poll();

            // Add the current Vertex to be a reachable vertex
            reachableVertices.add(current);

            // Find the Edges of the current Vertex
            List<Edge> currentVertexEdges = graph.get(current);

            // Visit all unvisited neighbors of the current Vertex
            for (Edge edge: currentVertexEdges) {
                Vertex neighbor = edge.getVertex2();
                if (!visited.contains(neighbor)) {
                    // Mark neighbor as visited and enqueue
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return reachableVertices;
    }

    /*
    The algorithm produces a list of the <Child>=<Parent> pairs separated by a comma.
    For example: B=A, C=B, D=A. It means that the parents of B, C, and D in the minimum spanning tree are A, B, and A respectively.
    It will be displayed in alphabetical order.
     */
    private static String processEdgesOfMST(List<Edge> edgesOfMST) {
        String output = "";

        ChildEdgesInAlphabeticalOrder comparator = new ChildEdgesInAlphabeticalOrder();
        edgesOfMST.sort(comparator);

        for (Edge edge: edgesOfMST) {
            output += edge.getVertex2().getId() + "=" + edge.getVertex1().getId() + ", ";
        }

        return output.substring(0, output.length() - 2);

    }

    static class ChildEdgesInAlphabeticalOrder implements Comparator<Edge> {

        @Override
        public int compare(Edge edge1, Edge edge2) {
            Vertex child1 = edge1.getVertex2();
            Vertex child2 = edge2.getVertex2();

            return String.valueOf(child1.getId()).compareTo(child2.getId());
        }
    }
}
