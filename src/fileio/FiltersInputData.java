package fileio;

import java.util.ArrayList;

public final class FiltersInputData {
    private SortInputData sort;
    private ContainsInputData contains;

    public FiltersInputData() {

    }

    public SortInputData getSort() {
        return sort;
    }

    public void setSort(final SortInputData sort) {
        this.sort = sort;
    }

    public ContainsInputData getContains() {
        return contains;
    }

    public void setContains(final ContainsInputData contains) {
        this.contains = contains;
    }
}
