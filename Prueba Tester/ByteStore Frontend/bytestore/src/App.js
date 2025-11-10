import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from './paginas/HomePage';
import AuthPage from './paginas/AuthPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Esta es la ruta index */}
        <Route path="/" element={<HomePage />} />

        {/* Ruta para login/registro */}
        <Route path="/auth" element={<AuthPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
