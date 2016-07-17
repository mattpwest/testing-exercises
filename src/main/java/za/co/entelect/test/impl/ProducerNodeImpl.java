package za.co.entelect.test.impl;

import za.co.entelect.test.Material;
import za.co.entelect.test.Node;
import za.co.entelect.test.NodeProducer;

public class ProducerNodeImpl extends AbstractNodeImpl implements Node, NodeProducer {

    private Material producedMaterial;
    private double productionPerDay;
    private double maxStorage;
    private double amount;

    public ProducerNodeImpl(Material material, double productionPerDay, double maxStorage) {
        super();

        producedMaterial = material;
        this.productionPerDay = productionPerDay;
        this.maxStorage = maxStorage;
        this.amount = 0.0;
    }

    @Override
    public Material getProducedMaterial() {
        return producedMaterial;
    }

    @Override
    public double getProductionPerDay() {
        return productionPerDay;
    }

    @Override
    public double getMaxStorage() {
        return maxStorage;
    }

    @Override
    public double getStoredAmount() {
        return amount;
    }

    @Override
    public void produce() {
        this.amount += productionPerDay;
        this.amount = Math.min(amount, maxStorage);
    }
}
