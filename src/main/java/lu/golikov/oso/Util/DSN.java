package lu.golikov.oso.Util;

import lu.golikov.oso.Domain.DataMapper.DataSourceURI;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DSN {
    public static DataSourceURI file(String path) {
        Path absolutePath = Paths.get(path).toAbsolutePath();

        return new DataSourceURI("file://" + absolutePath);
    }
}
