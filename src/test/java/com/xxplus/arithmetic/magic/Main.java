package com.xxplus.arithmetic.magic;

import java.util.*;

/**
 * Created by 栾海鹏 on 2016/1/28.
 */
public class Main {

    private static Map<Node, Node> NODE_MAP = new HashMap<Node, Node>();

    private static List<PathDirection> directionList = new ArrayList<PathDirection>(4);

    private static LinkedList<Node> waitVisitNodeList = new LinkedList<Node>();
//
    private static Node targetNode = new Node(new int[][]{
            {9, 4, 7},
            {8, 5, 2},
            {3, 6, 1}
    });



//    private static Node targetNode = new Node(new int[][]{
//            {2, 5, 3},
//            {1, 4, 6},
//            {7, 8, 9}
//    });


//    private static Node targetNode = new Node(new int[][]{
//            {4, 6, 1},
//            {9, 2, 3},
//            {5, 7, 8}
//    });

    static {
        directionList.add(new PathDirection(0, 0, true, "R1"));
        directionList.add(new PathDirection(0, 1, true, "R2"));
        directionList.add(new PathDirection(1, 0, true, "R3"));
        directionList.add(new PathDirection(1, 1, true, "R4"));

        directionList.add(new PathDirection(0, 0, false, "r1"));
        directionList.add(new PathDirection(0, 1, false, "r2"));
        directionList.add(new PathDirection(1, 0, false, "r3"));
        directionList.add(new PathDirection(1, 1, false, "r4"));
    }

    public static void main(String[] args) {

        Node startNode = new Node(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        startNode.setFromPath("");

        waitVisitNodeList.add(startNode);

        run();

    }


    private static void run() {
        while (true) {
            Node node = waitVisitNodeList.pollFirst();
            if (node != null && !node.isHasVisited()) {
                boolean hasCatched = visitNode(node);
                if (hasCatched) {
                    return;
                }
            }
        }
    }


    private static boolean visitNode(Node startNode) {
        if (startNode.equals(targetNode)) {
            System.out.println(startNode.getFromPath());
            return true;
        }

        startNode.setHasVisited(true);

        for (PathDirection direction : directionList) {
            int[][] nextStatus = direction.go(startNode.getStatus());

            Node nextNode = new Node(nextStatus);
            nextNode.setFromPath(startNode.getFromPath() + direction.getName());
            Node node = NODE_MAP.get(nextNode);
            if (node == null) {
                NODE_MAP.put(nextNode, nextNode);
                waitVisitNodeList.add(nextNode);
            }
        }

        return false;

    }
}
