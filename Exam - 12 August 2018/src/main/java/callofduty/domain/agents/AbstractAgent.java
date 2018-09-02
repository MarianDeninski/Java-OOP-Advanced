package callofduty.domain.agents;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;


import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAgent implements Agent {

    private String id;
    private String name;
    private double rating;

    private List<Mission> list;
    private List<Mission>completeList;

    protected AbstractAgent(String id, String name) {

        this.id = id;
        this.name = name;

        this.list = new LinkedList<>();
        this.completeList = new LinkedList<>();
    }

    protected List<Mission> getList() {
        return list;
    }

    protected List<Mission> getCompleteList() {
        return completeList;
    }

    protected void setRating(Double rating) {
        this.rating += rating;
    }

    @Override
    public void acceptMission(Mission mission) {

        this.list.add(mission);
    }

    @Override
    public abstract void completeMissions();



    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public Double getRating() {
        return this.rating;
    }




}
