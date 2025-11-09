package com.bytestore.bytestore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnvioCodigoDto {
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Formato de correo inválido")
    @Size(max = 50, message = "El correo no debe exeder los 50 caracteres")
    private String correoCuenta;

}
