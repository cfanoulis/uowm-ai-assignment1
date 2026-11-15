package dev.fanoulis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GameStateNode {
    private final String state[];
    private final int cost;
    private GameStateNode parent;
    private final GameAction genesisAction; //todo: this should be an enum
    private final int depth;

    public GameStateNode(String[] state) {
    // root node
        this.state = state;
        this.cost = 0;
        this.depth = 0;
        this.genesisAction=new GameAction(0,  "ROOT");
    };

    public GameStateNode(@NotNull GameAction genesisAction, @NotNull GameStateNode parent) {
        this.genesisAction = genesisAction;
        this.parent = parent;
        this.depth = parent.depth+1;
        this.cost = parent.cost + genesisAction.cost();

        this.state = genesisAction.runAction(parent.state);
    }

    public int getActionCost() {
        return genesisAction.cost();
    }

    public int getPathCost() {
        return cost;
    }

    public boolean isGoalState() {
        for (int i = 0; i < state.length-1; i++) {
            if(state[i] == state[i+1]) return false;
        }
        return true;
    }

    public String[] getState() {
        return state.clone();
    }

    private int findEmpty() {
        int emptyidx = -1;
        for (int i = 0; i < state.length; i++) {
            if(state[i] == "E") {
                emptyidx = i;
                break;
            }
        }
        return emptyidx;
    }

    public List<GameStateNode> expand() {
        ArrayList<GameStateNode> expandedNodes = new ArrayList<GameStateNode>();
        int emptyIdx = findEmpty();

        //  Implementation according to pg 4
        for (int i = 3; i > 0; i--) {
            if(emptyIdx+i <= state.length-1) {
                expandedNodes.add(new GameStateNode(new GameAction(i, "RIGHT"), this));
            }
        }

        for (int i = 1; i < 4; i++) {
            if(emptyIdx-i>0) {
                expandedNodes.add(new GameStateNode(new GameAction(i, "LEFT"), this));
            }
        }

        return expandedNodes;
    }

    public String toString() {
        return String.format("GameStateNode{\n\tstate=%s\n\tmove=%s\n\tcost=%d\n\tdepth=%d\n}", String.join("|", state), genesisAction.toString(), cost, depth);
    }
}
