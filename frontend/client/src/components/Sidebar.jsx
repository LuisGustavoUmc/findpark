import React from "react";
import { Link, useNavigate } from "react-router-dom";
import logo from "../assets/logo-findpark.png";

export default function Sidebar() {
  const navigate = useNavigate();

  // Função de logout
  async function logout() {
    localStorage.clear(); 
    navigate('/'); 
  }

  const role = localStorage.getItem("userRole");

  if (!role) {
    return (
      <div className="sidebar bg-dark text-white p-3">
        <p>Você não tem permissão para acessar esta área. Faça login primeiro.</p>
      </div>
    );
  }

  return (
    <div className="sidebar bg-dark text-white" style={{ width: "250px", height: "100vh" }}>
      <div className="text-center my-4">
        <img src={logo} alt="Logo" className="logo mb-3" width="120" />
      </div>
      <ul className="list-unstyled">
        <li>
          <Link to="/vagas" className="text-white d-block py-2 px-3">
            Home
          </Link>
        </li>

        {role === "CLIENTE" && (
          <>
            <li>
              <Link to="/reservas" className="text-white d-block py-2 px-3">
                Minhas Reservas
              </Link>
            </li>
            <li>
              <Link to="/placas" className="text-white d-block py-2 px-3">
                Placas
              </Link>
            </li>
            <li>
              <Link to="/buscar" className="text-white d-block py-2 px-3">
                Buscar Estacionamentos
              </Link>
            </li>
          </>
        )}

        {role === "PROPRIETARIO" && (
          <>
            <li>
              <Link to="/estacionamentos" className="text-white d-block py-2 px-3">
                Meus Estacionamentos
              </Link>
            </li>
            <li>
              <Link to="/vagas" className="text-white d-block py-2 px-3">
                Gerenciar Vagas
              </Link>
            </li>
          </>
        )}

        {role === "ADMIN" && (
          <>
            <li>
              <Link to="/admin" className="text-white d-block py-2 px-3">
                Painel Admin
              </Link>
            </li>
          </>
        )}

        <li>
          <button
            onClick={logout}
            type="button"
            className="btn btn-danger w-100 mt-4"
          >
            Logout
          </button>
        </li>
      </ul>
    </div>
  );
}
