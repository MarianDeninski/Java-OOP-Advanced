package callofduty.domain.missions;

public class HuntMission extends AbstractMission {
    public HuntMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }



    @Override
    protected void setRating(Double rating) {
        super.setRating(rating*1.5);
    }

    @Override
    protected void setBounty(Double bounty) {
        super.setBounty(bounty*2);
    }
}
