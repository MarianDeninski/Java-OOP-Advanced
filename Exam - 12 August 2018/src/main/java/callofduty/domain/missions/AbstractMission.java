package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public abstract class AbstractMission implements Mission {


    private String id;
    private double rating;
    private double bounty;
    private String status;


    protected AbstractMission(String id, double rating, double bounty) {
        this.id = id;
        setRating(rating);
        setBounty(bounty);
        this.status = "Open";


    }

    protected String getStatus() {
        return status;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    protected void setRating(Double rating) {
        this.rating = rating;
    }

    protected void setBounty(Double bounty) {
        this.bounty = bounty;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        String type = this.getClass().getSimpleName();

        switch (type){
            case "EscortMission":
                type = "Escort";
                break;
            case "HuntMission":
                type = "Hunt";
                break;
            case "SurveillanceMission":
                type = "Surveillance";
                break;
        }
        sb.append(type+" Mission - "+this.getId());
        sb.append(System.lineSeparator());
        sb.append("Status: "+ this.status);
        sb.append(System.lineSeparator());
        sb.append(String.format("Rating: %.2f",this.getRating()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Bounty: %.2f", this.getBounty()));

        return sb.toString().trim();
    }
}
