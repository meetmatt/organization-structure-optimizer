package lu.golikov.oso.Application.DataObject;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultList extends AbstractList<String> {
    private final List<String> results = new ArrayList<>();

    @Override
    public String get(int index) {
        return results.get(index);
    }

    @Override
    public int size() {
        return results.size();
    }

    @Override
    public boolean add(String result) {
        return results.add(result);
    }

    @Override
    public Iterator<String> iterator() {
        return results.iterator();
    }

    public void merge(ResultList list) {
        this.results.addAll(list);
    }
}
