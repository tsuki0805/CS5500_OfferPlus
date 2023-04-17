import React, {useState} from 'react';
import axios from 'axios';
const API_URL = process.env.REACT_APP_API_URL;

function DailyActivitySummary() {
  const [date, setDate] = useState('');
  const [category, setCategory] = useState('');
  const [duration, setDuration] = useState('');
  const [distance, setDistance] = useState('');
  const [calories, setCalories] = useState('');
  const [steps, setSteps] = useState('');
  const [summaries, setSummary] = useState([]);

  const handleDateChange = (event) => {
    setDate(event.target.value);
  };
  const handleCategoryChange = (event) => {
    setCategory(event.target.value);
  };
  const handleDurationChange = (event) => {
    setDuration(event.target.value);
  };
  const handleDistanceChange = (event) => {
    setDistance(event.target.value);
  };
  const handleCaloriesChange = (event) => {
    setCalories(event.target.value);
  };
  const handleStepsChange = (event) => {
    setSteps(event.target.value);
  };

  const handleAddSummary = () => {
    const newSummary = generateNewSummary(date, duration,distance, calories, steps, category);
    console.log(newSummary)
    axios
    .post(`${API_URL}/add/dailyActivitySummary`, newSummary)
    .then((response) => {
      setSummary([...summaries, response.data]);
    })
    .then(data => console.log(data))
    .catch((error) => console.log("Error adding object"));
  };

  const generateNewSummary = (date, duration,distance, calories, steps, category) => {
    const newDetail = {
      date: date,
      duration: duration,
      distance: distance,
      calories: calories,
      steps: steps,
      category: category
    };
    return newDetail;
  };

  return(
      <div className="container">

        <ul></ul>

        <div className="col-md-6">
          <div className="form-group">
            <label> Add an activity record to your database </label>

            <form className="col-md-6">
              <input
                  placeholder="Date"
                  type="text"
                  className="form-control"
                  value={date}
                  onChange={handleDateChange}
              />
              <input
                  placeholder="Duration"
                  type="number"
                  className="form-control"
                  value={duration}
                  onChange={handleDurationChange}
              />
              <input
                  placeholder="Distance"
                  type="number"
                  className="form-control"
                  value={distance}
                  onChange={handleDistanceChange}
              />
              <input
                  placeholder="Calories"
                  type="number"
                  className="form-control"
                  value={calories}
                  onChange={handleCaloriesChange}
              />
              <input
                  placeholder="Steps"
                  type="number"
                  className="form-control"
                  value={steps}
                  onChange={handleStepsChange}
              />
              <select
                  placeholder="Category"
                  type={category}
                  className="form-control"
                  onChange={handleCategoryChange}
              >
                <option value="">Category</option>
                <option value="walking">Walking</option>
                <option value="running">Running</option>
                <option value="cycling">Cycling</option>
                <option value="transport">Transport</option>
              </select>
            </form>
          </div>
          <button className="btn btn-primary" onClick={handleAddSummary}>
            Add this Summary Record
          </button>
        </div>
      </div>
  )


}

export default DailyActivitySummary;