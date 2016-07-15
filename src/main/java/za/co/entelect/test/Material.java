package za.co.entelect.test;

public enum Material {
    Bricks(1.89),
    Concrete(2.4),
    Sand(1.6),
    Water(1.0);

    private double density;

    Material(double density) {
        this.density = density;
    }

    public double getDensity() {
        return density;
    }
}
