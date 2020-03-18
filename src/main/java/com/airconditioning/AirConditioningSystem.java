package com.airconditioning;

public class AirConditioningSystem {

    private Thermomether thermomether;
    private double temperatureThreshold;
    private boolean open;

    public AirConditioningSystem() {
        open = false;
    }

    public void checkAirConditioningSystem() {
        this.open = (thermomether.getTemperature() >= temperatureThreshold);
    }

    public boolean isOpen() {
        return open;
    }

    public void setThermomether(Thermomether thermomether) {
        this.thermomether = thermomether;
    }

    public void setTemperatureThreshold(double temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }


}
