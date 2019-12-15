package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;

import static motocrossWorldChampionship.common.ExceptionMessages.INVALID_MODEL;

public abstract class MotorcycleImpl implements Motorcycle {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.horsePower = horsePower;
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if(model == null || model.trim().isEmpty() || model.length() < 4) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model, 4));
        }
        this.model = model;
    }


    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / (this.horsePower * laps);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Motorcycle)) {
            return false;
        }

        Motorcycle compare = (Motorcycle) o;
        return this.getModel().equals(compare.getModel());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
