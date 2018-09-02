package cresla.entities.containers.models.reactors;


import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;
import java.lang.reflect.Field;
import java.util.LinkedList;

public abstract class AbstractReactor  implements Reactor {
    private Container moduleContainer;
    private int id;

    AbstractReactor(Container container, int id) {
        this.moduleContainer = container;
        this.id = id;
    }

    Container getModuleContainer() {
        return this.moduleContainer;
    }

    @Override
    public abstract long getTotalEnergyOutput();

    @Override
    public abstract long getTotalHeatAbsorbing();

    @Override
    @SuppressWarnings(value = "unchecked")
    public int getModuleCount()  {
        try {
            Class moduleContainerClass = ModuleContainer.class;
            Field field = moduleContainerClass.getDeclaredFields()[1];
            field.setAccessible(true);
            return ((LinkedList<Field>)field.get(this.moduleContainer)).size();
        } catch (IllegalAccessException e) {
            return 0;
        }
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.moduleContainer.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.moduleContainer.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }
}
