package lu.golikov.oso.Domain.Service;

import lu.golikov.oso.Application.DataObject.ResultList;
import lu.golikov.oso.Domain.Model.EmployeeRepository;

public interface Analyzer {
    ResultList analyze(EmployeeRepository employeeRepository);
}
