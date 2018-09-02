package panzer.models.parts;

import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class EndurancePart extends  Parts implements HitPointsModifyingPart {

    private int hitPointsModifier;

    public EndurancePart(String model, double weight, BigDecimal price, int hitPointsModifier) {
        super(model, weight, price);
        this.hitPointsModifier = hitPointsModifier;

    }

    @Override
    public int getHitPointsModifier() {
        return this.hitPointsModifier;
    }
    @Override
    protected String getThePart() {

        return "+"+this.getHitPointsModifier()+" HitPoints";
    }
}
