import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import BrowseFlights from "./pages/BrowseFlights";
import Header from "./components/layout/Header";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Registration from "./pages/Registration";
import Login from "./pages/Login";

function App() {
  return (
    <BrowserRouter>
      <ToastContainer />
      <Header />
      <main>
        <Routes>
          <Route index element={<Home />} />
          <Route path="browse" element={<BrowseFlights />} />
          <Route path="registration" element={<Registration />} />
          <Route path="login" element={<Login />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
