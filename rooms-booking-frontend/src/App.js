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
import AccountManagement from "./pages/AccountManagement";
import GuestReservationRequests from "./pages/GuestReservationRequests";
import { CreateRoom } from "./pages/CreateRoom";
import { HostRooms } from "./pages/HostRooms";
import { RoomAvailabilities } from "./pages/RoomAvailabilities";
import { RoomPrices } from "./pages/RoomPrices";

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
          <Route path="account-management" element={<AccountManagement />} />
          <Route path="register" element={<Registration />} />
          <Route path="login" element={<Login />} />
          <Route
            path="guestReservationRequests"
            element={<GuestReservationRequests />}
          />
          <Route path="create-room" element={<CreateRoom />} />
          <Route path="my-rooms">
            <Route index element={<HostRooms />} />
            <Route path=":id/availabilities" element={<RoomAvailabilities />} />
            <Route path=":id/prices" element={<RoomPrices />} />
          </Route>
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
