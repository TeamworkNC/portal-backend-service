package com.moviesandchill.portalbackendservice.mapper;

import com.moviesandchill.portalbackendservice.dto.user.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {
    FullUserDto mapToFullDto(UserDto dto);
}
