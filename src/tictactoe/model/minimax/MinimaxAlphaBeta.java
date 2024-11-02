package tictactoe.model.minimax;

import tictactoe.model.state.State;
import tictactoe.model.state.StateChild;

import java.util.ArrayList;
import java.util.List;

public class MinimaxAlphaBeta {
    private final int numPlys;

    public MinimaxAlphaBeta(int numPlys) {
        this.numPlys = numPlys;
    }

    public StateChild alphaBetaSearch(StateChild node, int depth, double alpha, double beta) {
        return MinimaxRecursive(node, depth, alpha, beta);
    }

    private StateChild MinimaxRecursive(StateChild node, int depth, double alpha, double beta){
        State nodeState = node.getState();

        if (depth >= numPlys || nodeState.isFinished()) {
            nodeState.setMinimax(nodeState.getUtility());
            System.out.println(nodeState);
            System.out.println(nodeState.getUtility());
            return node;
        }

        double max = alpha;
        double min = beta;
        double bestValue = nodeState.getIsMax() ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        StateChild bestValueState = null;

        List<StateChild> children = orderChildrenWithHeuristics(nodeState.getChildren(depth != 0));

        for (StateChild child : children) {
            StateChild minimaxBackup = MinimaxRecursive(child, depth + 1, max, min);
            double v = minimaxBackup.getState().getMinimax();
            if (nodeState.getIsMax()) {
                if (v > max) {
                    max = v;
                    bestValueState = child;
                }
                bestValue = Math.max(bestValue, v);
                child.getState().setMinimax(bestValue);
                if(bestValue > beta){
                    return bestValueState;
                }
            }
            else {
                if (v < min) {
                    min = v;
                    bestValueState = child;
                }
                bestValue = Math.min(bestValue, v);
                child.getState().setMinimax(bestValue);
                if(bestValue < alpha){
                    return bestValueState;
                }
            }
        }
        return bestValueState == null ? children.get(0) : bestValueState;
    }

    public List<StateChild> orderChildrenWithHeuristics(List<StateChild> children) {
        List<StateChild> sortedChildren = new ArrayList<>(children);

//        if(!children.get(0).getState().getIsMax()) {
//            sortedChildren.sort((s1, s2) -> {
//                if (heuristic(s1) > heuristic(s2)) return 1;
//                else if (heuristic(s1) < heuristic(s2)) return -1;
//                return 0;
//            });
//        }
//        else {
//            sortedChildren.sort((s1, s2) -> {
//                if (heuristic(s1) < heuristic(s2)) return 1;
//                else if (heuristic(s1) > heuristic(s2)) return -1;
//                return 0;
//            });
//        }
        return sortedChildren;
    }
}
