import "./styles/Login.css";
import { useState } from "react";

export default function Login() {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const handleCredentialsChange = (event) => {
    const name = event.name;
    const value = event.value;
    setCredentials((values) => ({ ...values, [name]: value }));
  };

  const submitForm = (event) => {
    event.preventDefault();
    console.log(credentials);
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
            <input className="submitInput" type="submit" value="Register" />
          </div>
        </form>
      </div>
    </div>
  );
}
