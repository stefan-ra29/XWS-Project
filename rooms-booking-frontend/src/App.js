import "./App.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/layout/Header";
import Home from "./pages/Home";
import Registration from "./pages/Registration";
import Booking from "./pages/Booking";
import RoomImages from "./components/booking/RoomImages";
import Login from "./pages/Login";
import GuestReservationRequests from "./pages/GuestReservationRequests";
import { CreateRoom } from "./pages/CreateRoom";
import GuestApprovedReservations from "./pages/GuestApprovedReservations";

function App() {
  return (
    <BrowserRouter>
      <ToastContainer />
      <Header />
      <main>
        <Routes>
          <Route index element={<Home />} />
          <Route path="book" element={<Booking />} />
          <Route path="roomImages" element={<RoomImages />} />
          <Route path="register" element={<Registration />} />
          <Route path="login" element={<Login />} />
          <Route path="guestReservationRequests" element={<GuestReservationRequests />} />
          <Route path="reservations" element={<GuestApprovedReservations />} />
          <Route path="create-room" element={<CreateRoom />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
