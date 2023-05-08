import "./App.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/layout/Header";
import Home from "./pages/Home";
import Registration from "./pages/Registration";
import Booking from "./pages/Booking";
import RoomImages from "./components/booking/RoomImages";

function App() {
  return (
    <BrowserRouter>
      <ToastContainer />
      <Header />
      <main>
        <Routes>
          <Route index element={<Home />} />
          <Route path="register" element={<Registration />} />
          <Route path="book" element={<Booking />} />
          <Route path="roomImages" element={<RoomImages />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
