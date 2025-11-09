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
public class CambioContrasenaDto {

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Formato de correo inválido")
    @Size(max = 50, message = "El correo no debe exeder los 50 caracteres")
    private String correoCuenta;

    @NotBlank(message = "El código no debe estar vacio")
    @Size(min = 6, max = 6, message = "El código solo debe tener 6 caracteres")
    private String codigoCuenta;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 25, message = "Debe tener entre 8 y 25 caracteres")
    private String contrasena;

}
