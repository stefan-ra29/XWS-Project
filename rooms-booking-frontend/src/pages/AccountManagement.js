import "./styles/AccountManagement.css";
import { useEffect, useState } from "react";
import { getUser } from "../service/UserService";
import { getRoleFromLocalStorage } from "../utils/LocalStorageService";

export default function AccountManagement() {
  const role = getRoleFromLocalStorage();
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
    // if (role === "GUEST" || role === "HOST") {
    //     navigate("/");
    //   }
    //setUser(getUser());
  }, []);

  function onChange() {
    console.log("here");
  }

  return (
    <div className="form-container-account">
      <form className="account-form">
        <div>
          <div className="row-wrapper-account">
            <div>
              <label htmlFor="fnmame">First name:</label>
            </div>
            <div>
              <input
                type="text"
                id="fname"
                disabled={true}
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
                disabled={true}
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
                disabled={true}
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
                disabled={true}
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
                disabled={true}
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
                disabled={true}
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
                disabled={true}
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
                disabled={true}
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
                disabled={true}
                value={user.email}
              />
            </div>
          </div>
        </div>
        <div>
          <button className="submit-input-account" onChange={onChange}>
            Change account information
          </button>
        </div>
      </form>
    </div>
  );
}
