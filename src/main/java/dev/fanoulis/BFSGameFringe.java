package dev.fanoulis;

import java.util.*;

public class BFSGameFringe {

    private PriorityQueue<GameStateNode> queue;

    public BFSGameFringe(GameStateNode rootNode) {
        queue = new PriorityQueue<GameStateNode>(Comparator.comparingInt(GameStateNode::getPathCost));
        queue.add(rootNode);
    }

    public boolean isFringeEmpty() {
        return queue.isEmpty();
    }

    public GameStateNode checkNextNode() {
        GameStateNode currentNode = queue.poll();
        System.out.println("Current node is " + currentNode.toString());
        if (currentNode == null) return null; //todo: handle an empty queue - probably failed search

        boolean goal = CheckGoalState(currentNode.getState());
        if(goal == true) return currentNode;

        var expandedNodes = currentNode.expand();
        System.out.println(String.format("Node failed the test, expanded to %d", expandedNodes.size()));
        for (GameStateNode expandedNode : expandedNodes) {
            queue.add(expandedNode);
        }

        return null;
    }

    public static boolean CheckGoalState(String[] state) {
        for (int i = 0; i < state.length-1; i++) {
            if(state[i] == state[i+1]) return false;
        }
        return true;
    }
}
