package panzer.models.vehicles;

import java.math.BigDecimal;

public class Revenger extends VehicleImp {
    public Revenger(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price, attack, defense, hitPoints);
    }

    @Override
    protected void setAttack(int attack) {
        super.setAttack((int)(attack*2.5));
    }

    @Override
    protected void setDefense(int defense) {
        super.setDefense((int)(defense*0.5));
    }

    @Override
    protected void setHitPoints(int hitPoints) {
        super.setHitPoints((int)(hitPoints*0.5));
    }

    @Override
    public void setPrice(BigDecimal price) {

        double asd = price.doubleValue()*1.5;
        super.setPrice(BigDecimal.valueOf(asd));
    }
}
