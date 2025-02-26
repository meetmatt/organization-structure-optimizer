package lu.golikov.oso.Domain.Service;

import lu.golikov.oso.Application.DataObject.ResultList;
import lu.golikov.oso.Domain.Model.Employee;
import lu.golikov.oso.Domain.Model.EmployeeRepository;

public class ReportingLineLengthAnalyzer implements Analyzer {
    public static final String RESULT_MESSAGE_FORMAT = "%s has %d managers in reporting line (exceeds maximum by %d)";

    private final int maxReportingLineLength;

    public ReportingLineLengthAnalyzer(int maxReportingLineLength) {
        this.maxReportingLineLength = maxReportingLineLength;
    }

    @Override
    public ResultList analyze(EmployeeRepository employeeRepository) {
        ResultList results = new ResultList();

        for (Employee employee : employeeRepository) {
            int lineLength = calculateReportingLineLength(employee, employeeRepository);
            if (lineLength > maxReportingLineLength) {
                String result = RESULT_MESSAGE_FORMAT.formatted(employee, lineLength, lineLength - maxReportingLineLength);
                results.add(result);
            }
        }

        return results;
    }

    private int calculateReportingLineLength(Employee employee, EmployeeRepository employees) {
        int length = 0;

        Integer currentManagerId = employee.managerId();
        while (currentManagerId != null) {
            length++;
            Employee manager = employees.findById(currentManagerId);
            if (manager == null) {
                break;
            }
            currentManagerId = manager.managerId();
        }

        return length;
    }
}
