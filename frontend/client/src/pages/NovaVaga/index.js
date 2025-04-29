import React, { useState, useEffect } from "react";
import "./styles.css";
import { useNavigate, Link, useParams } from "react-router-dom";
import logo from "../../assets/logo-findpark.png";
import api from "../../services/api";

export default function NovaVaga() {

  const [id, setId] = useState(null);
  const [status, setStatus] = useState('');
  const [tipo, setTipo] = useState('');
  const [preco, setPreco] = useState('');

  const {vagaId} = useParams();

  const email = localStorage.getItem("email");
  const accessToken = localStorage.getItem("accessToken");

  const navigate = useNavigate();

  async function loadVaga() {
    try {
      const response = await api.get(`findpark/vaga/${vagaId}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`
          }
        })

        setId(response.data.id);
        setStatus(response.data.status);
        setTipo(response.data.tipo);
        setPreco(response.data.preco);
    } catch (err) {
      alert('Erro ao recuperar vaga! Tente novamente.')
      navigate('/vagas')
    }
  }

  useEffect(() => {
    if (vagaId === '0') return;
    else loadVaga();
  }, [vagaId])

  async function saveOrUpdate(e) {
    e.preventDefault();

    const data = {
      status,
      tipo,
      preco,
    }

    try {
      if (vagaId === '0') {
        await api.post("findpark/vaga", data, {
          headers: {
            Authorization: `Bearer ${accessToken}`
          }
        });
      } else {
        data.id = id;
        await api.put("findpark/vaga", data, {
          headers: {
            Authorization: `Bearer ${accessToken}`
          }
        });
      }
      
      navigate('/vagas')
    } catch (err) {
      alert('Erro ao salvar a vaga! Tente Novamente!')
    }
  }

  return (
    <div className="nova-vaga-container">
      <div className="content">
        <section className="form">
          <img src={logo} alt="FindPark" />
          <h1>{vagaId === '0' ? 'Adicionar nova vaga' : 'Atualizar'}</h1>
          <p>Insira as informações da vaga e clique em {vagaId === '0' ? "'Adicionar'" : "'Atualizar'"}:</p>
          <Link className="btn btn-warning" to="/vagas">
            voltar
          </Link>
        </section>
        <form onSubmit={saveOrUpdate}>
          <input placeholder="status" value={status} onChange={e => setStatus(e.target.value)} />
          <input placeholder="tipo" value={tipo} onChange={e => setTipo(e.target.value)}/>
          <input placeholder="preço" value={preco} onChange={e => setPreco(e.target.value)}/>

          <button className="btn btn-warning" type="submit">{vagaId === '0' ? 'Adicionar' : 'Atualizar'}</button>
        </form>
      </div>
    </div>
  );
}
