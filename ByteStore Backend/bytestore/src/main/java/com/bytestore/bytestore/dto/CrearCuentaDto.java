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
public class CrearCuentaDto {

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Formato de correo inválido")
    @Size(max = 50, message = "El correo no debe exeder los 50 caracteres")
    private String correoCuenta;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 25, message = "Debe tener entre 8 y 25 caracteres")
    private String contrasena;

    @NotBlank(message = "El pseudónimo no puede estar vacío")
    @Size(max = 30, message = "El nombre no debe exceder los 30 caracteres")
    private String pseudonimoCuenta;

}
