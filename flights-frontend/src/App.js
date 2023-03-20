import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Home from './pages/Home';
import BrowseFlights from './pages/BrowseFlights';
import Header from './components/layout/Header';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <main>
        <Routes>
          <Route index element={<Home />} />
          <Route path='browse' element={<BrowseFlights />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
