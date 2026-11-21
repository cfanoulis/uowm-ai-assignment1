package dev.fanoulis;

import java.util.PriorityQueue;
import java.util.Random;

//todo: this should be extracted into a base Fringe class! D-R-Y!
public class AStarGameFringe {

	private final PriorityQueue<HeuristicGameNode> queue;

	public AStarGameFringe(HeuristicGameNode rootNode) {
		queue = new PriorityQueue<>(AStarGameFringe::NodeComparator);
		queue.add(rootNode);
	}

	public boolean isFringeEmpty() {
		return queue.isEmpty();
	}

	public HeuristicGameNode checkNextNode() {
		HeuristicGameNode currentNode = queue.poll();
		if (currentNode == null) return null;

		if (currentNode.isGoalState()) return currentNode;

		var expandedNodes = currentNode.expand();
		queue.addAll(expandedNodes);

		return null;
	}

	//implement random selection here as well for comparing amongst equal-cost states when sorting for priority
	//as required by assignment, pg.4
	public static int NodeComparator(HeuristicGameNode a, HeuristicGameNode b) {
		int result = Integer.compare(a.getFCost(), b.getFCost());
		if (result != 0) return result;

		Random r = new Random();
		if (r.nextBoolean()) return -1;
		return 1;
	}

}
