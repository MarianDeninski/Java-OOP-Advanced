package callofduty.domain.agents;

import callofduty.interfaces.Mission;

import java.lang.reflect.Field;

public class NoviceAgent extends AbstractAgent {


    public NoviceAgent(String id, String name) {
        super(id, name);
        setRating(0.0);

    }

    @Override
    protected void setRating(Double rating) {
        super.setRating(rating);
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
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Novice Agent - " + this.getName());
        sb.append(System.lineSeparator());
        sb.append("Personal Code: " + this.getId());
        sb.append(System.lineSeparator());
        sb.append("Assigned Missions: " + this.getList().size());
        sb.append(System.lineSeparator());
        sb.append("Completed Missions: " + this.getCompleteList().size());
        sb.append(System.lineSeparator());
        sb.append(String.format("Rating: %.2f",this.getRating()));



        return sb.toString().trim();
    }

}
