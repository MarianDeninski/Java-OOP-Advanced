package callofduty.engine;

import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {

    private InputReader inputReader;
    private OutputWriter outputWriter;
    private MissionManager manager;

    public Engine(InputReader inputReader, OutputWriter outputWriter, MissionManager manager){

        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.manager = manager;

    }

    @Override
    public void run() {

        while (true) {

            List<String> tokens = null;
            try {
                tokens = new ArrayList<>(Arrays.asList(this.inputReader.readLine().split("\\s+")));
            } catch (IOException e) {
                e.printStackTrace();
            }


            switch (tokens.get(0)) {

                case "Agent":
                    this.outputWriter.println(this.manager.agent(tokens).trim());
                    break;
                case "Request":
                    this.outputWriter.println(this.manager.request(tokens).trim());
                    break;
                case "Complete":
                    outputWriter.println(this.manager.complete(tokens).trim());
                    break;
                case "Status":
                    outputWriter.println(this.manager.status(tokens).trim());
                    break;
                case "Over":
                    outputWriter.println(this.manager.over(tokens).trim());
                    return;
            }

        }





    }
}
