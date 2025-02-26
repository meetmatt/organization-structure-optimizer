package lu.golikov.oso;

import lu.golikov.oso.Application.Service.OrganizationAnalyzer;
import lu.golikov.oso.Application.DataObject.ResultList;
import lu.golikov.oso.Domain.DataMapper.DataSourceURI;
import lu.golikov.oso.Domain.Service.ReportingLineLengthAnalyzer;
import lu.golikov.oso.Domain.Service.SalaryBalanceAnalyzer;
import lu.golikov.oso.Util.DSN;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to the CSV file as an argument.");
            System.exit(1);
            return;
        }

        OrganizationAnalyzer analyzer = configureAnalyzer();
        DataSourceURI dataSourceURI = DSN.file(args[0]);

        try {
            System.out.printf("Analyzed data source %s%n", dataSourceURI);

            ResultList results = analyzer.analyze(dataSourceURI);
            for (String result : results) {
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.printf("Something went wrong:%n%s%n", e);
            System.exit(1);
        }
    }

    private static OrganizationAnalyzer configureAnalyzer() {
        OrganizationAnalyzer analyzer = new OrganizationAnalyzer();

        analyzer.addAnalyzer(new SalaryBalanceAnalyzer(1.20, 1.50));
        analyzer.addAnalyzer(new ReportingLineLengthAnalyzer(4));

        return analyzer;
    }
}
