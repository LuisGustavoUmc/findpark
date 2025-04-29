import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import logo from "../../assets/logo-findpark.png";
import imgTelaLogin from "../../assets/img-telalogin-esquerda.jpg"
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

      const role = response.data.role;

      localStorage.setItem("email", email);
      localStorage.setItem("accessToken", response.data.accessToken);
      localStorage.setItem("userRole", response.data.role);

      if (role === "ADMIN") {
        navigate("/vagas"); // Página do admin
      } else if (role === "CLIENTE") {
        navigate("/estacionamentos"); // Página do proprietário
      } else if (role === "PROPRIETARIO") {
        navigate("/vagas"); // Página do cliente
      } 
    } catch (err) {
      alert("Falha no Login! Tente novamente!");
    }
  }

  return (
    <div className="row vh-100 g-0">
      {/* Lado esquerdo */}
      <div className="col-lg-6 position-relative d-none d-lg-block">
        <div className="bg-holder" style={{ backgroundImage: `url(${imgTelaLogin})`}}></div>
      </div>

      {/* Lado direito */}
      <div className="col-lg-6">
        <div className="row align-items-center justify-content-center h-100 g-0 px-4 px-sm-0">
          <div className="col col-sm-6 col-lg-7 col-xl-6">
            {/* Logo */}
            <a href="#" className="d-flex justify-content-center mb-4">
              <img src={logo} alt="FindPark" width={100} />
            </a>

            <div className="text-center mb-5">
              <h3 className="fw-bold">Entrar</h3>
              <p className="text-secondary">Acesse sua conta</p>
            </div>

            {/* Formulário */}
            <form onSubmit={login}>
              <div className="input-group mb-3">
                <span className="input-group-text">
                  <i className="lni lni-envelope-1"></i>
                </span>
                <input type="email" className="form-control form-control-lg fs-6" placeholder="E-mail"
                  value={email} onChange={(e) => setEmail(e.target.value)}/>
              </div>
              <div className="input-group mb-3">
                <span className="input-group-text">
                  <i className="lni lni-locked-2"></i>
                </span>
                <input type="password" className="form-control form-control-lg fs-6" placeholder="Senha"
                  value={senha} onChange={(e) => setSenha(e.target.value)}/>
              </div>
              <div className="input-group mb-3 d-flex justify-content-between">
                <div className="form-check">
                  <input type="checkbox" className="form-check-input" id="formCheck" />
                  <label htmlFor="formCheck" className="form-check-label text-secondary"><small>Lembrar de mim</small></label>
                </div>
                <div>
                  <small><a href="#">Esqueceu sua senha?</a></small>
                </div>
              </div>
              <button type="submit" className="btn btn-primary btn-lg w-100 mb-3">Entrar</button>
            </form>
            {/* Fomulário */}

            <div className="text-center">
              <small>Não tem uma conta? <a href="#" className="fw-bold">Criar nova conta</a></small>
            </div>
          </div>
        </div>
      </div>
      {/* Lado direito */}
    </div>





    // <div className="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light">
    //   <div className="login-card card shadow-sm p-4 p-md-5">
    //     <div className="text-center mb-4">
    //       <img src={logo} alt="FindPark logo" className="login-logo mb-3" />
    //       <h3 className="fw-bold">Login</h3>
    //     </div>

    //     <form onSubmit={login}>
    //       <div className="mb-3">
    //         <label htmlFor="email" className="form-label">
    //           Email
    //         </label>
    //         <input type="email" className="form-control" id="email" placeholder="Digite seu email"
    //           value={email} onChange={(e) => setEmail(e.target.value)}
    //         />
    //       </div>

    //       <div className="mb-2">
    //         <label htmlFor="senha" className="form-label">
    //           Senha
    //         </label>
    //         <input type="password" className="form-control" id="senha" placeholder="Digite sua senha"
    //           value={senha} onChange={(e) => setSenha(e.target.value)}
    //         />
    //       </div>

    //       <div className="text-end mb-3">
    //         <a href="#" className="text-decoration-none">
    //           Esqueceu sua senha?
    //         </a>
    //       </div>

    //       <div className="d-grid gap-2">
    //         <button type="submit" className="btn btn-primary">
    //           Entrar
    //         </button>
    //         <button type="button" className="btn btn-outline-secondary">
    //           Criar Conta Cliente
    //         </button>
    //         <button type="button" className="btn btn-outline-secondary">
    //           Criar Conta Proprietário
    //         </button>
    //       </div>
    //     </form>
    //   </div>
    // </div>
  );
}
