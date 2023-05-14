import "./styles/Registration.css";
import RegistrationForm from "../components/account/RegistrationForm";
import { useState, useEffect } from "react";
import { register } from "../service/UserService";
import { useNavigate } from "react-router-dom";
import { getRoleFromLocalStorage } from "../utils/LocalStorageService";

export default function Registration() {
  const role = getRoleFromLocalStorage();
  const navigate = useNavigate();
  useEffect(() => {
    if (role !== null) {
      navigate("/");
    }
  }, []);

  const registerUser = (user) => {
    register(user, navigate);
  };
  return (
    <div>
      <div className="alignment">
        <RegistrationForm onSubmit={registerUser} />
      </div>
    </div>
  );
}
