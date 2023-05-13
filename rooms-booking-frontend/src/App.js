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
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
