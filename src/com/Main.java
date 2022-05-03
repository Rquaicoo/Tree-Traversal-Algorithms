package com;

import java.util.Scanner;

import static com.Node.aStar;
import static com.Node.displayPath;

public class Main {

    public static void main(String args[]) {

        System.out.println("What operation do you want to perform ?" +
                "1. Regular search"
        + "2. Heuristic search");
        Scanner sc = new Scanner(System.in);

        int option = sc.nextInt();

        if(option == 1) {
        System.out.println("Enter number of elements in search space");

        int v = sc.nextInt();

        System.out.println("Enter number of edges");
        int e = sc.nextInt();

        Graph g = new Graph(v);

        for (int i = 0; i < e; i++) {
            System.out.println("Enter edge");
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            g.addEdge(v1, v2);
        }

        System.out.println("Enter root");
        int root = sc.nextInt();

        System.out.println("What algorithm do you want to use?");
        System.out.println("1. BFS 2. DFS 3. Iterative deepening");

        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Following is Breadth First Traversal "+
                    "(starting from vertex " + root );

            g.BFS(root);
        } else  {
            System.out.println("Following is Depth First Traversal "+
                    "(starting from vertex " + root );
            g.DFS(root);
        }

        /*
        System.out.println("Input the number of elem");
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);


        System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

       graph.BFS(0);

        System.out.println("Following is Depth First Traversal");

        graph.DFS(2);

         */

    }
        else {
            System.out.println("Try AStar algorithm with the default path. Press 1 to continue");
            System.out.println("Graph diagram: ");
            System.out.println("head -> n1 -> n4 -> target");
            System.out.println("head -> n2 -> n5 -> target");
            System.out.println("head -> n3 -> n4 -> target");

            int choice = sc.nextInt();

            Node head = new Node(3.0);
            head.g = 0.0;
            Node n1 = new Node(2.0);
            Node n2 = new Node(3.0);
            Node n3 = new Node(3.0);
            head.addBranch(1, n1);
            head.addBranch(5, n2);
            head.addBranch(2, n3);
            n3.addBranch(1, n2);
            Node n4 = new Node(1.0);
            Node n5 = new Node(1.0);
            Node destination = new Node(0.0);
            n1.addBranch(7, n4);
            n2.addBranch(4, n5);
            n3.addBranch(6, n4);
            n4.addBranch(3, destination);
            n5.addBranch(1, n4);
            n5.addBranch(3, destination);
            Node res = aStar(head, destination);
            System.out.println("Optimal path: ");

            displayPath(res);
        }
    }
}
