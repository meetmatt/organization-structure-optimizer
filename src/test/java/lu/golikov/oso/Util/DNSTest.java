package lu.golikov.oso.Util;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class DNSTest {
    @Test
    public void testFile() {
        assertEquals("file:///tmp/test.csv", DSN.file("/tmp/test.csv").toString());
    }
}
