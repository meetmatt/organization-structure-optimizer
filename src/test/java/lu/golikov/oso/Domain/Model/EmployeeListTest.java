package lu.golikov.oso.Domain.Model;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class EmployeeListTest {
    @Test
    public void testGet() {
        EmployeeList list = new EmployeeList();
        Employee employee = new Employee(1, "Bill", "Gates", 1, null);
        list.add(employee);
        assertSame(employee, list.getFirst());
    }
    @Test
    public void testUniqueById() {
        EmployeeList list = new EmployeeList();
        Employee employee1 = new Employee(1, "Bill", "Gates", 1, null);
        Employee employee2 = new Employee(1, "Steve", "Jobs", 1, null);
        assertTrue(list.add(employee1));
        assertFalse(list.add(employee2));
    }
}
