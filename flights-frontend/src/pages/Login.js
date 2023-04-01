import React, { useState } from "react";
import { login } from "../services/AuthService";
import "./styles/Registration.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  function handleLogin() {
    login({
      email: email,
      password: password,
    });
  }

  return (
    <div>
      <div className="registration-form-container">
        <div className="registration-form-row">
          <label className="registration-form-label">Email: </label>
          <input
            className="registration-form-input"
            type="text"
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="registration-form-row">
          <label className="registration-form-label">Password: </label>
          <input
            className="registration-form-input"
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="registration-form-button-wrapper">
          <button onClick={() => handleLogin()}>Login</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
