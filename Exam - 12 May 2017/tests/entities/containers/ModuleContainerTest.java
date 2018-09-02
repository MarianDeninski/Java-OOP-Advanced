package cresla.entities.containers;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ModuleContainerTest {

    private Container container;
    private AbsorbingModule absorbingModule;
    private AbsorbingModule absorbingModule1;
    private AbsorbingModule absorbingModule2;
    private EnergyModule energyModule;
    private EnergyModule energyModule1;
    private EnergyModule energyModule2;

    @Before
    public void setUp()  {

       this.container = new ModuleContainer(2);

       this.absorbingModule = Mockito.mock(AbsorbingModule.class);
       this.absorbingModule1 = Mockito.mock(AbsorbingModule.class);
       this.absorbingModule2 = Mockito.mock(AbsorbingModule.class);
       this.energyModule = Mockito.mock(EnergyModule.class);
       this.energyModule1 = Mockito.mock(EnergyModule.class);
       this.energyModule2 = Mockito.mock(EnergyModule.class);

       Mockito.when(this.absorbingModule.getHeatAbsorbing()).thenReturn(2_000_000_000);
       Mockito.when(this.absorbingModule.getId()).thenReturn(1);

       Mockito.when(this.absorbingModule1.getHeatAbsorbing()).thenReturn(2_000_000_000);
        Mockito.when(this.absorbingModule1.getId()).thenReturn(2);

        Mockito.when(this.energyModule.getEnergyOutput()).thenReturn(2_000_000_000);
        Mockito.when(this.energyModule.getId()).thenReturn(3);

        Mockito.when(this.energyModule1.getEnergyOutput()).thenReturn(2_000_000_000);
        Mockito.when(this.energyModule1.getId()).thenReturn(4);

        Mockito.when(this.absorbingModule2.getHeatAbsorbing()).thenReturn(2_000_000_000);
        Mockito.when(this.absorbingModule2.getId()).thenReturn(5);

        Mockito.when(this.energyModule2.getEnergyOutput()).thenReturn(2_000_000_000);
        Mockito.when(this.energyModule2.getId()).thenReturn(6);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addEnergyModuleNull(){
        this.container.addEnergyModule(null);

    }
    @Test(expected = IllegalArgumentException.class)
    public void addAbsorbingModuleNull(){
        this.container.addAbsorbingModule(null);

    }
    @Test
    public void addEnergyModule() {
        this.container.addEnergyModule(this.energyModule);
        this.container.addEnergyModule(this.energyModule1);
        Assert.assertEquals(4000000000L,this.container.getTotalEnergyOutput());
    }

    @Test
    public void addAbsorbingModule() {
        this.container.addAbsorbingModule(this.absorbingModule);
        this.container.addAbsorbingModule(this.absorbingModule1);
        Assert.assertEquals(4000000000L,this.container.getTotalHeatAbsorbing());
    }

    @Test
    public void addEnergyModuleBiggerCapacity() {
        this.container.addEnergyModule(this.energyModule);
        this.container.addEnergyModule(this.energyModule1);
        this.container.addEnergyModule(this.energyModule2);
        Assert.assertEquals(4000000000L,this.container.getTotalEnergyOutput());
    }
    @Test
    public void addAbsorbingModuleBiggerCapacity() {
        this.container.addAbsorbingModule(this.absorbingModule);
        this.container.addAbsorbingModule(this.absorbingModule1);
        this.container.addAbsorbingModule(this.absorbingModule2);
        Assert.assertEquals(4000000000L,this.container.getTotalHeatAbsorbing());
    }
    @Test(expected = NoSuchElementException.class)
    public void zeroSize() {
        this.container = new ModuleContainer(0);
        this.container.addAbsorbingModule(this.absorbingModule);
    }
    @Test
    public void removeModule() {
        this.container.addAbsorbingModule(this.absorbingModule);
        this.container.addEnergyModule(this.energyModule);
        this.container.addEnergyModule(this.energyModule1);
        Assert.assertEquals(0, this.container.getTotalHeatAbsorbing());
        Assert.assertEquals(4_000_000_000L, this.container.getTotalEnergyOutput());
    }






}