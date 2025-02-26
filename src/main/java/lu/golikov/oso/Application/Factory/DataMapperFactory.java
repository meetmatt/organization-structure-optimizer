package lu.golikov.oso.Application.Factory;

import lu.golikov.oso.Domain.DataMapper.DataMapper;
import lu.golikov.oso.Domain.DataMapper.DataSourceURI;
import lu.golikov.oso.Infrastructure.DataMapper.CSVDataMapper;
import lu.golikov.oso.Util.FileUtil;

public class DataMapperFactory {
    public DataMapper create(DataSourceURI dataSourceURI) throws Exception {
        String scheme = dataSourceURI.scheme();
        switch (scheme) {
            case "file":
                return this.createFileDataMapper(dataSourceURI.path());
            default:
                throw new Exception("Unsupported data source scheme %s".formatted(scheme));
        }
    }

    private DataMapper createFileDataMapper(String path) throws Exception {
        String extension = FileUtil.getFileExtension(path);
        switch (extension) {
            case "csv":
                return new CSVDataMapper(path);
            default:
                throw new Exception("Unsupported file type %s".formatted(extension));
        }
    }
}
