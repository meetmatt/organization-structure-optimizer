package lu.golikov.oso.application;

public class Command {
    private final Service service;

    public Command(Service service) {
        this.service = service;
    }

    public void execute(String filePath) {
        Input input = new Input(filePath);
        Output output = this.service.analyze(input);
        output.print();
    }
}
