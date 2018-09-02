package panzer.manager;

import panzer.contracts.BattleOperator;
import panzer.contracts.Manager;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.core.PanzerBattleOperator;
import panzer.models.parts.ArsenalPart;
import panzer.models.parts.EndurancePart;
import panzer.models.parts.ShellPart;
import panzer.models.vehicles.Revenger;
import panzer.models.vehicles.Vanguard;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerImpl implements Manager {

    private Map<String, Vehicle>vehicles;
    private BattleOperator battleOperator;
    private Map<String, List<Part>> parts;
    private List<Vehicle> defeatedViacles;


    public ManagerImpl(){

        this.battleOperator = new PanzerBattleOperator();
        this.vehicles = new LinkedHashMap<>();
        this.parts = new LinkedHashMap<>();
        this.defeatedViacles = new LinkedList<>();

    }


    @Override
    public String addVehicle(List<String> arguments) {
        //Vehicle {vehicleType} {model} {weight} {price} {attack} {defense} {hitPoints}
        Vehicle vehicle = null;
        switch (arguments.get(1)){

            case "Revenger":

                vehicle = new Revenger(arguments.get(2),Double.parseDouble(arguments.get(3)),BigDecimal.valueOf(Double.parseDouble(arguments.get(4))),
                        Integer.parseInt(arguments.get(5)),Integer.parseInt(arguments.get(6)),Integer.parseInt(arguments.get(7)));

                this.vehicles.put(arguments.get(2),vehicle);

                break;
            case "Vanguard":

                vehicle = new Vanguard(arguments.get(2),Double.parseDouble(arguments.get(3)),BigDecimal.valueOf(Double.parseDouble(arguments.get(4))),
                        Integer.parseInt(arguments.get(5)),Integer.parseInt(arguments.get(6)),Integer.parseInt(arguments.get(7)));

                this.vehicles.put(arguments.get(2),vehicle);
                break;
        }

        //Created {type} Vehicle – {model}

        return "Created "+arguments.get(1)+" Vehicle - "+arguments.get(2);
    }

    @Override
    public String addPart(List<String> arguments) {

        //Part {vehicleModel} {partType} {model} {weight} {price} {additionalParameter}

        Part part = null;
        switch (arguments.get(2)){

            case "Arsenal":
                part = new ArsenalPart(arguments.get(3),Double.parseDouble(arguments.get(4))
                        ,BigDecimal.valueOf(Double.parseDouble(arguments.get(5))),
                        Integer.parseInt(arguments.get(6)));
                if(parts.containsKey(arguments.get(1))){
                    this.parts.get(arguments.get(1)).add(part);
                }else{
                    this.parts.put(arguments.get(1),new LinkedList<>());
                    this.parts.get(arguments.get(1)).add(part);
                }


                this.vehicles.get(arguments.get(1)).addArsenalPart(part);
                break;
            case "Endurance":
                part = new EndurancePart(arguments.get(3),Double.parseDouble(arguments.get(4))
                        ,BigDecimal.valueOf(Double.parseDouble(arguments.get(5))),
                        Integer.parseInt(arguments.get(6)));


                if(parts.containsKey(arguments.get(1))){
                    this.parts.get(arguments.get(1)).add(part);
                }else{
                    this.parts.put(arguments.get(1),new LinkedList<>());
                    this.parts.get(arguments.get(1)).add(part);
                }
                this.vehicles.get(arguments.get(1)).addEndurancePart(part);

                break;
            case "Shell":

                part = new ShellPart(arguments.get(3),Double.parseDouble(arguments.get(4))
                        ,BigDecimal.valueOf(Double.parseDouble(arguments.get(5))),
                        Integer.parseInt(arguments.get(6)));


                if(parts.containsKey(arguments.get(1))){
                    this.parts.get(arguments.get(1)).add(part);
                }else{
                    this.parts.put(arguments.get(1),new LinkedList<>());
                    this.parts.get(arguments.get(1)).add(part);
                }
                this.vehicles.get(arguments.get(1)).addShellPart(part);

                break;
        }


        //Added {partType} - {partModel} to Vehicle - {vehicleModel}
        return "Added "+arguments.get(2)+" - "+arguments.get(3)+" to Vehicle - "+arguments.get(1);
    }

    @Override
    public String inspect(List<String> arguments) {
//Inspect {model}
        if(this.vehicles.containsKey(arguments.get(1))){

            return this.vehicles.get(arguments.get(1)).toString().trim();
        }else{
            return this.parts.get(arguments.get(1)).toString().trim();
        }

    }

    @Override
    public String battle(List<String> arguments) {


        Vehicle attacker = this.vehicles.get(arguments.get(1));
        Vehicle defender = this.vehicles.get(arguments.get(2));

       String winner =  this.battleOperator.battle(attacker,defender);
       if(attacker.getModel().equalsIgnoreCase(winner)){
           this.vehicles.remove(defender.getModel());
           this.defeatedViacles.add(defender);
        this.parts = this.parts.entrySet().stream().filter(a->!a.getKey().equals(defender.getModel())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e2, LinkedHashMap::new));
           return attacker.getModel() +" versus "+defender.getModel()+" -> "+attacker.getModel()+" Wins! Flawless Victory!";
       }else{

           this.defeatedViacles.add(attacker);
           this.vehicles.remove(attacker.getModel());
           this.parts =  this.parts.entrySet().stream().filter(a->!a.getKey().equals(attacker.getModel())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                   (e1, e2) -> e2, LinkedHashMap::new));
           return attacker.getModel() +" versus "+defender.getModel()+" -> "+defender.getModel()+" Wins! Flawless Victory!";
       }


    }

    @Override
    public String terminate(List<String> arguments) {


        //Remaining Vehicles: {vehicle1Model}, {vehicle2Model}...
        //Defeated Vehicles: {defeatedVehicle1Model}, {defeatedVehicle2Model}...
        //Currently Used Parts: {countOfCurrentlyUsedParts}
        StringBuilder sb = new StringBuilder();

        if(this.vehicles.size()==0){
            sb.append("Remaining Vehicles: None");
        }else {
            sb.append("Remaining Vehicles: " + this.vehicles.values().stream().map(Vehicle::getModel)
                    .collect(Collectors.joining(", ")));
        }
        sb.append(System.lineSeparator());

        if(this.defeatedViacles.size() ==0){
            sb.append("Defeated Vehicles: None");
        }else{
            sb.append("Defeated Vehicles: "+this.defeatedViacles.stream().map(Vehicle::getModel)
                    .collect(Collectors.joining(", ")));
        }
        sb.append(System.lineSeparator());

        int sum = 0;

        for (Map.Entry<String,List<Part>> stringListEntry : this.parts.entrySet()) {

            sum+=stringListEntry.getValue().size();

        }

        sb.append("Currently Used Parts: "+sum);




        return sb.toString().trim();
    }
}
