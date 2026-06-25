package com.ipn.mx.miniinventario.features.mail.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailRequestDTO {
    @NotBlank(message = "El Correo Es Obligatorio")
    @Email(message = "El formato del correo es Invalido")
    private String correo;
}