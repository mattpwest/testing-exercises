package za.co.entelect.test;

import java.util.Set;

public interface Node {

    void addNeighbour(Node neighbour, Road road);
    Set<Node> getNeighbours();
    Road getRoadToNeighbour(Node neighbour);

}
