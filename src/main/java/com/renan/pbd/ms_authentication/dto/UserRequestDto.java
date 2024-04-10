package com.renan.pbd.ms_authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(@NotBlank String username, @NotBlank String password) {
}
