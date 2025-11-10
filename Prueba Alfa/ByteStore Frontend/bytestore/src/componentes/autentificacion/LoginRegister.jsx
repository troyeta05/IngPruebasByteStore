import { useState } from 'react';
import axios from 'axios';

export default function LoginRegister() {
  const [isLogin, setIsLogin] = useState(true);
  const [form, setForm] = useState({
    correoCuenta: '',
    contrasena: '',
    pseudonimoCuenta: ''
  });
  const [mensaje, setMensaje] = useState('');

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const endpoint = isLogin ? '/cuenta/login' : '/cuenta/crear';
    const payload = isLogin
      ? { correoCuenta: form.correoCuenta, contrasena: form.contrasena }
      : {
          correoCuenta: form.correoCuenta,
          contrasena: form.contrasena,
          pseudonimoCuenta: form.pseudonimoCuenta
        };

    try {
      const res = await axios.post(
        `https://bytestore-backend-production.up.railway.app${endpoint}`,
        payload
      );
      setMensaje(res.data.mensaje);
    } catch (err) {
      setMensaje('Error: ' + (err.response?.data?.mensaje || 'No se pudo conectar'));
    }
  };

  return (
    <div>
      <h2>{isLogin ? 'Iniciar sesión' : 'Crear cuenta'}</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          name="correoCuenta"
          placeholder="Correo"
          value={form.correoCuenta}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="contrasena"
          placeholder="Contraseña"
          value={form.contrasena}
          onChange={handleChange}
          required
        />
        {!isLogin && (
          <input
            type="text"
            name="pseudonimoCuenta"
            placeholder="Pseudónimo"
            value={form.pseudonimoCuenta}
            onChange={handleChange}
            required
          />
        )}
        <button type="submit">{isLogin ? 'Entrar' : 'Registrarse'}</button>
      </form>
      <button onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? '¿No tienes cuenta?' : '¿Ya tienes cuenta?'}
      </button>
      {mensaje && <p>{mensaje}</p>}
    </div>
  );
}
