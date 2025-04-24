import React from "react";
import Sidebar from "./Sidebar";
import { Outlet } from "react-router-dom";
import './styles.css'; 

export default function AppLayout() {
  return (
    <div className="layout-container">
      <Sidebar />
      <main className="layout-content">
        <Outlet />
      </main>
    </div>
  );
}
