import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Login from './pages/Login';
import Vaga from "./pages/Vaga";

export default function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" exact element={<Login/>}/>
                <Route path="/vagas" element={<Vaga/>}/>
            </Routes>
        </BrowserRouter>
    );
}