import "./RegistrationForm.css";
import { useState } from "react";

export default function RegistrationForm({ onSubmit }) {
  const [newUser, setNewUser] = useState({
    firstName: "",
    lastName: "",
    address: { country: "", city: "", street: "", streetNumber: "" },
    username: "",
    password: "",
    email: "",
    isHost: false,
  });
  const [newAddress, setNewAddress] = useState({
    country: "",
    city: "",
    street: "",
    streetNumber: "",
  });

  const [isHost, setIsHost] = useState(false);

  const handleRadioButtonSelectionChanged = (event) => {
    setIsHost(event.target.value === "true");
  };

  const handleUserInfoChange = (event) => {
    const name = event.name;
    const value = event.value.trim();
    setNewUser((values) => ({ ...values, [name]: value }));
    validateInputFields(event.id, value);
  };

  const handleAddressInfoChange = (event) => {
    const name = event.name;
    const value = event.value.trim();
    setNewAddress((values) => ({ ...values, [name]: value }));
    validateInputFields(event.id, value);
  };

  const submitForm = (event) => {
    event.preventDefault();
    newUser.address = newAddress;
    newUser.isHost = isHost;
    setNewUser((values) => ({
      ...values,
      values: newUser,
    }));
    onSubmit(newUser);
  };

  const [isDisabled, setIsDisabled] = useState(true);

  const nameRegex = /^[A-Z][a-z]*(\s[A-Z][a-z]*)*$/;
  const addressRegex = /^[A-Z][a-z]*(\s[A-Z]?[a-z]*)*$/;
  const streetNumberRegex = /^[1-9]+[A-Z]?$/;
  const credentialsRegex = /^([A-Za-z0-9]{3,})$/;
  const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;

  function validateInputFields(id, value) {
    switch (id) {
      case "fname": {
        if (nameRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "lname": {
        if (nameRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "a-country": {
        if (addressRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "a-city": {
        if (addressRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "a-street": {
        if (addressRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "a-number": {
        if (streetNumberRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "uname": {
        if (credentialsRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "pass": {
        if (credentialsRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      case "id-email": {
        if (emailRegex.test(value) === false) {
          document.getElementById(id).className = "error-input-field";
          setIsDisabled(true);
        } else {
          document.getElementById(id).className = "";
        }
        break;
      }
      default:
        return;
    }
    checkAccessibilityOfSubmitButton();
  }

  function checkAccessibilityOfSubmitButton() {
    if (
      nameRegex.test(document.getElementById("fname").value) === true &&
      nameRegex.test(document.getElementById("lname").value) === true &&
      addressRegex.test(document.getElementById("a-country").value) === true &&
      addressRegex.test(document.getElementById("a-city").value) === true &&
      addressRegex.test(document.getElementById("a-street").value) === true &&
      streetNumberRegex.test(document.getElementById("a-number").value) ===
        true &&
      credentialsRegex.test(document.getElementById("uname").value) === true &&
      credentialsRegex.test(document.getElementById("pass").value) === true &&
      emailRegex.test(document.getElementById("id-email").value) === true
    ) {
      setIsDisabled(false);
    }
  }

  return (
    <div className="form-container-registration">
      <form
        className="registration-form"
        onSubmit={(event) => submitForm(event)}
      >
        <div>
          <div className="radio-button-row-wrapper-registration">
            <div className="radio-label">Register as</div>
            <div>
              <div className="radio-checker-guest">
                <input
                  type="radio"
                  id="guest"
                  name="role"
                  value={false}
                  checked={isHost === false}
                  onChange={(e) => handleRadioButtonSelectionChanged(e)}
                />

                <label htmlFor="guest">Guest</label>
              </div>
              <div className="radio-checker-host">
                <input
                  type="radio"
                  id="host"
                  name="role"
                  value={true}
                  checked={isHost === true}
                  onChange={(e) => handleRadioButtonSelectionChanged(e)}
                />
                <label htmlFor="host">Host</label>{" "}
              </div>
            </div>
          </div>
          <div className="row-wrapper-registration">
            <div>
              <label htmlFor="fnmame">First name:</label>
            </div>
            <div>
              <input
                type="text"
                id="fname"
                onChange={(e) => handleUserInfoChange(e.target)}
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
                onChange={(e) => handleUserInfoChange(e.target)}
                name="lastName"
              />
            </div>
          </div>

          <div className="row-wrapper-registration">
            <div>
              <label htmlFor="a-country">Country:</label>
            </div>
            <div>
              <input
                type="text"
                id="a-country"
                onChange={(e) => handleAddressInfoChange(e.target)}
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
                onChange={(e) => handleAddressInfoChange(e.target)}
                name="city"
              />
            </div>
          </div>
          <div className="row-wrapper-registration">
            <div>
              <label htmlFor="a-street">Street:</label>
            </div>
            <div>
              <input
                type="text"
                id="a-street"
                onChange={(e) => handleAddressInfoChange(e.target)}
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
                onChange={(e) => handleAddressInfoChange(e.target)}
                name="streetNumber"
              />
            </div>
          </div>
          <div className="row-wrapper-registration">
            <div>
              <label htmlFor="uname">Username:</label>
            </div>
            <div>
              <input
                type="text"
                id="uname"
                name="username"
                onChange={(e) => handleUserInfoChange(e.target)}
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
                onChange={(e) => handleUserInfoChange(e.target)}
              />
            </div>
          </div>
          <div className="email-row-wrapper-registration">
            <div className="email-label">
              <label htmlFor="id-email">E-mail address:</label>{" "}
            </div>
            <div className="email-input">
              <input
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
          <input
            disabled={isDisabled}
            className="submit-input-registartion"
            type="submit"
            value="Register"
          />
        </div>
      </form>
    </div>
  );
}
