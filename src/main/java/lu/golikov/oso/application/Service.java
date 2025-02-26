package lu.golikov.oso.application;

public class Service {
    private final OutputFactory outputFactory;

    public Service(OutputFactory outputFactory) {
        this.outputFactory = outputFactory;
    }
    public Output analyze(Input input) {
        return this.outputFactory.build("Analyzed file %s".formatted(input.filePath()));
    }
}
