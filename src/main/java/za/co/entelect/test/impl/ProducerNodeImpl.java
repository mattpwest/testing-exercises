package za.co.entelect.test.impl;

import za.co.entelect.test.LabourProvider;
import za.co.entelect.test.Material;
import za.co.entelect.test.Node;
import za.co.entelect.test.NodeProducer;

public class ProducerNodeImpl extends AbstractNodeImpl implements Node, NodeProducer {

    private LabourProvider labourProvider;
    private Material producedMaterial;
    private double productionPerDay;
    private double maxStorage;
    private double amount;
    private int numLabourersRequired;

    public ProducerNodeImpl(LabourProvider labourProvider, int numLabourersRequired,
                            Material material, double productionPerDay, double maxStorage) {
        super();

        this.labourProvider = labourProvider;
        this.numLabourersRequired = numLabourersRequired;
        this.producedMaterial = material;
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

    public int getNumLabourersRequired() {
        return numLabourersRequired;
    }

    @Override
    public void produce() {
        int labourersReceived = labourProvider.provideDayLabourers(numLabourersRequired);
        this.amount += productionPerDay * (getNumLabourersRequired() / (double) labourersReceived);
        this.amount = Math.min(amount, maxStorage);
    }
}
