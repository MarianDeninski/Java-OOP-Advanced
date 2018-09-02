package hell.entities.miscellaneous;


import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;



public class HeroInventoryTest {

    private Inventory heroInventory;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Recipe recipe1;
    private Recipe recipe2;

    @Before
    public void setUp() throws Exception {

        this.heroInventory = new HeroInventory();



        this.item1 = Mockito.mock(Item.class);
        this.item2 = Mockito.mock(Item.class);
        this.item3 = Mockito.mock(Item.class);
        this.item4 = Mockito.mock(Item.class);

        Mockito.when(this.item2.getName()).thenReturn("koci");
        Mockito.when(this.item3.getName()).thenReturn("heii");
        Mockito.when(this.item1.getName()).thenReturn("boci");
        Mockito.when(this.item4.getName()).thenReturn("kocko");
        this.recipe1 = Mockito.mock(Recipe.class);
        List<String>here = new ArrayList<>();
        here.add("koci");
        here.add("boci");

        List<String>here1 = new ArrayList<>();
        here.add("heii");
        here.add("kocko");

        Mockito.when((this.recipe1.getRequiredItems())).thenReturn(here);
        Mockito.when((this.recipe1.getName())).thenReturn("piron");
        Mockito.when(this.recipe1.getDamageBonus()).thenReturn(2_000_000_000);
        this.recipe2 = Mockito.mock(Recipe.class);
        Mockito.when((this.recipe2.getRequiredItems())).thenReturn(here1);
        Mockito.when((this.recipe2.getName())).thenReturn("piron1");
        Mockito.when(this.recipe2.getDamageBonus()).thenReturn(2_000_000_000);

    }

    @Test
    public void getTotalStrengthBonus() {

        Mockito.when(this.item1.getStrengthBonus()).thenReturn(1_000_000_000);

        Mockito.when(this.item2.getStrengthBonus()).thenReturn(1_000_000_000);

        Inventory hero =  this.heroInventory;
        hero.addCommonItem(this.item1);
        hero.addCommonItem(this.item2);

        long expect = this.item1.getStrengthBonus() + this.item2.getStrengthBonus();

        Assert.assertEquals(expect,hero.getTotalStrengthBonus());
    }
    @Test
    public void getTotalAgilityBonus() {

        Mockito.when(this.item1.getAgilityBonus()).thenReturn(1_000_000_000);

        Mockito.when(this.item2.getAgilityBonus()).thenReturn(1_000_000_000);

        Inventory hero =  this.heroInventory;
        hero.addCommonItem(this.item1);
        hero.addCommonItem(this.item2);

        long expect = this.item1.getAgilityBonus() + this.item2.getAgilityBonus();

        Assert.assertEquals(expect,hero.getTotalAgilityBonus());
    }

    @Test
    public void getTotalIntelligenceBonus() {

        Mockito.when(this.item1.getIntelligenceBonus()).thenReturn(1_000_000_000);

        Mockito.when(this.item2.getIntelligenceBonus()).thenReturn(1_000_000_000);

        Inventory hero =  this.heroInventory;
        hero.addCommonItem(this.item1);
        hero.addCommonItem(this.item2);

        long expect = this.item1.getIntelligenceBonus() + this.item2.getIntelligenceBonus();

        Assert.assertEquals(expect,hero.getTotalIntelligenceBonus());
    }

    @Test
    public void getTotalHitPointsBonus() {

        Mockito.when(this.item1.getHitPointsBonus()).thenReturn(2_000_000_000);

        Mockito.when(this.item2.getHitPointsBonus()).thenReturn(2_000_000_000);

        Inventory hero =  this.heroInventory;
        hero.addCommonItem(this.item1);
        hero.addCommonItem(this.item2);

        long expect = (long) this.item1.getHitPointsBonus() + (long) this.item2.getHitPointsBonus();

        Assert.assertEquals(expect,hero.getTotalHitPointsBonus());
    }

    @Test
    public void getTotalDamageBonus() {
        Mockito.when(this.item1.getDamageBonus()).thenReturn(1_000_000_000);

        Mockito.when(this.item2.getDamageBonus()).thenReturn(1_000_000_000);

        HeroInventory hero = (HeroInventory) this.heroInventory;
        hero.addCommonItem(this.item1);
        hero.addCommonItem(this.item2);

        long expect = this.item1.getDamageBonus() + this.item2.getDamageBonus();

        Assert.assertEquals(expect,hero.getTotalDamageBonus());
    }

    @Test
    public void addCommonItem() {

        Inventory hero =  this.heroInventory;

        hero.addCommonItem(this.item1);
        hero.addCommonItem(this.item2);
        hero.addRecipeItem(this.recipe1);

        Assert.assertEquals(0,hero.getTotalDamageBonus());

    }

    @Test
    public void addRecipeItem() {

        Inventory hero =  this.heroInventory;

        hero.addCommonItem(this.item1);

        hero.addRecipeItem(this.recipe1);

        Assert.assertEquals(0,hero.getTotalDamageBonus());
    }
    @Test
    public void addRecipeItem2() {

        Inventory hero =  this.heroInventory;

        Mockito.when(this.item3.getDamageBonus()).thenReturn(1_000_000_000);
        hero.addCommonItem(this.item3);

        hero.addRecipeItem(this.recipe1);

        Assert.assertEquals(1_000_000_000,hero.getTotalDamageBonus());
    }

    @Test
    public void addRecipeItem3() {

        Inventory hero =  this.heroInventory;

        Mockito.when(this.item2.getDamageBonus()).thenReturn(2_000_000_000);
        Mockito.when(this.item1.getDamageBonus()).thenReturn(2_000_000_000);
        Mockito.when(this.item3.getDamageBonus()).thenReturn(2_000_000_000);
        Mockito.when(this.item4.getDamageBonus()).thenReturn(2_000_000_000);

        hero.addRecipeItem(this.recipe1);
        hero.addRecipeItem(this.recipe2);
        hero.addCommonItem(this.item2);
        hero.addCommonItem(this.item1);
        hero.addCommonItem(this.item3);
        hero.addCommonItem(this.item4);


        long num = 4_000_000_000L;
        Assert.assertEquals( num,hero.getTotalDamageBonus());
    }
}