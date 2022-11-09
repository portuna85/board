package com.blitz.board.service.dto;

import com.blitz.board.domain.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
