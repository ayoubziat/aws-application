package com.springboot.awsapp.mappers.impl;

import com.springboot.awsapp.domain.dto.UserDto;
import com.springboot.awsapp.domain.entities.UserEntity;
import com.springboot.awsapp.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper implements Mapper<UserEntity, UserDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserEntity dtoToBo(UserDto userDto) {
        return modelMapper.map(
                userDto, UserEntity.class
        );
    }

    @Override
    public UserDto boToDto(UserEntity userEntity) {
        return modelMapper.map(
                userEntity, UserDto.class
        );
    }
}
