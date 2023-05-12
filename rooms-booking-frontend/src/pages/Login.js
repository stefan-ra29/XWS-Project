import "./styles/Login.css";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../service/AuthService";
import { getRoleFromLocalStorage } from "../utils/LocalStorageService";

export default function Login() {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const [isDisabled, setIsDisabled] = useState(true);

  const role = getRoleFromLocalStorage();
  const navigate = useNavigate();
  useEffect(() => {
    if (role === "GUEST" || role === "HOST") {
      navigate("/");
    }
  }, []);

  const handleCredentialsChange = (event) => {
    const name = event.name;
    const value = event.value.trim();
    setCredentials((values) => ({ ...values, [name]: value }));
    validateFieldInput();
  };

  function validateFieldInput() {
    if (
      document.getElementById("uname").value.trim().length > 0 &&
      document.getElementById("pass").value.trim().length > 0
    ) {
      setIsDisabled(false);
    } else {
      setIsDisabled(true);
    }
  }

  const submitForm = (event) => {
    event.preventDefault();
    console.log(credentials);
    login(credentials, navigate);
  };
  return (
    <div className="form-container-login">
      <form className="login-form" onSubmit={(event) => submitForm(event)}>
        <div>
          <div className="row-wrapper-login">
            <div>
              <label htmlFor="umame">Username:</label>
            </div>
            <div>
              <input
                type="text"
                id="uname"
                onChange={(e) => handleCredentialsChange(e.target)}
                name="username"
              />
            </div>
          </div>
          <div className="row-wrapper-login">
            <div>
              <label htmlFor="pass">Password:</label>
            </div>
            <div>
              <input
                type="text"
                id="pass"
                onChange={(e) => handleCredentialsChange(e.target)}
                name="password"
              />
            </div>
          </div>
        </div>

        <div>
          <input
            className="submit-input-login"
            disabled={isDisabled}
            type="submit"
            value="Log in"
          />
        </div>
      </form>
    </div>
  );
}
