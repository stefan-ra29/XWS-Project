import "./styles/AccountManagement.css";
import { useEffect, useState } from "react";
import { getUser } from "../service/UserService";
import {
  getTokenFromLocalStorage,
  getIdFromLocalStorage,
} from "../utils/LocalStorageService";
import { useNavigate } from "react-router-dom";
import { deleteAccount } from "../service/UserService";
import { changeUserInfo } from "../service/UserService";

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
    role: "",
    id: "",
  });

  const [address, setAddress] = useState({
    country: "",
    city: "",
    street: "",
    streetNumber: "",
  });
  const [isChangeActivated, setIsChangeActivated] = useState(false);
  const [isDeleteButtonDisabled, setIsDeleteButtonDisabled] = useState(false);

  useEffect(() => {
    if (token === null) {
      navigate("/");
    }
    getUser(getIdFromLocalStorage(), setUser, setAddress);
  }, []);

  const onChange = (event) => {
    event.preventDefault();
    if (isChangeActivated) {
      user.address = address;
      setUser((values) => ({
        ...values,
        values: user,
      }));
      changeUserInfo(
        user,
        setIsChangeActivated,
        setIsDeleteButtonDisabled,
        navigate
      );
    } else {
      setIsChangeActivated(!isChangeActivated);
      setIsDeleteButtonDisabled(!isDeleteButtonDisabled);
    }
  };

  const handleAccountDeletion = (event) => {
    event.preventDefault();
    if (window.confirm("Are you sure you want to delete your account?")) {
      deleteAccount();
    }
  };

  const handleUserInfoChange = (event) => {
    const name = event.name;
    const value = event.value;
    setUser((values) => ({ ...values, [name]: value }));
    validateInputFields(event.id, value);
  };

  const handleAddressInfoChange = (event) => {
    const name = event.name;
    const value = event.value;
    setAddress((values) => ({ ...values, [name]: value }));
    validateInputFields(event.id, value);
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
                disabled={!isChangeActivated}
                value={user.firstName}
                name="firstName"
                onChange={(e) => handleUserInfoChange(e.target)}
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
                onChange={(e) => handleUserInfoChange(e.target)}
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
                value={address.country}
                name="country"
                onChange={(e) => handleAddressInfoChange(e.target)}
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
                value={address.city}
                name="city"
                onChange={(e) => handleAddressInfoChange(e.target)}
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
                value={address.street}
                name="street"
                onChange={(e) => handleAddressInfoChange(e.target)}
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
                value={address.streetNumber}
                name="streetNumber"
                onChange={(e) => handleAddressInfoChange(e.target)}
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
                disabled={!isChangeActivated}
                value={user.password}
                onChange={(e) => handleUserInfoChange(e.target)}
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
                onChange={(e) => handleUserInfoChange(e.target)}
              />
            </div>
          </div>
        </div>
        <div className="row-wrapper-change-button-account">
          {!isChangeActivated && (
            <button
              className="change-button-account"
              onClick={(e) => onChange(e)}
            >
              Change account information
            </button>
          )}
          {isChangeActivated && (
            <button
              disabled={isDisabled}
              className="change-button-account"
              onClick={(e) => onChange(e)}
            >
              Submit
            </button>
          )}
          <button
            className="delete-button-account"
            disabled={isDeleteButtonDisabled}
            onClick={(e) => handleAccountDeletion(e)}
          >
            Delete account
          </button>
        </div>
      </form>
    </div>
  );
}
