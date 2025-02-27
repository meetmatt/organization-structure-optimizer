package lu.golikov.oso.Application.Service;

import lu.golikov.oso.Application.DataObject.ResultList;
import lu.golikov.oso.Application.Factory.EmployeeRepositoryFactory;
import lu.golikov.oso.Domain.DataMapper.DataSourceURI;
import lu.golikov.oso.Domain.Model.EmployeeRepository;
import lu.golikov.oso.Domain.Service.Analyzer;

import java.util.ArrayList;
import java.util.List;

public class OrganizationAnalyzer {
    private final EmployeeRepositoryFactory employeeRepositoryFactory;
    private final List<Analyzer> analyzers = new ArrayList<>();

    public OrganizationAnalyzer() {
        this.employeeRepositoryFactory = new EmployeeRepositoryFactory();
    }

    public void addAnalyzer(Analyzer analyzer) {
        this.analyzers.add(analyzer);
    }

    public ResultList analyze(DataSourceURI dataSourceURI) throws Exception {
        EmployeeRepository employeeRepository = this.employeeRepositoryFactory.create(dataSourceURI);

        ResultList results = new ResultList();
        for (Analyzer analyzer : this.analyzers) {
            results.merge(analyzer.analyze(employeeRepository));
        }

        return results;
    }
}
