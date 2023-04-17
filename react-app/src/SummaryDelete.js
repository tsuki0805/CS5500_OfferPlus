import React, {useState} from 'react';
import axios from 'axios';
const API_URL = process.env.REACT_APP_API_URL;

function DailyActivitySummary() {
  const [date, setDate] = useState('');
  const [category, setCategory] = useState('');
  const [summaries, setSummary] = useState([]);

  const handleDateChange = (event) => {
    setDate(event.target.value);
  };

  const handleDeleteByDate = () => {
    axios
    .delete(`${API_URL}/delete/dailyActivitySummaryByDate?date=${date}`)
    .then((response) => {
      const updatedSummary = response.data.map((summary) => ({
        ...summary,
        date: date,
      }));
      setSummary(updatedSummary);
    })
    .then(data => console.log(data))
    .catch((error) => console.log("Error in fetch by date"));
  };

  return(
      <div className="container">
        <div className="col-md-6">
          <div className="form-group">
            <label> Enter date in YYYYMMDD to delete your activity summaries on that date: </label>
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

export default DailyActivitySummary;