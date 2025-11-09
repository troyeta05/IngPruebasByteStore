package com.bytestore.bytestore.service;

import com.bytestore.bytestore.exception.CodigoRecuperacionInvalidoException;
import com.bytestore.bytestore.exception.CorreoDuplicadoException;
import com.bytestore.bytestore.exception.CuentaNoEncontradaException;
import com.bytestore.bytestore.model.Cuenta;
import com.bytestore.bytestore.repository.CuentaRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CorreoService correoService;

    public CuentaService(CuentaRepository cuentaRepository, CorreoService correoService) {
        this.cuentaRepository = cuentaRepository;
        this.correoService = correoService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Registrar una nueva cuenta
    public Cuenta registrarCuenta(Cuenta cuenta) {
        if (correoRegistrado(cuenta.getCorreoCuenta())) {
            throw new CorreoDuplicadoException("El correo ya está registrado");
        }

        asignarNoCuenta(cuenta);
        generarCodigoRecuperacion(cuenta);
        hashContrasena(cuenta);

        return cuentaRepository.save(cuenta);
    }

    // Hashear contraseñas
    public void hashContrasena(Cuenta cuenta) {
        String hash = passwordEncoder.encode(cuenta.getContrasena());
        cuenta.setContrasena(hash);
    }

    // Asigar el número de cuenta
    public void asignarNoCuenta(Cuenta cuenta) {
        LocalDateTime ahora = LocalDateTime.now();

        int dia = ahora.getDayOfMonth();
        int mes = ahora.getMonthValue();
        int hora = ahora.getHour();
        int minuto = ahora.getMinute();
        int noAleatorio = ThreadLocalRandom.current().nextInt(1, 100);

        String diaStr = (dia < 10) ? "0" + dia : String.valueOf(dia);
        String mesStr = (mes < 10) ? "0" + mes : String.valueOf(mes);
        String horaStr = (hora < 10) ? "0" + hora : String.valueOf(hora);
        String minutoStr = (minuto < 10) ? "0" + minuto : String.valueOf(minuto);
        String noAleatorioStr = (noAleatorio < 10) ? "0" + noAleatorio : String.valueOf(noAleatorio);

        String noCuenta = horaStr + minutoStr + diaStr + mesStr + noAleatorioStr;
        cuenta.setNumCuenta(noCuenta);
    }

    // Agregar un número de cuenta de recuperación
    public void generarCodigoRecuperacion(Cuenta cuenta) {
        int codigo = ThreadLocalRandom.current().nextInt(100000, 1000000);
        String codigoRecuperacion = String.valueOf(codigo);
        cuenta.setCodigoCuenta(codigoRecuperacion);
    }

    // Enviar el codigo para recuperar la cuenta
    public void enviarCodigoRecuperacion(String correo) {
        Cuenta cuenta = cuentaRepository.findByCorreoCuenta(correo);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("No se encontró la cuenta con ese correo");
        }

        generarCodigoRecuperacion(cuenta);
        cuentaRepository.save(cuenta); // ← Esto guarda el nuevo código

        String saludo = obtenerSaludoPorHora();

        String asunto = "Código de recuperación de ByteStore";
        String cuerpo = "Hola, " + saludo + ".\n\n"
                + "Se le envía su código para recuperar su cuenta.\n"
                + "Código de recuperación: " + cuenta.getCodigoCuenta();

        correoService.enviarCorreo(correo, asunto, cuerpo);
    }

    // Buscar correo registrado
    public boolean correoRegistrado(String correo) {
        return cuentaRepository.existsByCorreoCuenta(correo);
    }

    // Hacer cambio de contraseña
    public void cambiarContrasena(String correo, String codigo, String nuevaContrasena) {
        Cuenta cuenta = cuentaRepository.findByCorreoCuenta(correo);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("No se encontró la cuenta con ese correo");
        }

        if (!codigo.equals(cuenta.getCodigoCuenta())) {
            throw new CodigoRecuperacionInvalidoException("El código de recuperación es incorrecto");
        }

        cuenta.setContrasena(passwordEncoder.encode(nuevaContrasena));
        generarCodigoRecuperacion(cuenta);
        cuentaRepository.save(cuenta);
    }

    private String obtenerSaludoPorHora() {
        int hora = LocalDateTime.now().getHour();
        if (hora >= 6 && hora < 12) return "buenos días";
        if (hora >= 12 && hora < 19) return "buenas tardes";
        return "buenas noches";
    }

    // Enviar cuenta a correo
    public void enviarCredencialesPorCorreo(Cuenta cuenta, String contrasenaSH) {
        String saludo = obtenerSaludoPorHora();
        String asunto = "Bienvenido a ByteStore";
        String cuerpo = "Hola, " + saludo + ".\n\n"
            + "Su cuenta ha sido creada exitosamente.\n"
            + "Número de cuenta: " + cuenta.getNumCuenta() + "\n"
            + "Correo registrado: " + cuenta.getCorreoCuenta() + "\n"
            + "Contraseña: " + contrasenaSH + "\n\n"
            + "Recuerde no compartir su usario y contraseña.";

        correoService.enviarCorreo(cuenta.getCorreoCuenta(), asunto, cuerpo);
    }

    // Validar cuenta al iniciar seción
    public Cuenta validarCredenciales(String correo, String contrasena) {
        Cuenta cuenta = cuentaRepository.findByCorreoCuenta(correo);
        if (cuenta == null || !passwordEncoder.matches(contrasena, cuenta.getContrasena())) {
            throw new CuentaNoEncontradaException("Credenciales inválidas");
        }
        return cuenta;
    }

}