package dev.fanoulis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class IDSGameFringe {
	private ArrayList<GameStateNode> queue = new ArrayList<>();
	// holds states that have been queued for expansion once already
	// when they're finally expanded, if their children are goal states we return those
	// else, if we encounter them again, we filter them out.
	// implement assignment requirement from page 4
	private final HashSet<String> discovered = new HashSet<>();
	private int nodesExpanded = 0;

	public IDSGameFringe(GameStateNode root) {
		queue.add(root);
	}

	public int getNodesExpanded() {
		return nodesExpanded;
	}

	public GameStateNode goDeeper() {

		Vector<GameStateNode> temp = new Vector<>();
		for (GameStateNode node : queue) {
			var expanded = node.expand();
			nodesExpanded += expanded.size();
			for (GameStateNode i : expanded) {
				if (i.isGoalState()) {
					return i;
				} else {
					var stateStr = String.join("|", i.getState());
					if (discovered.contains(stateStr)) continue;
					temp.add(i);
					discovered.add(stateStr);
				}
			}
		}

		queue = new ArrayList<>(temp);
		return null;
	}
}
