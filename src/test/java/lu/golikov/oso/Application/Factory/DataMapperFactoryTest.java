package lu.golikov.oso.Application.Factory;

import lu.golikov.oso.Domain.DataMapper.DataSourceURI;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataMapperFactoryTest {
    @Test
    public void createThrowsExceptionOnUnsupportedScheme() {
        DataMapperFactory factory = new DataMapperFactory();
        DataSourceURI uri = new DataSourceURI("foo://bar");
        assertThrows(Exception.class, () -> factory.create(uri));
    }

    @Test
    public void createThrowsExceptionOnUnsupportedFileType() {
        DataMapperFactory factory = new DataMapperFactory();
        DataSourceURI uri = new DataSourceURI("file:///tmp/test.txt");
        assertThrows(Exception.class, () -> factory.create(uri));
    }
}
