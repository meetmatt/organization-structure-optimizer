package lu.golikov.oso.Domain.Model;

import java.util.*;

public class EmployeeList extends AbstractList<Employee> {
    private final List<Employee> employees = new ArrayList<>();
    private final Set<Integer> employeeIds = new HashSet<>(); // Track unique names

    @Override
    public Employee get(int index) {
        return employees.get(index);
    }

    @Override
    public int size() {
        return employees.size();
    }

    @Override
    public boolean add(Employee employee) {
        if (employeeIds.add(employee.id())) {
            return employees.add(employee);
        }
        return false;
    }

    @Override
    public Iterator<Employee> iterator() {
        return employees.iterator();
    }
}
