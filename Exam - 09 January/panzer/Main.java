package panzer;

import panzer.contracts.InputReader;
import panzer.contracts.Manager;
import panzer.contracts.OutputWriter;

import panzer.engine.Engine;
import panzer.io.ConsoleInputReader;
import panzer.io.ConsoleOutputWriter;
import panzer.manager.ManagerImpl;

public class Main {
    public static void main(String[] args) {

        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();
        Manager manager = new ManagerImpl();
        Runnable runnable = new Engine(manager,inputReader,outputWriter);
        runnable.run();



        
    }
}
