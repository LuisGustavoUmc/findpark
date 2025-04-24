import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Login from './pages/Login';
import Vaga from "./pages/Vaga";
import AppLayout from "./components/AppLayout";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Login fora do layout */}
        <Route path="/" element={<Login />} />

        {/* Rotas protegidas com layout */}
        <Route element={<AppLayout />}>
          <Route path="/vagas" element={<Vaga />} />
          {/* outras rotas com sidebar aqui */}
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
