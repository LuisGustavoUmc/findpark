import React, {useState} from "react";
import { Link, useNavigate } from "react-router-dom";
import "./css/styles.css";

export default function Sidebar() {
  const navigate = useNavigate();
  const [expanded, setExpanded] = useState(false);

  // Função de logout
  async function logout() {
    localStorage.clear();
    navigate("/");
  }

  function handleLogoutClick(e) {
    e.preventDefault();
    logout();
  }

  const role = localStorage.getItem("userRole");

  if (!role) {
    return (
      <div className="sidebar bg-dark text-white p-3">
        <p>Você não tem permissão para acessar esta área. Faça login primeiro.</p>
      </div>
    );
  }

  function toggleSidebar() {
    setExpanded(!expanded);
  }

  return (
    <div className="wrapper">
      <aside id="sidebar" className={expanded ? "expand" : ""}>
        <div className="d-flex">
          <button id="toggle-btn" type="button" onClick={toggleSidebar}>
            <i className="lni lni-dashboard-square-1"></i>
          </button>
          <div className="sidebar-logo">
            <Link href="#">FindPark</Link>
          </div>
        </div>
        <ul className="sidebar-nav">
          <li className="sidebar-item">
            <Link href="#" className="sidebar-link">
              <i className="lni lni-user-4"></i>
              <span>Perfil</span>
            </Link>
          </li>
          <li className="sidebar-item">
            <Link href="#" className="sidebar-link">
              <i className="lni lni-calendar-days"></i>
              <span>Reservas</span>
            </Link>
          </li>
          <li className="sidebar-item">
            <Link href="#" className="sidebar-link has-dropdown collapsed" data-bs-toggle="collapse" 
            data-bs-target="#auth" aria-expanded="false" aria-controls="auth">
                <i className="lni lni-shield-2-check"></i>
                <span>Auth</span>
            </Link>
            <ul id="auth" className="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
              <li className="sidebar-item">
                <Link href="#" className="sidebar-link">Login</Link>
              </li>
              <li className="sidebar-item">
                <Link href="#" className="sidebar-link">Registrar</Link>
              </li>
            </ul>
          </li>
          <li className="sidebar-item">
            <Link href="#" className="sidebar-link">
              <i className="lni lni-bell-1"></i>
              <span>Notificações</span>
            </Link>
          </li>
          <li className="sidebar-item">
            <Link href="#" className="sidebar-link">
              <i className="lni lni-gear-1"></i>
              <span>Configurações</span>
            </Link>
          </li>
        </ul>
        <div className="sidebar-footer">
          <Link href="#" className="sidebar-link" onClick={handleLogoutClick}>
            <i className="lni lni-exit"></i>
            <span>Sair</span>
          </Link>
        </div>
      </aside>
    </div>


    // <div className="sidebar bg-dark text-white" style={{ width: "250px", height: "100vh" }}>
    //   <div className="text-center my-4">
    //     <img src={logo} alt="Logo" className="logo mb-3" width="120" />
    //   </div>
    //   <ul className="list-unstyled">
    //     <li>
    //       <Link to="/vagas" className="text-white d-block py-2 px-3">
    //         Home
    //       </Link>
    //     </li>

    //     {role === "CLIENTE" && (
    // <>
    //   <li>
    //     <Link to="/reservas" className="text-white d-block py-2 px-3">
    //       Minhas Reservas
    //     </Link>
    //   </li>
    //   <li>
    //     <Link to="/placas" className="text-white d-block py-2 px-3">
    //       Placas
    //     </Link>
    //   </li>
    //   <li>
    //     <Link to="/buscar" className="text-white d-block py-2 px-3">
    //       Buscar Estacionamentos
    //     </Link>
    //   </li>
    // </>
    // )}

    // {role === "PROPRIETARIO" && (
    // <>
    //   <li>
    //     <Link to="/estacionamentos" className="text-white d-block py-2 px-3">
    //       Meus Estacionamentos
    //     </Link>
    //   </li>
    //   <li>
    //     <Link to="/vagas" className="text-white d-block py-2 px-3">
    //       Gerenciar Vagas
    //     </Link>
    //   </li>
    // </>
    // )}

    // {role === "ADMIN" && (
    // <>
    //   <li>
    //     <Link to="/admin" className="text-white d-block py-2 px-3">
    //       Painel Admin
    //     </Link>
    //   </li>
    // </>
    // )}

    //   <li>
    //     <button
    //       onClick={logout}
    //       type="button"
    //       className="btn btn-danger w-100 mt-4"
    //     >
    //       Logout
    //     </button>
    //   </li>
    // </ul>
    //   </div>
  );
}
