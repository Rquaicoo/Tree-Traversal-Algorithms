package com;

import java.util.*;

public class Node implements Comparable<Node> {
    /*
    i. make an empty list of closed nodes
    ii. make an empty list of open nodes and their respective f values containing the start node
    iii. pick a node from the open nodes with a best f value whiles it is not empty
    iv. If the node is our target, return the solution
    v. for every node m, which has a neighbour n, if m is not in the closed nodes and open nodes,
    calculate g(m) and f(m) and save them,
    else if they are,
    if the f(m) from the previous iteration is better than  the g(m) from this iteration,
    - set n as m's parent
    - update g(m)  and f(m)
    - If m is in the closed nodes, m is moved to the open nodes.
    vi. n in moved from open to closed nodes.
    vii. if O is empty, return there is no solution
     */

    private static int idcount = 0;
    public int id;

    public Node parent = null;
    public List<Edge> neighbours;

    //Functions for evaluation
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;

    //heuristic
    public double h;

    Node(double h) {
        this.h = h;
        this.id = idcount++;
        this.neighbours = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {
        Edge(int weight, Node node) {
            this.weight = weight;
            this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(int weight, Node node) {
        Edge newEdge = new Edge(weight, node);
        neighbours.add(newEdge);
    }

    public double findHeuristic(Node destination) {
        return this.h;
    }

    public static Node aStar(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.findHeuristic(target);
        openList.add(start);

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target){
                return n;
            }

            for(Edge edge : n.neighbours){
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.findHeuristic(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.findHeuristic(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static  void displayPath(Node destination) {
        Node n = destination;

        if(n==null)
            return;

        List<Integer> ids = new ArrayList<>();

        while(n.parent != null) {
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for(int id : ids) {
            System.out.print(id + "->");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Node head = new Node(3);

        head.g = 0;

        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(3);

        head.addBranch(1, n1);
        head.addBranch(5, n2);
        head.addBranch(2, n3);
        n3.addBranch(1, n2);

        Node n4 = new Node(1);
        Node n5 = new Node(1);
        Node destination = new Node(0);

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
