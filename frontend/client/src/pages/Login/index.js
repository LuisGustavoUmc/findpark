import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import logo from "../../assets/logo-findpark.png";
import api from "../../services/api";

export default function Login() {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const navigate = useNavigate();

  async function login(e) {
    e.preventDefault();

    const data = {
      email,
      senha,
    };

    try {
      const response = await api.post("findpark/auth/login", data);

      localStorage.setItem("email", email);
      localStorage.setItem("accessToken", response.data.token);

      navigate("/vagas");
    } catch (err) {
      alert("Falha no Login! Tente novamente!");
    }
  }

  return (
    <div className="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <div className="login-card card shadow-sm p-4 p-md-5">
        <div className="text-center mb-4">
          <img src={logo} alt="FindPark logo" className="login-logo mb-3" />
          <h3 className="fw-bold">Login</h3>
        </div>

        <form onSubmit={login}>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              placeholder="Digite seu email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div className="mb-2">
            <label htmlFor="senha" className="form-label">
              Senha
            </label>
            <input
              type="password"
              className="form-control"
              id="senha"
              placeholder="Digite sua senha"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
            />
          </div>

          <div className="text-end mb-3">
            <a href="#" className="text-decoration-none">
              Esqueceu sua senha?
            </a>
          </div>

          <div className="d-grid gap-2">
            <button type="submit" className="btn btn-primary">
              Entrar
            </button>
            <button type="button" className="btn btn-outline-secondary">
              Criar Conta Cliente
            </button>
            <button type="button" className="btn btn-outline-secondary">
              Criar Conta Proprietário
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
