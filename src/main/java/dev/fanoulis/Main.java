// University of Western Macedonia
// School of Engineering
// Electrical & Computer Engineering Dept.

// Subject: Artificial Intelligence
// Assignment 1
// Author: Charalampos Fanoulis <ece02266 at uowm gr
package dev.fanoulis;


public class Main {
	static void main() {
		String[] initialState = {"B", "B", "B", "B", "E", "W", "W", "W", "W"};
		GameStateNode rootNode = new GameStateNode(initialState);

		var ucsGameFringe = new UCSGameFringe(rootNode);
		var idsGameFringe = new IDSGameFringe(rootNode);
		var astarGameFringe = new AStarGameFringe(new HeuristicGameNode(initialState));

		GameStateNode bfsGoalNode = null;
		GameStateNode idsGoalNode = null;
		HeuristicGameNode astarGoalNode = null;


		int i = 0;
		while (bfsGoalNode == null) {
			if (ucsGameFringe.isFringeEmpty()) {
				break;
			}

			i++;
			bfsGoalNode = ucsGameFringe.checkNextNode();
		}

		if (bfsGoalNode == null) {
			System.out.println("Search failed, ucsGameFringe empty. Leaving...");
		} else {
			System.out.println("Search finished, result found after " + i + " expansions!\n" + bfsGoalNode);
		}

		while (idsGoalNode == null) {
			idsGoalNode = idsGameFringe.goDeeper();
		}

		System.out.println("Search finished, result found after " + idsGameFringe.getNodesExpanded() + " expansions!\n" + idsGoalNode);

		i = 0;
		while (astarGoalNode == null && !astarGameFringe.isFringeEmpty()) {
			i++;
			astarGoalNode = astarGameFringe.checkNextNode();
		}

		if (bfsGoalNode == null) {
			System.out.println("Search failed, A* fringe empty. Leaving...");
		} else {
			System.out.println("Search finished, result found after " + i + " expansions!\n" + astarGoalNode);
		}
	}
}
