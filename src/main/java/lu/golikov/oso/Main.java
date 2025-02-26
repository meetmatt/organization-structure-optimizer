package lu.golikov.oso;

import lu.golikov.oso.application.Command;
import lu.golikov.oso.application.OutputFactory;
import lu.golikov.oso.application.Service;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to the CSV file as an argument");
            return;
        }

        Command command = new Command(new Service(new OutputFactory()));
        command.execute(args[0]);

        System.out.println("Hello World!");
    }
}
