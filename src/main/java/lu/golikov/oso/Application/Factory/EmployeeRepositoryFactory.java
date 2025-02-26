package lu.golikov.oso.Application.Factory;

import lu.golikov.oso.Domain.DataMapper.DataMapper;
import lu.golikov.oso.Domain.DataMapper.DataSourceURI;
import lu.golikov.oso.Domain.Model.EmployeeRepository;
import lu.golikov.oso.Infrastructure.Repository.InMemoryEmployeeRepository;

public class EmployeeRepositoryFactory {
    private final DataMapperFactory dataMapperFactory;

    public EmployeeRepositoryFactory() {
        this.dataMapperFactory = new DataMapperFactory();
    }

    public EmployeeRepository create(DataSourceURI dataSourceURI) throws Exception {
        DataMapper dataMapper = this.dataMapperFactory.create(dataSourceURI);

        return new InMemoryEmployeeRepository(dataMapper);
    }
}
