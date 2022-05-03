package com;

public class Main {

    public static void main(String args[]) {
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);


        System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

       graph.BFS(0);

        System.out.println("Following is Depth First Traversal");

        graph.DFS(2);

    }
}
