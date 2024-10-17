package com.vitamincode.vitamincode_be.convert;

import com.vitamincode.vitamincode_be.dto.response.RoleDtoResponse;
import com.vitamincode.vitamincode_be.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapStruct {
    List<RoleDtoResponse> toRoleDtoResponseList(List<Role> roles);
    RoleDtoResponse toRoleDtoResponse(Role role);
}
