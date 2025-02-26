package lu.golikov.oso.application;

public class Output {
    private final String result;

    public Output(String result) {
        this.result = result;
    }
    public void print() {
        System.out.println(this.result);
    }
}
