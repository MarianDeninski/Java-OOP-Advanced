package cresla.entities.containers.models.modules;

import cresla.interfaces.AbsorbingModule;

abstract class AbstractAbsorbing extends AbstractModule implements AbsorbingModule {

    private int heatAbsorbing;

    AbstractAbsorbing(int id, int parameter) {
        super(id);
        this.heatAbsorbing = parameter;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " Module - " + super.getId() + "\n" +
                "Heat Absorbing: " + this.heatAbsorbing;
    }
}