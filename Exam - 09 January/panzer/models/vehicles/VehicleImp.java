package panzer.models.vehicles;

import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.models.miscellaneous.VehicleAssembler;

import java.lang.ref.PhantomReference;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VehicleImp implements Vehicle {

    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defense;
    private int hitPoints;
    private Assembler assembler;
    private List<Part> parts;

    protected VehicleImp(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        this.model = model;
        setWeight(weight);
        setPrice(price);
        setAttack(attack);
        setDefense(defense);
        setHitPoints(hitPoints);
        this.assembler = new VehicleAssembler();
        this.parts = new LinkedList<>();

    }

    protected void setPrice(BigDecimal price) {

        this.price = new BigDecimal(price.doubleValue());
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    protected void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getTotalWeight() {
        return this.weight + this.assembler.getTotalWeight();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.price.add(this.assembler.getTotalPrice());
    }

    @Override
    public long getTotalAttack() {
        return this.attack + this.assembler.getTotalAttackModification();
    }

    @Override
    public long getTotalDefense() {
        return this.defense + this.assembler.getTotalDefenseModification();
    }

    @Override
    public long getTotalHitPoints() {
        return this.hitPoints + this.assembler.getTotalHitPointModification();
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {

        this.assembler.addArsenalPart(arsenalPart);
        this.parts.add(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.assembler.addShellPart(shellPart);
        this.parts.add(shellPart);

    }

    @Override
    public void addEndurancePart(Part endurancePart) {

        this.assembler.addEndurancePart(endurancePart);
        this.parts.add(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() {
        return null;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName() + " - "+this.model);
        sb.append(System.lineSeparator());
        sb.append(String.format("Total Weight: %.3f",this.getTotalWeight()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Total Price: %.3f",this.getTotalPrice()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Attack: %s",this.getTotalAttack()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Defense: %s",this.getTotalDefense()));
        sb.append(System.lineSeparator());
        sb.append(String.format("HitPoints: %s",this.getTotalHitPoints()));
        sb.append(System.lineSeparator());
        if(this.parts.size()==0){
            sb.append("Parts: None");
        }else {
            sb.append("Parts: " + this.parts.stream().map(Part::getModel)
                    .collect(Collectors.joining(", ")));
        }


        return sb.toString().trim();

    }
}
