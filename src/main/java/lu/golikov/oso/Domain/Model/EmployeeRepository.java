package lu.golikov.oso.Domain.Model;

import java.util.Iterator;

public interface EmployeeRepository extends Iterable<Employee> {
    Iterator<Employee> iterator();
    Employee findById(int id);
    EmployeeList findByManagerId(int managerId);
}
