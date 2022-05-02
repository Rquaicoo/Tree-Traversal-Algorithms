package com;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

    private int vertices;
    private LinkedList<Integer> adj_list[];

    Graph(int vertices) {
        vertices = vertices;

        adj_list = new LinkedList[vertices];

        for (int i=0; i < vertices; i++)
            adj_list[i] = new LinkedList<>();
    }

    //add edges

    void  addEdge(int start, int end) {
        adj_list[start].add(end);
    }

    void BFS(int root) {
        boolean visited[] = new boolean[vertices];

        LinkedList<Integer> queue = new LinkedList<>();

        visited[root] = true;
        queue.add(root);

        while(queue.size() != 0) {
            root = queue.poll();
            System.out.println(root + " ");

            Iterator<Integer> i = adj_list[root].listIterator();
            while (i.hasNext()) {
                int n= i.next();
                if(!visited[n]) {
                    visited[n] =true;
                    queue.add(n);
                }
            }

        }
    }

    void DFS(int root) {
        boolean visited[] = new boolean[vertices];

        visited[root] = true;
        System.out.print(root + " ");

        Iterator<Integer> i = adj_list[root].listIterator();
        while (i.hasNext()) {
            int adjacent = i.next();
            if(!visited[adjacent]) {
                DFS(adjacent);
            }
        }

    }
}
