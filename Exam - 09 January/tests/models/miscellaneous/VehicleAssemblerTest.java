package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;


public class VehicleAssemblerTest {


    private Assembler assembler;
    private AttackModifyingPart part1;
    private HitPointsModifyingPart part2;
    private DefenseModifyingPart part3;
    private AttackModifyingPart part4;
    private AttackModifyingPart part5;
    private DefenseModifyingPart part6;
    private DefenseModifyingPart part7;
    private HitPointsModifyingPart part8;
    private HitPointsModifyingPart part9;


    @Before
    public void setUp() throws Exception {

        this.assembler = new VehicleAssembler();
        this.part1 = Mockito.mock(AttackModifyingPart.class);
        this.part2 = Mockito.mock(HitPointsModifyingPart.class);
        this.part3 = Mockito.mock(DefenseModifyingPart.class);

        this.part4 = Mockito.mock(AttackModifyingPart.class);
        this.part5 = Mockito.mock(AttackModifyingPart.class);

        this.part6 = Mockito.mock(DefenseModifyingPart.class);
        this.part7 = Mockito.mock(DefenseModifyingPart.class);

        this.part8 = Mockito.mock(HitPointsModifyingPart.class);
        this.part9 = Mockito.mock(HitPointsModifyingPart.class);



    }

    @Test
    public void getTotalWeight() {

        Mockito.when(this.part1.getWeight()).thenReturn(22.22);
        Mockito.when(this.part2.getWeight()).thenReturn(22.22);
        Mockito.when(this.part3.getWeight()).thenReturn(22.22);

        this.assembler.addArsenalPart(part1);
        this.assembler.addEndurancePart(part2);
        this.assembler.addShellPart(part3);

        double equals = 66.66;
        Assert.assertEquals(equals,this.assembler.getTotalWeight(),1);
    }

    @Test
    public void getTotalPrice() {

        Mockito.when(this.part1.getPrice()).thenReturn(BigDecimal.valueOf(22.22));
        Mockito.when(this.part2.getPrice()).thenReturn(BigDecimal.valueOf(22.22));
        Mockito.when(this.part3.getPrice()).thenReturn(BigDecimal.valueOf(22.22));

        this.assembler.addArsenalPart(part1);
        this.assembler.addEndurancePart(part2);
        this.assembler.addShellPart(part3);

        double equals = 66.66;
        Assert.assertEquals(equals,this.assembler.getTotalPrice().doubleValue(),1);
    }

    @Test
    public void getTotalAttackModification() {
        Mockito.when(this.part4.getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(this.part5.getAttackModifier()).thenReturn(Integer.MAX_VALUE);

        this.assembler.addArsenalPart(part4);
        this.assembler.addArsenalPart(part5);
        long equals = (long)(this.part4.getAttackModifier())+this.part5.getAttackModifier();

        Assert.assertEquals(equals,this.assembler.getTotalAttackModification());

    }

    @Test
    public void getTotalDefenseModification() {
        Mockito.when(this.part6.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(this.part7.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);

        this.assembler.addShellPart(part6);
        this.assembler.addShellPart(part7);
        long equals = (long)(this.part6.getDefenseModifier())+this.part7.getDefenseModifier();

        Assert.assertEquals(equals,this.assembler.getTotalDefenseModification());
    }

    @Test
    public void getTotalHitPointModification() {

        Mockito.when(this.part8.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(this.part9.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);

        this.assembler.addEndurancePart(part8);
        this.assembler.addEndurancePart(part9);
        long equals = (long)(this.part8.getHitPointsModifier())+this.part9.getHitPointsModifier();

        Assert.assertEquals(equals,this.assembler.getTotalHitPointModification());
    }
        @Test
        public void addArsenalPart1() {

            Assembler assembler = new VehicleAssembler();
            AttackModifyingPart part = Mockito.mock(AttackModifyingPart.class);
            AttackModifyingPart part1 = Mockito.mock(AttackModifyingPart.class);
            AttackModifyingPart part2 = Mockito.mock(AttackModifyingPart.class);



            assembler.addArsenalPart(part);
            assembler.addArsenalPart(part1);
            assembler.addArsenalPart(part2);

            int actualSize = 0;

            try {
                Field arsenalParts = assembler.getClass().getDeclaredField("arsenalParts");

                arsenalParts.setAccessible(true);
                List<AttackModifyingPart> parts = (List<AttackModifyingPart>) arsenalParts.get(assembler);

                actualSize= parts.size();

            }catch (NoSuchFieldException | IllegalAccessException e){
                e.printStackTrace();
            }

            int expectedSize = 3;

            Assert.assertEquals(expectedSize,actualSize);
        }

       
}