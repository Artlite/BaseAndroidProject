package com.artlite.bslibrary.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlernatovich on 3/22/2017.
 */

public abstract class BSArrayFilter<T, K> {
    /**
     * Method which provide the applying the filter for the
     * current list
     *
     * @param list current list
     * @param k    filter value
     * @return filtered list
     */
    public List<T> applyFilter(List<T> list, K k) {
        List<T> sortedList = new ArrayList<>();
        for (T t : list) {
            if (compare(t, k)) {
                sortedList.add(t);
            }
        }
        return sortedList;
    }

    /**
     * Method which provide the comparing
     * of the element
     *
     * @param t comparing object
     * @param k filter value
     * @return
     */
    public abstract boolean compare(T t, K k);
}
