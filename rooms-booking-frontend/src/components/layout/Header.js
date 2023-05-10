import { NavLink } from "react-router-dom";
import "./Header.css";

export default function Header() {
  return (
    <header className="direction">
      <nav>
        <h1>Booking</h1>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/book">Book</NavLink>
        <NavLink to="/register">Register</NavLink>
        <NavLink to="/login">Log in</NavLink>
      </nav>
    </header>
  );
}
