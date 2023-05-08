import React from 'react'
import './RoomSearchForm.css'

export default function RoomSearchForm({handleSubmit, searchQuery, handleInputChange}) {
    const getValidFromDate = () => {
        const today = new Date();
        const dd = String(today.getDate()).padStart(2, "0");
        const mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
        const yyyy = today.getFullYear();
        return yyyy + "-" + mm + "-" + dd;
    };

    const getValidToDate = () => {
        const today = new Date();
        const dd = String(today.getDate() + 1).padStart(2, "0");
        const mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
        const yyyy = today.getFullYear();
        return yyyy + "-" + mm + "-" + dd;
    };

  return (
    <form className='searchForm' onSubmit={handleSubmit}>
        <label htmlFor='location'>Location:</label>
        <input name='location' id='location' type='text' value={searchQuery.location} onChange={handleInputChange}></input>

        <label htmlFor='dateFrom'>From:</label>
        <input type='date' name='dateFrom' id='dateFrom' min={getValidFromDate()} value={searchQuery.fromDate} onChange={handleInputChange}></input>

        <label htmlFor='dateTo'>To:</label>
        <input type='date' name='dateTo' id='dateTo' min={getValidToDate()} value={searchQuery.toDate} onChange={handleInputChange}></input>

        <label htmlFor='numberOfGuests'>Number of guests:</label>
        <input type='number' name='numberOfGuests' id='numberOfGuests' min='1' step='1' value={searchQuery.numberOfGuests} onChange={handleInputChange}></input>

        <input className='searchButton' type='submit' value='Search!'></input>
    </form>
  )
}
