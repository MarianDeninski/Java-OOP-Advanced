package callofduty;

import callofduty.engine.Engine;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.ConsoleReader;
import callofduty.io.ConsoleWriter;
import callofduty.manager.ManagerImp;

public class Main {
    public static void main(String[] args)  {


        InputReader inputReader = new ConsoleReader();
        OutputWriter outputWriter = new ConsoleWriter();
        MissionManager manager = new ManagerImp();
        Runnable runnable = new Engine(inputReader,outputWriter,manager);
        runnable.run();




    }
}




