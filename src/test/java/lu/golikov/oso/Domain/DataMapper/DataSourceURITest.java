package lu.golikov.oso.Domain.DataMapper;

import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.junit.Assert.*;

public class DataSourceURITest {
    @Test
    public void testReturnsValidSchemeAndPath() {
        DataSourceURI dataSourceUri = new DataSourceURI("foo:///bar/buz");

        assertEquals("foo", dataSourceUri.scheme());
        assertEquals("/bar/buz", dataSourceUri.path());
        assertEquals("foo:///bar/buz", dataSourceUri.toString());
    }

    @Test
    public void testThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new DataSourceURI("Hello World"));
    }
}
