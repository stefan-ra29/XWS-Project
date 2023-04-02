import React, { useEffect, useState } from "react";
import { register } from "../services/RegistrationService";
import "./styles/Registration.css";

const Registration = () => {
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    if (localStorage.getItem("token") != null) {
      window.location.replace("/");
    }
  }, []);

  function handleRegistraion() {
    register({
      name: name,
      surname: surname,
      email: email,
      password: password,
    });
  }

  return (
    <div>
      <div className="registration-form-container">
        <div className="registration-form-row">
          <label className="registration-form-label">Name: </label>
          <input
            className="registration-form-input"
            type="text"
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="registration-form-row">
          <label className="registration-form-label">Surname: </label>
          <input
            className="registration-form-input"
            type="text"
            onChange={(e) => setSurname(e.target.value)}
          />
        </div>
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
          <button onClick={() => handleRegistraion()}>Register</button>
        </div>
      </div>
    </div>
  );
};

export default Registration;
