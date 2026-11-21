package dev.fanoulis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// todo: this should be a class that gets extended! D-R-Y!
public class HeuristicGameNode {
	private final String[] state;
	private final int cost;
	private HeuristicGameNode parent;
	private final GameAction genesisAction; //todo: this should be an enum
	private final int depth;

	public HeuristicGameNode(String[] state) {
		// root node
		this.state = state;
		this.cost = 0;
		this.depth = 0;
		this.genesisAction=new GameAction(0,  "ROOT");
	}

	public HeuristicGameNode(@NotNull GameAction genesisAction, @NotNull HeuristicGameNode parent) {
		this.genesisAction = genesisAction;
		this.parent = parent;
		this.depth = parent.depth+1;
		this.cost = parent.cost + genesisAction.cost();

		this.state = genesisAction.runAction(parent.state);
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
			if(state[i].equalsIgnoreCase(state[i-1])) errors++;
		}

		return errors + getPathCost(); // f = g+h;
	}

	public int getPathCost() {
		return cost;
	}

	public boolean isGoalState() {
		for (int i = 1; i < state.length; i++) {
			if(state[i].equalsIgnoreCase(state[i-1])) return false;
		}
		return true;
	}

	public String[] getState() {
		return state.clone();
	}

	private int findEmpty() {
		int emptyidx = -1;
		for (int i = 0; i < state.length; i++) {
			if(state[i].equalsIgnoreCase("E")) {
				emptyidx = i;
				break;
			}
		}
		return emptyidx;
	}

	public List<HeuristicGameNode> expand() {
		ArrayList<HeuristicGameNode> expandedNodes = new ArrayList<>();
		int emptyIdx = findEmpty();

		//  Implementation according to pg 4
		for (int i = 3; i > 0; i--) {
			if(emptyIdx+i <= state.length-1) {
				expandedNodes.add(new HeuristicGameNode(new GameAction(i, "RIGHT"), this));
			}
		}

		for (int i = 1; i < 4; i++) {
			if(emptyIdx-i>0) {
				expandedNodes.add(new HeuristicGameNode(new GameAction(i, "LEFT"), this));
			}
		}

		return expandedNodes;
	}

	public String toString() {
		return String.format("HeuristicStateNode{\n\tstate=%s\n\tmove=%s\n\tcost=%d\n\tdepth=%d\n}", String.join("|", state), genesisAction.toString(), cost, depth);
	}
}
