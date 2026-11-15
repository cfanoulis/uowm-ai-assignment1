package dev.fanoulis;


public class Main {
    static void main() {
        String[] initialState = {"B", "B", "B", "B", "E", "W", "W", "W", "W"};
        GameStateNode rootNode = new GameStateNode(initialState);

        var bfsGameFringe = new BFSGameFringe(rootNode);
        var idsGameFringe = new IDSGameFringe(rootNode);

        GameStateNode bfsGoalNode = null;
        GameStateNode idsGoalNode = null;


        int i=0;
        while (bfsGoalNode == null) {
            if (bfsGameFringe.isFringeEmpty()) {
                break;
            }

            i++;
            bfsGoalNode = bfsGameFringe.checkNextNode();
        }

        if (bfsGoalNode == null) {
            System.out.println("Search failed, bfsGameFringe empty. Leaving...");
        } else {
            System.out.println("Search finished, result found after "+ i+ " iterations!\n" + bfsGoalNode);
        }

        while (idsGoalNode == null) {
            idsGoalNode = idsGameFringe.goDeeper();
        }

        System.out.println("Search finished, result found after "+ idsGameFringe.getIterations() + " iterations!\n" + idsGoalNode);
    }
}
