package nl.mollie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import nl.mollie.util.ExtensibleValueObject;

public class ResultPage<T extends PageableEntity> extends ExtensibleValueObject {
    public static final String FIRST = "first";
    public static final String PREVIOUS = "previous";
    public static final String NEXT = "next";
    public static final String LAST = "last";

    private Integer totalCount;
    private Integer offset;
    private Integer count;
    private List<T> data;
    private Map<String, String> links;

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getCount() {
        return count;
    }

    public List<T> getData() {
        return data;
    }

    @Nonnull
    public Map<String, String> getLinks() {
        return links != null ? links : new HashMap<>();
    }

    public boolean hasLink(String name) {
        return links != null && links.containsKey(name);
    }

    @Nullable
    public String getLink(String name) {
        return links != null ? links.get(name) : null;
    }
}
