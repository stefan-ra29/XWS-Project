import "./RegistrationForm.css";
import { useState } from "react";

export default function RegistrationForm({ isHost, onSubmit }) {
  const [newUser, setNewUser] = useState({
    firstName: "",
    lastName: "",
    address: { country: "", city: "", street: "", streetNumber: "" },
    username: "",
    password: "",
    email: "",
    isHost: isHost,
  });
  const [newAddress, setNewAddress] = useState({
    country: "",
    city: "",
    street: "",
    streetNumber: "",
  });

  const handleUserInfoChange = (event) => {
    const name = event.name;
    const value = event.value;
    setNewUser((values) => ({ ...values, [name]: value }));
  };

  const handleAddressInfoChange = (event) => {
    const name = event.name;
    const value = event.value;
    setNewAddress((values) => ({ ...values, [name]: value }));
  };
  const submitForm = (event) => {
    event.preventDefault();
    newUser.address = newAddress;
    setNewUser((values) => ({
      ...values,
      values: newUser,
    }));
    onSubmit(newUser);
  };

  return (
    <div>
      <h2 style={{ textAlign: "center", justifyContent: "center" }}>
        Register as {isHost ? "host" : "guest"} here!
      </h2>
      <div className="form-container">
        <form className="registrationForm" onSubmit={(event) => submitForm(event)}>
          <div>
            <div
              style={{
                textAlign: "left",
                display: "flex",
                flexDirection: "row",
              }}
            >
              <div
                style={{
                  width: "260px",
                }}
              >
                <label className="label" htmlFor="fnmame">First name:</label>
                <input
                  className="textInput"
                  type="text"
                  id="fname"
                  onChange={(e) => handleUserInfoChange(e.target)}
                  name="firstName"
                />
              </div>
              <div>
                <label className="label" htmlFor="lname">Last name:</label>
                <input
                  className="textInput"
                  type="text"
                  id="lname"
                  style={{ marginLeft: "45px" }}
                  onChange={(e) => handleUserInfoChange(e.target)}
                  name="lastName"
                />
              </div>
            </div>

            <div
              style={{
                textAlign: "left",
                display: "flex",
                flexDirection: "row",
              }}
            >
              <div
                style={{
                  width: "260px",
                }}
              >
                <label className="label" htmlFor="a-country">Country:</label>
                <input
                  className="textInput"
                  type="text"
                  id="a-country"
                  style={{ marginLeft: "29px" }}
                  onChange={(e) => handleAddressInfoChange(e.target)}
                  name="country"
                />
              </div>
              <div>
                <label className="label" htmlFor="a-city">City:</label>
                <input
                  className="textInput"
                  type="text"
                  id="a-city"
                  style={{ marginLeft: "96px" }}
                  onChange={(e) => handleAddressInfoChange(e.target)}
                  name="city"
                />
              </div>
            </div>
            <div
              style={{
                textAlign: "left",
                display: "flex",
                flexDirection: "row",
              }}
            >
              <div
                style={{
                  width: "260px",
                }}
              >
                <label className="label" htmlFor="a-street">Street:</label>
                <input
                  className="textInput"
                  type="text"
                  id="a-street"
                  style={{ marginLeft: "47px" }}
                  onChange={(e) => handleAddressInfoChange(e.target)}
                  name="street"
                />
              </div>
              <div>
                <label className="label" htmlFor="a-number">Street number:</label>
                <input
                  className="textInput"
                  type="text"
                  id="a-number"
                  onChange={(e) => handleAddressInfoChange(e.target)}
                  name="streetNumber"
                />
              </div>
            </div>
            <div
              style={{
                textAlign: "left",
                display: "flex",
                flexDirection: "row",
              }}
            >
              <div
                style={{
                  width: "260px",
                }}
              >
                <label className="label" htmlFor="uname">Username:</label>
                <input
                  className="textInput"
                  type="text"
                  id="uname"
                  name="username"
                  style={{ marginLeft: "13px" }}
                  onChange={(e) => handleUserInfoChange(e.target)}
                />
              </div>
              <div>
                <label className="label" htmlFor="pass">Password:</label>
                <input
                  className="textInput"
                  type="text"
                  id="pass"
                  name="password"
                  style={{ marginLeft: "51px" }}
                  onChange={(e) => handleUserInfoChange(e.target)}
                />
              </div>
            </div>
            <div
              style={{
                textAlign: "center",
                display: "flex",
                flexDirection: "row",
                justifyContent: "center",
              }}
            >
              <div>
                <label className="label" htmlFor="id-email">E-mail address:</label>
                <input
                  className="textInput"
                  type="text"
                  id="id-email"
                  name="email"
                  onChange={(e) => handleUserInfoChange(e.target)}
                />
              </div>
            </div>
          </div>
          <div
            style={{
              textAlign: "center",
              display: "flex",
              flexDirection: "row",
              justifyContent: "center",
            }}
          >
            <input className="submitInput" type="submit" value="Register" />
          </div>
        </form>
      </div>
    </div>
  );
}
