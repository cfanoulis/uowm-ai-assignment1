package dev.fanoulis;

import java.util.PriorityQueue;
import java.util.Random;


public class BFSGameFringe {

    private final PriorityQueue<GameStateNode> queue;

    public BFSGameFringe(GameStateNode rootNode) {
        queue = new PriorityQueue<>(BFSGameFringe::NodeComparator);
        queue.add(rootNode);
    }

    public boolean isFringeEmpty() {
        return queue.isEmpty();
    }

    public GameStateNode checkNextNode() {
        GameStateNode currentNode = queue.poll();
//        System.out.println("Current node is " + currentNode.toString());
        if (currentNode == null) return null; //todo: handle an empty queue - probably failed search

        if(currentNode.isGoalState()) return currentNode;

        var expandedNodes = currentNode.expand();
//        System.out.println(String.format("Node failed the test, expanded to %d", expandedNodes.size()));
        queue.addAll(expandedNodes);

        return null;
    }

    //implement random selection for comparing amongst equal-cost states when sorting for priority
    //as required by assignment, pg.4
    public static int NodeComparator(GameStateNode a, GameStateNode b) {
        int result = Integer.compare(a.getPathCost(), b.getPathCost());
        if (result != 0) return result;

        Random r = new Random();
        if(r.nextBoolean()) return -1;
        return 1;
    }

}
