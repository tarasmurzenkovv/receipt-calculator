package com.price.calculator.service;

import java.util.Collection;

public interface CollectionUtils {
    static  <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
}
