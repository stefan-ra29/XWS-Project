import { NavLink } from "react-router-dom";
import "./Header.css";
import { getRoleFromLocalStorage } from "../../utils/LocalStorageService";

export default function Header() {
  console.log(localStorage.getItem("token"));
  const role = getRoleFromLocalStorage();
  return (
    <header>
      <nav>
        <h1>XWS Flights</h1>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/browse">Browse flights</NavLink>
        {localStorage.getItem("token") != null && (
          <a
            href="/"
            onClick={() => {
              localStorage.clear();
            }}
          >
            Logout
          </a>
        )}
        {localStorage.getItem("token") == null && (
          <NavLink to="/login">Login</NavLink>
        )}
        {localStorage.getItem("token") == null && (
          <NavLink to="/registration">Register</NavLink>
        )}
        {role == "BASIC_USER" && (
          <NavLink to="/ticket-history">Purchase history</NavLink>
        )}
        {role == "ADMINISTRATOR" && (
          <NavLink to="/flights">Flight managment</NavLink>
        )}
      </nav>
    </header>
  );
}
