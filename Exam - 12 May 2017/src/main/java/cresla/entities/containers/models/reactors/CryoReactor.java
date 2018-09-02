package cresla.entities.containers.models.reactors;
import cresla.interfaces.Container;

public class CryoReactor extends AbstractReactor {


    private int cryoProductionIndex;

    public CryoReactor( int id,Container container,int cryoProductionIndex) {
        super(container,id);
        this.cryoProductionIndex = cryoProductionIndex;

    }
    @Override
    public long getTotalEnergyOutput() {
        long result =  super.getModuleContainer().getTotalEnergyOutput()*this.cryoProductionIndex;

        if(super.getModuleContainer().getTotalHeatAbsorbing()<result){
            return 0;
        }else{
            return result;
        }
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getModuleContainer().getTotalHeatAbsorbing();
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
