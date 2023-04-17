import React, {useState} from 'react';
import axios from 'axios';
const API_URL = process.env.REACT_APP_API_URL;

function DetailDelete() {
  const [date, setDate] = useState('');
  const [category, setCategory] = useState('');
  const [duration, setDuration] = useState('');
  const [distance, setDistance] = useState('');
  const [calories, setCalories] = useState('');
  const [steps, setSteps] = useState('');
  const [lastUpdate, setLastUpdate] = useState('');
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const [details, setDetails] = useState([]);

  const handleDateChange = (event) => {
    setDate(event.target.value);
  };


  const handleDeleteByDate = () => {
    axios
    .delete(`${API_URL}/delete/dailyActivityDetailByDate?date=${date}`)
    .then((response) => {
      const updatedDetails = response.data.map((detail) => ({
        ...detail,
        startTime: convertToReadableTime(detail.startTime),
        endTime: convertToReadableTime(detail.endTime),
      }));
      setDetails(updatedDetails);
    })
    .catch((error) => console.log("Error in fetch by date"));
  };


  const convertToReadableTime = (timestamp) => {
    const year = timestamp.slice(0, 4);
    const month = timestamp.slice(4, 6);
    const day = timestamp.slice(6, 8);
    const hours = timestamp.slice(9, 11);
    const minutes = timestamp.slice(11, 13);
    const seconds = timestamp.slice(13, 15);
    const timezoneOffset = timestamp.slice(15);
    const date = new Date(`${year}-${month}-${day}T${hours}:${minutes}:${seconds}${timezoneOffset}`);
    return date.toLocaleString();
  };

  return(
      <div className="container">
        <div className="col-md-6">
          <div className="form-group">
            <label> Enter date in YYYYMMDD to delete your activity details on that date: </label>
            <input
                type="text"
                className="form-control"
                value={date}
                onChange={handleDateChange}
            />
          </div>
          <button className="btn btn-primary" onClick={handleDeleteByDate}>
            Delete Details by Date
          </button>
        </div>

      </div>
  )
}

export default DetailDelete;