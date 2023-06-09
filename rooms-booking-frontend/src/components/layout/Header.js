import { NavLink } from "react-router-dom";
import "./Header.css";
import { getRoleFromLocalStorage } from "../../utils/LocalStorageService";
import { deleteAccount } from "../../service/UserService";

export default function Header() {
  const role = getRoleFromLocalStorage();

  function handleAccountDeletion() {
    if (window.confirm("Are you sure you want to delete your account?")) {
      deleteAccount();
    }
  }

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
        {localStorage.getItem("role") === "HOST" && (
          <NavLink to="/hostReservationRequests">Reservation requests</NavLink>
        )}
        {localStorage.getItem("role") === "GUEST" && (
          <NavLink to="/reservations">Reservations</NavLink>
        )}
        {localStorage.getItem("role") === "HOST" && (
          <NavLink to="/create-room">Create room</NavLink>
        )}
        {localStorage.getItem("role") === "HOST" && (
          <NavLink to="/my-rooms">My rooms</NavLink>
        )}
        {localStorage.getItem("token") != null && (
          <NavLink to="/account-management">Account</NavLink>
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
      </nav>
    </header>
  );
}
