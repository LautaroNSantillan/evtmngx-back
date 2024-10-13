package com.ls.eventmanager.security.dtos;

import com.ls.eventmanager.dtos.DTOUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter@Getter@AllArgsConstructor
public class LoginResponse {
    DTOUser user;
    private String jwtToken;
}
