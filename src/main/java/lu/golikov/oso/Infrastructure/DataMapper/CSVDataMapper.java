package lu.golikov.oso.Infrastructure.DataMapper;

import lu.golikov.oso.Domain.DataMapper.DataMapper;
import lu.golikov.oso.Domain.Model.Employee;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class CSVDataMapper implements DataMapper {
    private final BufferedReader fileReader;
    private boolean wasHeaderSkipped = false;

    public CSVDataMapper(String filePath) throws FileNotFoundException {
        this.fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    }

    @Override
    public Employee read() {
        try {
            if (!this.wasHeaderSkipped) {
                this.fileReader.readLine();
                this.wasHeaderSkipped = true;
            }

            String line = this.fileReader.readLine();
            if (line == null) {
                return null;
            }

            return mapLineToEmployee(line);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map data source record to entity. Error: %s".formatted(e));
        }
    }

    private Employee mapLineToEmployee(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        Integer salary = Integer.parseInt(parts[3]);
        Integer managerId = parts.length < 5 ? null : Integer.parseInt(parts[4]);

        return new Employee(id, firstName, lastName, salary, managerId);
    }
}
