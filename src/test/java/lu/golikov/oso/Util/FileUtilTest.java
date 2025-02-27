package lu.golikov.oso.Util;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class FileUtilTest {
    @Test
    public void getFileExtensionReturnsEmptyString() {
        assertEquals("", FileUtil.getFileExtension("/foo/bar"));
    }

    @Test
    public void getFileExtensionReturnsEmptyStringForTrailingDot() {
        assertEquals("", FileUtil.getFileExtension("/foo/bar."));
    }
}