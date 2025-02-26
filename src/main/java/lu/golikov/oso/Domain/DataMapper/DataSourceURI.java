package lu.golikov.oso.Domain.DataMapper;

public class DataSourceURI {
    private final String uri;
    private final String scheme;
    private final String path;

    public DataSourceURI(String uri) {
        this.uri = uri;
        int index = uri.indexOf("://");
        if (index == -1) {
            throw new IllegalArgumentException("Invalid URI: " + uri);
        }

        this.scheme = uri.substring(0, index);
        this.path = uri.substring(index + 3);
    }

    public String scheme() {
        return this.scheme;
    }

    public String path() {
        return this.path;
    }

    @Override
    public String toString() {
        return this.uri;
    }
}
