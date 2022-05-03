package com;

import com.Node;

import java.io.*;
import java.util.*;

class IDS
{
    private static boolean bottomReached = false;

    public static Node IDS(Node start, int target) {
        int depth = 1;
        while(!bottomReached) {
            Node result = iterativeDeepingDFS(start, target, 0, depth);
            if(result != null) {
                return result;
            }
            depth *= 2;
            System.out.println("increased depth to " + depth);
        }
        return null;
    }

    private static Node iterativeDeepingDFS(Node node, int target, int currentDepth, int maxDepth) {
        System.out.println("Visiting node " + node.value + " at depth " + currentDepth);

        if(node.value == target) {
            System.out.println("Found target at depth " + currentDepth);
            return node;
        }

        if (currentDepth == maxDepth) {
            System.out.println("Reached max depth");

            if (node.children.length > 0) {
                bottomReached = false;
            }
            bottomReached = true;
            return null;
        }

        for (int i = 0; i < node.children.length; i++) {
            Node child = iterativeDeepingDFS(node.children[i], target, currentDepth + 1, maxDepth);
            if (child != null) {
                return child;
            }
        }
        return null;
    }

    class Node {
        public int value;
        public Node[] children;
    }
}