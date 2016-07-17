package za.co.entelect.test;

public interface NodeProducer {
    Material getProducedMaterial();
    double getProductionPerDay();
    double getMaxStorage();
    double getStoredAmount();
    void produce();
}
