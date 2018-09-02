package panzer.engine;

import panzer.contracts.InputReader;
import panzer.contracts.Manager;
import panzer.contracts.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {

    private Manager manager;
    private InputReader inputReader;
    private OutputWriter outputWriter;
    public Engine(Manager manager, InputReader inputReader, OutputWriter outputWriter){

        this.manager = manager;
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;

    }
    @Override
    public void run() {

        while (true) {

            List<String> tokens = new ArrayList<>(Arrays.asList(this.inputReader.readLine().split("\\s+")));

            switch (tokens.get(0)) {

                case "Vehicle":
                    this.outputWriter.println(this.manager.addVehicle(tokens).trim());
                    break;
                case "Part":
                    this.outputWriter.println(this.manager.addPart(tokens).trim());
                    break;
                case "Inspect":
                    outputWriter.println(this.manager.inspect(tokens).trim());
                    break;
                case "Battle":
                    outputWriter.println(this.manager.battle(tokens).trim());
                    break;
                case "Terminate":
                    outputWriter.println(this.manager.terminate(tokens).trim());
                    return;

            }

        }


    }
}
