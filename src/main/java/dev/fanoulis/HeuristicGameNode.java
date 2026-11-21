package dev.fanoulis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HeuristicGameNode extends GameStateNode {
	public HeuristicGameNode(String[] state) {
		super(state);
	}

	public HeuristicGameNode(@NotNull GameAction genesisAction, @NotNull HeuristicGameNode parent) {
		super(genesisAction, parent);
	}

	// This implementation is based on the assumption that "the most complete solution is the one we should prioritize"
	// The final goal has 0 same pairs. Therefore, the more errors we have in each state, the further we are
	// from the goal. This heuristic is not the best, as it does not consider the cost to solve each error.
	// However, I believe that it finds balance between speed & complexity, therefore it is suitable for our
	// educational use case.

	public int getFCost() {
		int errors = 0;
		String[] state = getState();

		for (int i = 1; i < state.length; i++) {
			if (state[i].equalsIgnoreCase(state[i - 1])) errors++;
		}

		return errors + getPathCost(); // f = g+h;
	}

	@Override
	public List<HeuristicGameNode> expand() {
		ArrayList<HeuristicGameNode> expandedNodes = new ArrayList<>();
		int emptyIdx = findEmpty();

		//  Implementation according to pg 4
		for (int i = 3; i > 0; i--) {
			if (emptyIdx + i <= state.length - 1) {
				expandedNodes.add(new HeuristicGameNode(new GameAction(i, "RIGHT"), this));
			}
		}

		for (int i = 1; i < 4; i++) {
			if (emptyIdx - i > 0) {
				expandedNodes.add(new HeuristicGameNode(new GameAction(i, "LEFT"), this));
			}
		}

		return expandedNodes;
	}
}
