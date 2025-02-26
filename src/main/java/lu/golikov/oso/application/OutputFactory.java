package lu.golikov.oso.application;

public class OutputFactory {
    public Output build(String result) {
        return new Output(result);
    }
}
