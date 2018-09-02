package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;


public class MasterAgent extends AbstractAgent implements BountyAgent {


    private Double bounty;

    public MasterAgent(String id, String name) {
        super(id, name);
        this.bounty = 0D;
    }

    @Override
    public void completeMissions() {
        for (Mission mission : super.getList()) {

            try {
                Class clazz = Class.forName("callofduty.domain.missions.AbstractMission");

                Field field = clazz.getDeclaredField("status");

                field.setAccessible(true);

                field.set(mission,"Completed");

                super.setRating(mission.getRating());
                this.bounty+=mission.getBounty();


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            super.getCompleteList().add(mission);

        }
        super.getList().clear();
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Master Agent - " + this.getName());
        sb.append(System.lineSeparator());
        sb.append("Personal Code: " + this.getId());
        sb.append(System.lineSeparator());
        sb.append("Assigned Missions: " + this.getList().size());
        sb.append(System.lineSeparator());
        sb.append("Completed Missions: " + this.getCompleteList().size());
        sb.append(System.lineSeparator());
        sb.append(String.format("Rating: %.2f",this.getRating()));

        sb.append(System.lineSeparator());
        sb.append(String.format("Bounty Earned: $%.2f", this.getBounty()));


        return sb.toString().trim();
    }
}
