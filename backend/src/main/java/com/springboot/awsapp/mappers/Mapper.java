package com.springboot.awsapp.mappers;

public interface Mapper<A, B> {

    A dtoToBo(B b);

    B boToDto(A a);
}
