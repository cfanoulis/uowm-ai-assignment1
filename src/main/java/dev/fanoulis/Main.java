package dev.fanoulis;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        String initialState[] = {"B", "B", "B", "B", "E", "W", "W", "W", "W"};
        GameStateNode rootNode = new GameStateNode(initialState);

        var fringe = new BFSGameFringe(rootNode);
        GameStateNode goalNode = null;

        while (goalNode == null) {
            if (fringe.isFringeEmpty()) {
                break;
            }

            goalNode = fringe.checkNextNode();
        }

        if (goalNode == null) {
            System.out.println("Search failed, fringe empty. Leaving...");
        } else {
            System.out.println("Search finished, result found!\n" + goalNode);
        }
        return;
    }
}
