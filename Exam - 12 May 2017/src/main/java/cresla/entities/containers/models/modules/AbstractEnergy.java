package cresla.entities.containers.models.modules;

import cresla.interfaces.EnergyModule;

abstract class AbstractEnergy extends AbstractModule implements EnergyModule {

    private int energyOutput;

    AbstractEnergy(int id, int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " Module - " + super.getId() + "\n" +
                "Energy Output: " + this.energyOutput;
    }
}
