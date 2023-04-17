import React, {useState} from 'react';
import axios from 'axios';
const API_URL = process.env.REACT_APP_API_URL;

function DetailUpdate() {
  const [id, setId] = useState('');
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

  const handleIdChange = (event) => {
    setId(event.target.value);
  };
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
  const handleLastUpdateChange = (event) => {
    setLastUpdate(event.target.value);
  };
  const handleStartTimeChange = (event) => {
    setStartTime(event.target.value);
  };
  const handleEndTimeChange = (event) => {
    setEndTime(event.target.value);
  };

  const handleUpdateDetail = () => {
    const newDetail = generateNewDetail(id, date, duration,distance, calories, steps, lastUpdate,startTime, endTime, category);
    console.log(newDetail)
    axios
    .put(`${API_URL}/update/dailyActivityDetailById?_id=${id}`, newDetail)
    .then((response) => {
      // Assuming the API returns the new object with an ID assigned, add it to the existing list
      setDetails([...details, response.data]);
    })
    .catch((error) => console.log("Error updating object"));
  };

  const generateNewDetail = (id, date, duration,distance, calories, steps, lastUpdate,startTime, endTime, category) => {
    const newDetail = {
      _id:id,
      date: date,
      duration: duration,
      distance: distance,
      calories: calories,
      steps: steps,
      lastUpdate: lastUpdate,
      startTime: startTime,
      endTime: endTime,
      category: category
    };
    return newDetail;
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
        <ul>
        </ul>

        <div className="col-md-6">
          <div className="form-group">
            <label> Update an activity record to your database </label>
            <form className="col-md-6">
              <input
                  placeholder="Id"
                  type="text"
                  className="form-control"
                  value={id}
                  onChange={handleIdChange}
              />
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
              <input
                  placeholder="LastUpdate"
                  type="text"
                  className="form-control"
                  value={lastUpdate}
                  onChange={handleLastUpdateChange}
              />
              <input
                  placeholder="StartTime"
                  type="text"
                  className="form-control"
                  value={startTime}
                  onChange={handleStartTimeChange}
              />
              <input
                  placeholder="EndTime"
                  type="text"
                  className="form-control"
                  value={endTime}
                  onChange={handleEndTimeChange}
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
          <button className="btn btn-primary" onClick={handleUpdateDetail}>
            Update this Detail Record
          </button>
        </div>
      </div>
  )
}

export default DetailUpdate;