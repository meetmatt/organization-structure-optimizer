package lu.golikov.oso.Application.Service;

import lu.golikov.oso.Application.DataObject.ResultList;
import lu.golikov.oso.Domain.DataMapper.DataSourceURI;
import lu.golikov.oso.Domain.Service.ReportingLineLengthAnalyzer;
import lu.golikov.oso.Domain.Service.SalaryBalanceAnalyzer;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static junit.framework.TestCase.*;

public class OrganizationAnalyzerTest {
    @Test
    public void testAnalyzeWithoutAnalyzersReturnsEmptyResult() throws Exception {
        String fileName = "test.csv";
        Path filePath = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());

        OrganizationAnalyzer analyzer = new OrganizationAnalyzer();
        analyzer.addAnalyzer(new SalaryBalanceAnalyzer(1.20, 1.50));
        analyzer.addAnalyzer(new ReportingLineLengthAnalyzer(4));

        ResultList results = analyzer.analyze(new DataSourceURI("file://" + filePath));

        assertEquals(
            "Joe Doe earns 13496.25 more than maximum expected (current: 90000, should be: 76503.75)",
            results.get(0));

        assertEquals(
            "Martin Chekov earns 4995.00 less than minimum expected (current: 55005, should be: 60000.00)",
            results.get(1));

        assertEquals(
            "Lucy Lee has 5 managers in reporting line (exceeds maximum by 1)",
            results.get(2));
    }
}
