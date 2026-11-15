package dev.fanoulis;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        String initialState[] = {"B", "B", "B", "B", "E", "W", "W", "W", "W"};
        GameStateNode rootNode = new GameStateNode(initialState);
        System.out.println(rootNode.toString());
        List<GameStateNode> extendedNodes = rootNode.expand();
        for (GameStateNode node : extendedNodes) {
            System.out.println(node.toString());
        }
    }
}
