import "./styles/AccountManagement.css";
import { useEffect, useState } from "react";
import { getUser } from "../service/UserService";
import {
  getTokenFromLocalStorage,
  getIdFromLocalStorage,
} from "../utils/LocalStorageService";
import { useNavigate } from "react-router-dom";

export default function AccountManagement() {
  const token = getTokenFromLocalStorage();
  const navigate = useNavigate();
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    address: { country: "", city: "", street: "", streetNumber: "" },
    username: "",
    password: "",
    email: "",
  });
  const [isChangeActivated, setIsChangeActivated] = useState(false);
  useEffect(() => {
    if (token === null) {
      navigate("/");
    }
    getUser(getIdFromLocalStorage(), setUser);
  }, []);

  const onChange = (event) => {
    event.preventDefault();
    setIsChangeActivated(!isChangeActivated);
  };

  return (
    <div className="form-container-account">
      <form className="account-form" onSubmit={(e) => onChange(e)}>
        <div>
          <div className="row-wrapper-account">
            <div>
              <label htmlFor="fnmame">First name:</label>
            </div>
            <div>
              <input
                type="text"
                id="fname"
                disabled={!isChangeActivated}
                value={user.firstName}
                name="firstName"
              />
            </div>

            <div>
              <label htmlFor="lname">Last name:</label>
            </div>
            <div>
              <input
                type="text"
                id="lname"
                disabled={!isChangeActivated}
                value={user.lastName}
                name="lastName"
              />
            </div>
          </div>

          <div className="row-wrapper-account">
            <div>
              <label htmlFor="a-country">Country:</label>
            </div>
            <div>
              <input
                type="text"
                id="a-country"
                disabled={!isChangeActivated}
                value={user.address.country}
                name="country"
              />
            </div>
            <div>
              <label htmlFor="a-city">City:</label>
            </div>
            <div>
              <input
                type="text"
                id="a-city"
                disabled={!isChangeActivated}
                value={user.address.city}
                name="city"
              />
            </div>
          </div>
          <div className="row-wrapper-account">
            <div>
              <label htmlFor="a-street">Street:</label>
            </div>
            <div>
              <input
                type="text"
                id="a-street"
                disabled={!isChangeActivated}
                value={user.address.street}
                name="street"
              />
            </div>
            <div>
              <label htmlFor="a-number">Street number:</label>
            </div>
            <div>
              <input
                type="text"
                id="a-number"
                disabled={!isChangeActivated}
                value={user.address.streetNumber}
                name="streetNumber"
              />
            </div>
          </div>
          <div className="row-wrapper-account">
            <div>
              <label htmlFor="uname">Username:</label>
            </div>
            <div>
              <input
                type="text"
                id="uname"
                name="username"
                disabled={!isChangeActivated}
                value={user.username}
              />
            </div>
            <div>
              <label htmlFor="pass">Password:</label>
            </div>
            <div>
              <input
                type="text"
                id="pass"
                name="password"
                disabled={!isChangeActivated}
                value={user.password}
              />
            </div>
          </div>
          <div className="email-row-wrapper-account">
            <div className="email-label">
              <label htmlFor="id-email">E-mail address:</label>{" "}
            </div>
            <div className="email-input">
              <input
                type="text"
                id="id-email"
                name="email"
                disabled={!isChangeActivated}
                value={user.email}
              />
            </div>
          </div>
        </div>
        <div>
          {!isChangeActivated && (
            <button className="change-button-account">
              Change account information
            </button>
          )}
          {isChangeActivated && (
            <button className="change-button-account">Submit</button>
          )}
        </div>
      </form>
    </div>
  );
}
