import { NavLink } from "react-router-dom";
import "./Header.css";
import { getRoleFromLocalStorage } from "../../utils/LocalStorageService";

export default function Header() {
  const role = getRoleFromLocalStorage();
  return (
    <header className="direction">
      <nav>
        <h1>Booking</h1>
        <NavLink to="/">Home</NavLink>
        {localStorage.getItem("role") === "GUEST" && (
          <NavLink to="/book">Book</NavLink>
        )}
        {localStorage.getItem("role") === "GUEST" && (
          <NavLink to="/guestReservationRequests">Reservation requests</NavLink>
        )}
        {localStorage.getItem("token") == null && (
          <NavLink to="/register">Register</NavLink>
        )}
        {localStorage.getItem("token") == null && (
          <NavLink to="/login">Log in</NavLink>
        )}
        {localStorage.getItem("token") != null && (
          <NavLink
            to="/"
            onClick={() => {
              localStorage.clear();
              window.location.replace("http://localhost:3000/");
            }}
          >
            Log out
          </NavLink>
        )}
        {localStorage.getItem("token") != null && (
          <NavLink
            to="/"
            onClick={() => {
              window.location.replace("http://localhost:3000/");
            }}
          >
            Deactivate account
          </NavLink>
        )}
        <NavLink to="/create-room">Create Room</NavLink>
      </nav>
    </header>
  );
}
