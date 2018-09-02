package cresla;

import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.entities.containers.io.Reader;
import cresla.entities.containers.io.Writer;
import cresla.entities.containers.manager.ManagerImpl;


import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Manager manager = new ManagerImpl();
        InputReader inputReader = new Reader();
        OutputWriter outputWriter = new Writer();
        List<String> command = Arrays.asList(inputReader.readLine().split("\\s+"));
        while (true) {


            switch (command.get(0)) {

                case "Reactor":
                   command =  command.subList(1,command.size());
                  outputWriter.writeLine(manager.reactorCommand(command));
                    break;
                case "Module":
                    command =  command.subList(1,command.size());
                    outputWriter.writeLine(manager.moduleCommand(command));
                    break;
                case "Report":
                    command =  command.subList(1,command.size());
                    outputWriter.writeLine(manager.reportCommand(command));
                    break;
                case "Exit":

                    outputWriter.writeLine(manager.exitCommand(command));

                    return;
            }
            command = Arrays.asList(inputReader.readLine().split("\\s+"));

        }


    }
}
