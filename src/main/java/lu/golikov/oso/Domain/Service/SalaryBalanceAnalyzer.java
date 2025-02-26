package lu.golikov.oso.Domain.Service;

import lu.golikov.oso.Application.DataObject.ResultList;
import lu.golikov.oso.Domain.Model.Employee;
import lu.golikov.oso.Domain.Model.EmployeeRepository;

import java.util.List;

public class SalaryBalanceAnalyzer implements Analyzer {
    public static final String RESULT_LESS_THAN_MINIMUM = "%s earns %.2f less than minimum expected (current: %d, should be: %.2f)";
    public static final String RESULT_MORE_THAN_MAXIMUM = "%s earns %.2f more than maximum expected (current: %d, should be: %.2f)";

    private final double minSalaryRatio;
    private final double maxSalaryRatio;

    public SalaryBalanceAnalyzer(double minSalaryRatio, double maxSalaryRatio) {
        this.minSalaryRatio = minSalaryRatio;
        this.maxSalaryRatio = maxSalaryRatio;
    }

    @Override
    public ResultList analyze(EmployeeRepository employeeRepository) {
        ResultList results = new ResultList();

        for (Employee manager : employeeRepository) {
            List<Employee> subordinates = employeeRepository.findByManagerId(manager.id());
            if (subordinates == null || subordinates.isEmpty()) {
                // not a manager
                continue;
            }

            double avgSubordinateSalary = subordinates.stream().mapToInt(Employee::salary).average().orElse(0.0);

            double minExpectedSalary = avgSubordinateSalary * minSalaryRatio;
            double maxExpectedSalary = avgSubordinateSalary * maxSalaryRatio;

            if (manager.salary() < minExpectedSalary) {
                String result = RESULT_LESS_THAN_MINIMUM.formatted(manager, minExpectedSalary - manager.salary(), manager.salary(), minExpectedSalary);
                results.add(result);
            } else if (manager.salary() > maxExpectedSalary) {
                String result = RESULT_MORE_THAN_MAXIMUM.formatted(manager, manager.salary() - maxExpectedSalary, manager.salary(), maxExpectedSalary);
                results.add(result);
            }
        }

        return results;
    }
}
