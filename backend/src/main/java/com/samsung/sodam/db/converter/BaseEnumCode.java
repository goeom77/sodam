package com.samsung.sodam.db.converter;

public interface BaseEnumCode<T,K> {
    T getValue();

    K getName();
}