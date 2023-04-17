import React, {useState} from 'react';
import axios from 'axios';
const API_URL = process.env.REACT_APP_API_URL;

function DailyActivitySummary() {
  const [date1, setDate1] = useState('');
  const [date2, setDate2] = useState('');

  const [category, setCategory] = useState('');
  const [summaries, setSummary] = useState([]);

  const handleDate1Change = (event) => {
    setDate1(event.target.value);
  };

  const handleCategoryChange = (event) => {
    setCategory(event.target.value);
  };

  const handleDate2Change = (event) => {
    setDate2(event.target.value);
  };

  const handleFetchByDate = () => {
    axios
    .get(`${API_URL}/getListOfSummaryByDate?date=${date1}`)
    .then((response) => {
      const updatedSummary = response.data.map((summary) => ({
        ...summary,
        date: date1,
      }));
      setSummary(updatedSummary);
    })
    .then(data => console.log(data))
    .catch((error) => console.log("Error in fetch by date"));
  };

  const handleFetchByDateAndCat = () => {
    axios
    .get(`${API_URL}/getDailyCaloriesSumByDateAndCat?date=${date2}&category=${category}`)
    .then((response) => {
      const sortedSummary = response.data.sort((a, b) => b.date.localeCompare(a.date));
      const limitedSummary = sortedSummary.slice(0, 20).map((summary) => ({
        ...summary,
        date: date2,
      }));
      setSummary(limitedSummary);
    })
    .catch((error) => console.log("Error in fetch by date and category"));
  };

  return (
      <div className="container">
        <div className="row">
          <div className="col-md-6">
            <div className="form-group">
              <label> Enter date in YYYYMMDD to get your activity summary on that date: </label>
              <input
                  type="text"
                  className="form-control"
                  value={date1}
                  onChange={handleDate1Change}
              />
            </div>
            <button className="btn btn-primary" onClick={handleFetchByDate}>
              Get summary by date
            </button>
          </div>
          <div className="col-md-6">
            <div className="form-group">
              <label> Select from the date & categories to get your 20 most recent activity summaries: </label>
              <input
                  type="text"
                  className="form-control"
                  value={date2}
                  onChange={handleDate2Change}
              />
              <select
                  className="form-control"
                  value={category}
                  onChange={handleCategoryChange}
              >
                <option value="">Select Category</option>
                <option value="walking">Walking</option>
                <option value="running">Running</option>
                <option value="cycling">Cycling</option>
                <option value="transport">Transport</option>
              </select>
            </div>
            <button className="btn btn-primary" onClick={handleFetchByDateAndCat}>
              Get summary by date & category
            </button>
          </div>
        </div>

        {summaries.length > 0 ? (
            <div className="row">
              <div className="col-md-12">
                <table className="table">
                  <thead>
                  <tr>
                    <th>Date</th>
                    <th>Category</th>
                    <th>Calories</th>
                    <th>Distance</th>
                    <th>Duration</th>
                    <th>Steps</th>
                  </tr>
                  </thead>
                  <tbody>
                  {summaries.map((summary) => (
                      <tr key={summary.id}>
                        <td>{summary.date}</td>
                        <td>{summary.category}</td>
                        <td>{summary.calories}</td>
                        <td>{summary.distance}</td>
                        <td>{summary.duration}</td>
                        <td>{summary.steps}</td>
                      </tr>
                  ))}
                  </tbody>
                </table>
              </div>
            </div>
        ) : null}
      </div>

  );
}

export default DailyActivitySummary;