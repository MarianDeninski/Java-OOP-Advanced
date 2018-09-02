package panzer.models.parts;

import panzer.contracts.AttackModifyingPart;

import java.math.BigDecimal;

public class ArsenalPart extends Parts implements AttackModifyingPart {

    private int attackModifier;

    public ArsenalPart(String model, double weight, BigDecimal price, int attackModifier) {
        super(model, weight, price);
        this.attackModifier = attackModifier;

    }

    @Override
    public int getAttackModifier() {
        return this.attackModifier;
    }
    @Override
    protected String getThePart() {

        return "+"+this.getAttackModifier()+" Attack";
    }
}
