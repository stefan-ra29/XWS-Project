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

  const role = getRoleFromLocalStorage();
  const navigate = useNavigate();
  useEffect(() => {
    if (role === "GUEST" || role === "HOST") {
      navigate("/");
    }
  }, []);

  const handleCredentialsChange = (event) => {
    const name = event.name;
    const value = event.value;
    setCredentials((values) => ({ ...values, [name]: value }));
  };

  const submitForm = (event) => {
    event.preventDefault();
    console.log(credentials);
    login(credentials, navigate);
  };
  return (
    <div>
      <div className="form-container">
        <form className="loginForm" onSubmit={(event) => submitForm(event)}>
          <div>
            <div>
              <div>
                <label className="label" htmlFor="umame">
                  Username:
                </label>
                <input
                  className="textInput"
                  type="text"
                  id="uname"
                  onChange={(e) => handleCredentialsChange(e.target)}
                  name="username"
                />
              </div>
              <div>
                <label className="label" htmlFor="pass">
                  Password:
                </label>
                <input
                  className="textInput"
                  type="text"
                  id="pass"
                  onChange={(e) => handleCredentialsChange(e.target)}
                  name="password"
                />
              </div>
            </div>
          </div>
          <div>
            <input className="submitInput" type="submit" value="Log in" />
          </div>
        </form>
      </div>
    </div>
  );
}
