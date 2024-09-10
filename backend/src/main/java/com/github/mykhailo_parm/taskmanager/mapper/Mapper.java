package com.github.mykhailo_parm.taskmanager.mapper;

public interface Mapper<A, B> {
    B mapTo(A a);
    A mapFrom(B b);
}
