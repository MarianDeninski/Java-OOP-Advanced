package callofduty.domain.missions;

public class SurveillanceMission extends AbstractMission {



    public SurveillanceMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    @Override
    protected void setRating(Double rating) {
        super.setRating(rating*0.25);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty(bounty*1.5);
    }
}
