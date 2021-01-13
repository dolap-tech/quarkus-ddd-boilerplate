package com.dolap.quarkus.ddd.application.base.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<T, R> {

    R convert(T content);

    default List<R> convert(Collection<T> contents) {
        return contents.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}