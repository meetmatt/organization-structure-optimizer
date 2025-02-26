package lu.golikov.oso.Domain.Model;

public record Employee(int id, String firstName, String lastName, Integer salary, Integer managerId) {
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
