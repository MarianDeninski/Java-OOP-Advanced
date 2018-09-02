package callofduty.manager;

import callofduty.core.MissionControlImpl;
import callofduty.domain.agents.MasterAgent;
import callofduty.domain.agents.NoviceAgent;
import callofduty.interfaces.*;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ManagerImp implements MissionManager {

    private Map<String,Agent> agents;
    private Map<String,BountyAgent> bountyAgents;
    private Map<String,Mission> missions;
    private int completed;
    private MissionControl missionControl;
    private Map<String, Integer> ips;

    public ManagerImp(){
        this.agents = new LinkedHashMap<>();
        this.missions = new LinkedHashMap<>();
        this.missionControl = new MissionControlImpl();
        this.bountyAgents = new LinkedHashMap<>();
        this.ips = new LinkedHashMap<>();

    }
    @Override
    public String agent(List<String> arguments) {

        Agent agent = new NoviceAgent(arguments.get(1),arguments.get(2));
        this.agents.put(arguments.get(1),agent);

        return "Registered Agent - "+agent.getName()+":"+agent.getId();
    }

    @Override
    public String request(List<String> arguments) {

        //Parameters – agentId (string), missionId (string), missionRating (double), missionBounty (double).
        //Request 007b 1x1 25 5000
        Mission mission = this.missionControl.generateMission(arguments.get(2),Double.parseDouble(arguments.get(3)),
                Double.parseDouble(arguments.get(4)));

        boolean yes = false;
        if(this.agents.containsKey(arguments.get(1))) {
            this.agents.get(arguments.get(1)).acceptMission(mission);
            this.missions.put(arguments.get(2), mission);
            yes = true;
        }else{
            this.bountyAgents.get(arguments.get(1)).acceptMission(mission);
            this.missions.put(arguments.get(2), mission);
        }

        String name = "";
        if(mission.getClass().getSimpleName().equalsIgnoreCase("EscortMission")){

            name = "Escort Mission";
        }
        else if(mission.getClass().getSimpleName().equalsIgnoreCase("HuntMission")){

            name = "Hunt Mission";
        }else{
            name = "Surveillance Mission";
        }

        if(yes) {
            return "Assigned " + name + " - " + mission.getId() + " to Agent - " + this.agents.get(arguments.get(1)).getName();
        }else{
            return "Assigned " + name + " - " + mission.getId() + " to Agent - " + this.bountyAgents.get(arguments.get(1)).getName();
        }
    }

    @Override
    public String complete(List<String> arguments) {
        Agent agent = null;
        boolean yes = false;
        if(this.agents.containsKey(arguments.get(1))) {
            agent = this.agents.get(arguments.get(1));
            agent.completeMissions();
            yes = true;
        } else{
                agent = this.bountyAgents.get(arguments.get(1));
                agent.completeMissions();

            }

            try {
                Class clazz = Class.forName("callofduty.domain.agents.AbstractAgent");
                Field field = clazz.getDeclaredField("completeList");
                field.setAccessible(true);

                List<Mission> missions  = (List<Mission>) field.get(agent);



                if(!this.ips.containsKey(agent.getId())) {
                    this.ips.put(agent.getId(),missions.size());
                }else{
                    this.ips.put(agent.getId(),missions.size());
                }

                if(yes){
                if(missions.size()>=3) {

                    BountyAgent agent1 = new MasterAgent(agent.getId(), agent.getName());

                    Field field1 = clazz.getDeclaredField("rating");
                    field1.setAccessible(true);
                    Double rating = (Double) field1.get(agent);
                    field1.set(agent1, rating);
                    field.set(agent1, missions);

                    this.agents.remove(agent.getId());
                    this.bountyAgents.put(agent1.getId(), agent1);
                }

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }




        return "Agent - "+agent.getName()+":"+agent.getId()+" has completed all assigned missions.";
    }

    @Override
    public String status(List<String> arguments) {

        if(this.agents.containsKey(arguments.get(1))){
            return this.agents.get(arguments.get(1)).toString();
        }
        else if(this.bountyAgents.containsKey(arguments.get(1))){

            return this.bountyAgents.get(arguments.get(1)).toString();
        }
        else{
          return  this.missions.get(arguments.get(1)).toString();
        }
    }

    @Override
    public String over(List<String> arguments) {

        //Novice Agents: {noviceAgentsCount}
        //Master Agents: {masterAgentsCount}


        StringBuilder sb = new StringBuilder();

        sb.append("Novice Agents: "+this.agents.size());
        sb.append(System.lineSeparator());
        sb.append("Master Agents: "+this.bountyAgents.size());
        sb.append(System.lineSeparator());

        double rating = 0D;
        double bounty = 0D;

        for (Agent agent : this.agents.values()) {

            rating+=agent.getRating();
        }

        for (BountyAgent bountyAgent : this.bountyAgents.values()) {

            bounty+=bountyAgent.getBounty();
            rating+=bountyAgent.getRating();
        }

        //Assigned Missions: {totalAssignedMissionsCount}
        //Completed Missions: {totalCompletedMissionsCount}
        //Total Rating Given: {totalRatingEarned}
        //Total Bounty Given: ${totalBountyEarned}

        sb.append("Assigned Missions: "+this.missions.size());
        sb.append(System.lineSeparator());
        sb.append("Completed Missions: "+this.ips.entrySet().stream().mapToInt(a ->a.getValue()).sum());
        sb.append(System.lineSeparator());
        sb.append(String.format("Total Rating Given: %.2f",rating));
        sb.append(System.lineSeparator());
        sb.append(String.format("Total Bounty Given: $%.2f",bounty));

        return sb.toString().trim();
    }
}
