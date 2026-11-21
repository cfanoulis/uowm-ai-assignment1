package dev.fanoulis;


public class Main {
    static void main() {
        String[] initialState = {"B", "B", "B", "B", "E", "W", "W", "W", "W"};
        GameStateNode rootNode = new GameStateNode(initialState);

        var ucsGameFringe = new UCSGameFringe(rootNode);
        var idsGameFringe = new IDSGameFringe(rootNode);

        GameStateNode bfsGoalNode = null;
        GameStateNode idsGoalNode = null;


        int i=0;
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
            System.out.println("Search finished, result found after "+ i + " expansions!\n" + bfsGoalNode);
        }

        while (idsGoalNode == null) {
            idsGoalNode = idsGameFringe.goDeeper();
        }

        System.out.println("Search finished, result found after "+ idsGameFringe.getNodesExpanded() + " expansions!\n" + idsGoalNode);
    }
}
