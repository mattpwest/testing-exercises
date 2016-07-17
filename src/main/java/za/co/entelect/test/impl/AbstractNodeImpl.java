package za.co.entelect.test.impl;

import za.co.entelect.test.Node;
import za.co.entelect.test.Road;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractNodeImpl implements Node {

    private Map<Node, Road> neighbours;

    public AbstractNodeImpl() {
        this.neighbours = new HashMap<>();
    }

    @Override
    public void addNeighbour(Node neighbour, Road road) {
        neighbours.put(neighbour, road);
    }

    @Override
    public Set<Node> getNeighbours() {
        return neighbours.keySet();
    }

    @Override
    public Road getRoadToNeighbour(Node neighbour) {
        return neighbours.get(neighbour);
    }
}
