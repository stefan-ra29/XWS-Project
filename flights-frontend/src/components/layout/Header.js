import { NavLink } from "react-router-dom";
import "./Header.css";

export default function Header() {
  return (
    <header>
      <nav>
        <h1>XWS Flights</h1>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/browse">Browse flights</NavLink>
        {localStorage.getItem("token") != null && (
          <a href="/" onClick={() => localStorage.removeItem("token")}>
            Logout
          </a>
        )}
        {localStorage.getItem("token") == null && (
          <NavLink to="/login">Login</NavLink>
        )}
        {localStorage.getItem("token") == null && (
          <NavLink to="/registration">Register</NavLink>
        )}
        <NavLink to="/ticket-history">Purchase history</NavLink>
      </nav>
    </header>
  );
}
