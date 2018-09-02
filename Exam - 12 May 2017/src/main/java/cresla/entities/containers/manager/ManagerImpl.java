package cresla.entities.containers.manager;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.*;
import cresla.interfaces.Module;
import cresla.entities.containers.models.modules.CooldownSystem;
import cresla.entities.containers.models.modules.CryogenRod;
import cresla.entities.containers.models.modules.HeatProcessor;
import cresla.entities.containers.models.reactors.CryoReactor;
import cresla.entities.containers.models.reactors.HeatReactor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {

    private Map<Integer, Reactor> mapReactors = new HashMap<>();
    private Map<Integer, Module> mapModule = new HashMap<>();
    private int id = 1;
    private long crioReactors;
    private long heatReactors;
    private long energyModels;
    private long absorbingModels;

    @Override
    public String reactorCommand(List<String> arguments) {

        if ("cryo".equalsIgnoreCase(arguments.get(0))) {
            Container container = new ModuleContainer(Integer.parseInt(arguments.get(2)));
            Reactor reactor = new CryoReactor(this.id, container, Integer.parseInt(arguments.get(1)));
            mapReactors.put(this.id, reactor);
            this.crioReactors++;
            return "Created " + arguments.get(0) + " " + "Reactor - " + this.id++;
        } else {
            Container container = new ModuleContainer(Integer.parseInt(arguments.get(2)));

            Reactor reactor = new HeatReactor(this.id, container, Integer.parseInt(arguments.get(1)));
            mapReactors.put(this.id, reactor);
            this.heatReactors++;
            return "Created " + arguments.get(0) + " " + "Reactor - " + this.id++;
        }
    }

    @Override
    public String moduleCommand(List<String> arguments) {

        //Module {reactorId} {type} {additionalParameter}

        if (arguments.get(1).equals("CryogenRod")) {
            EnergyModule module = new CryogenRod(this.id, Integer.parseInt(arguments.get(2)));
            mapReactors.get(Integer.parseInt(arguments.get(0))).addEnergyModule(module);

            this.energyModels++;
            mapModule.put(this.id,module);
            return "Added " + arguments.get(1) + " - " + this.id++ + " to Reactor - " + arguments.get(0);
        } else if (arguments.get(1).equals("HeatProcessor")) {

            AbsorbingModule module = new HeatProcessor(this.id, Integer.parseInt(arguments.get(2)));
            mapReactors.get(Integer.parseInt(arguments.get(0))).addAbsorbingModule(module);

            this.absorbingModels++;
            mapModule.put(this.id,module);
            return "Added " + arguments.get(1) + " - " + this.id++ + " to Reactor - " + arguments.get(0);
        } else if (arguments.get(1).equals("CooldownSystem")) {

            AbsorbingModule module = new CooldownSystem(this.id, Integer.parseInt(arguments.get(2)));
            mapReactors.get(Integer.parseInt(arguments.get(0))).addAbsorbingModule(module);

            this.absorbingModels++;
            mapModule.put(this.id,module);
            return "Added " + arguments.get(1) + " - " + this.id++ + " to Reactor - " + arguments.get(0);

        }
        return null;
    }

    @Override
    public String reportCommand(List<String> arguments) {
        //{id}
        if(this.mapReactors.containsKey(Integer.parseInt(arguments.get(0)))){
            return this.mapReactors.get(Integer.parseInt(arguments.get(0))).toString();
        }else{

            return this.mapModule.get(Integer.parseInt(arguments.get(0))).toString();
        }


    }

    @Override
    public String exitCommand(List<String> arguments) {
        long energyModule = this.mapReactors.entrySet().stream().mapToLong(a -> a.getValue().getTotalEnergyOutput()).sum();
        long headAbsorbing = this.mapReactors.entrySet().stream().mapToLong(a -> a.getValue().getTotalHeatAbsorbing()).sum();

        StringBuilder sb = new StringBuilder();

        sb.append("Cryo Reactors: "+this.crioReactors);
        sb.append("\n");
        sb.append("Heat Reactors: "+this.heatReactors);
        sb.append("\n");
        sb.append("Energy Modules: "+this.energyModels);
        sb.append("\n");
        sb.append("Absorbing Modules: "+this.absorbingModels);
        sb.append("\n");
        sb.append("Total Energy Output: "+energyModule);
        sb.append("\n");
        sb.append("Total Heat Absorbing: "+headAbsorbing);

        return sb.toString();
    }
}
