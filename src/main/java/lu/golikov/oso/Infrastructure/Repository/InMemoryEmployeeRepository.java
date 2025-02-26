package lu.golikov.oso.Infrastructure.Repository;

import lu.golikov.oso.Domain.DataMapper.DataMapper;
import lu.golikov.oso.Domain.Model.Employee;
import lu.golikov.oso.Domain.Model.EmployeeList;
import lu.golikov.oso.Domain.Model.EmployeeRepository;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final HashMap<Integer, Employee> employeesById;
    private final HashMap<Integer, EmployeeList> employeesByManagerId;

    public InMemoryEmployeeRepository(DataMapper dataMapper) {
        this.employeesById = new HashMap<>();
        this.employeesByManagerId = new HashMap<>();

        this.loadEmployeesFromDataSource(dataMapper);
        this.mapEmployeesByManagerId();
    }

    @Override
    public Iterator<Employee> iterator() {
        return this.employeesById.values().iterator();
    }

    @Override
    public Employee findById(int id) {
        return this.employeesById.get(id);
    }

    @Override
    public EmployeeList findByManagerId(int managerId) {
        return this.employeesByManagerId.get(managerId);
    }

    private void loadEmployeesFromDataSource(DataMapper dataMapper) {
        while (true) {
            try {
                Employee employee = dataMapper.read();
                if (employee == null) {
                    break;
                }
                this.employeesById.put(employee.id(), employee);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Failed to read record from data source", e);
            }
        }
    }

    private void mapEmployeesByManagerId() {
        for (Employee employee : this.employeesById.values()) {
            int managerId = employee.id();

            List<Employee> subordinates = this.employeesById.values().stream()
                .filter(emp -> emp.managerId() != null && emp.managerId() == managerId)
                .toList();

            this.employeesByManagerId
                .computeIfAbsent(managerId, k -> new EmployeeList())
                .addAll(subordinates);
        }
    }
}
