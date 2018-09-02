package cresla.entities.containers.models.reactors;

import cresla.interfaces.Container;


public class HeatReactor extends AbstractReactor {


    private int heatReductionIndex;

    public HeatReactor( int id,Container container,int heatReductionIndex) {
        super(container,id);
        this.heatReductionIndex = heatReductionIndex;

    }
    @Override
    public long getTotalEnergyOutput() {
        long result =  super.getModuleContainer().getTotalEnergyOutput();

        if(super.getModuleContainer().getTotalHeatAbsorbing()+this.heatReductionIndex<result){
            return 0;
        }else{
            return result;
        }
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getModuleContainer().getTotalHeatAbsorbing()+this.heatReductionIndex;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName() + " - " + this.getId());
        sb.append("\n");
        sb.append("Energy Output: " + this.getTotalEnergyOutput());
        sb.append("\n");
        sb.append("Heat Absorbing: " + this.getTotalHeatAbsorbing());
        sb.append("\n");
        sb.append("Modules: "+this.getModuleCount());
        return sb.toString();
    }
}
