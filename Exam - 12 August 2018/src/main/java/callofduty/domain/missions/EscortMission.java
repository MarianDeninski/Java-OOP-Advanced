package callofduty.domain.missions;

public class EscortMission extends AbstractMission {

    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }


    @Override
    protected void setRating(Double rating) {
        super.setRating(rating*0.75);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty(bounty*1.25);
    }
}
