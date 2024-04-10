package com.renan.pbd.ms_authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenRequestResponseDto(@NotBlank String token) {
}
