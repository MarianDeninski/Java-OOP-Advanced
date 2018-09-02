package panzer.models.vehicles;

import java.math.BigDecimal;

public class Vanguard extends VehicleImp {


    public Vanguard(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price, attack, defense, hitPoints);
    }


    @Override
    protected void setWeight(double weight) {
        super.setWeight(weight*2);
    }

    @Override
    protected void setAttack(int attack) {
        super.setAttack((int)(attack*0.75));
    }

    @Override
    protected void setDefense(int defense) {
        super.setDefense((int)(defense*1.5));
    }

    @Override
    protected void setHitPoints(int hitPoints) {
        super.setHitPoints((int)(hitPoints*1.75));
    }
}
