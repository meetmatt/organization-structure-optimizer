package lu.golikov.oso.Domain.DataMapper;

import lu.golikov.oso.Domain.Model.Employee;

import java.io.FileNotFoundException;

public interface DataMapper {
    Employee read() throws FileNotFoundException;
}
