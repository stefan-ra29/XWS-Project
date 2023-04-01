import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Home from './pages/Home';
import BrowseFlights from './pages/BrowseFlights';
import Header from './components/layout/Header';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import FlightManagment from './pages/FlightManagment';
import NewFlight from './pages/NewFlight';

function App() {
  return (
    <BrowserRouter>
      <ToastContainer />
      <Header />
      <main>
        <Routes>
          <Route index element={<Home />} />
          <Route path='browse' element={<BrowseFlights />} />
          <Route path='flights' element={<FlightManagment />} />
          <Route path='new-flight' element={<NewFlight />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
