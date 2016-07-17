package za.co.entelect.test.impl;

import za.co.entelect.test.Node;
import za.co.entelect.test.NodeToll;

public class TollNodeImpl extends AbstractNodeImpl implements Node, NodeToll {

    private double tollAmount;

    public TollNodeImpl(double tollAmount) {
        super();

        this.tollAmount = tollAmount;
    }


    @Override
    public double getTollAmount() {
        return tollAmount;
    }
}
