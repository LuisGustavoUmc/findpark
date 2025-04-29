import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Login from './pages/Login';
import Vaga from "./pages/Vaga";
import Estacionamento from './pages/Estacionamento'
import Admin from './pages/Admin'
import AppLayout from "./components/AppLayout";
import NovaVaga from "./pages/NovaVaga";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Login fora do layout */}
        <Route path="/" exact element={<Login />} />

        {/* Rotas protegidas com layout */}
        <Route element={<AppLayout />}>
          <Route path="/vagas" element={<Vaga />} />
          <Route path="/estacionamentos" element={<Estacionamento />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/vaga/novo/:vagaId" element={<NovaVaga />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
