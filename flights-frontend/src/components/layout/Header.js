import {NavLink} from 'react-router-dom'
import './Header.css'


export default function Header() {
  return (
    <header>
        <nav>
          <h1>XWS Flights</h1>
          <NavLink to='/'>Home</NavLink>
          <NavLink to='/browse'>Browse flights</NavLink>
          <NavLink to='/flights'>Flight managment</NavLink>
        </nav>
    </header>
  )
}
