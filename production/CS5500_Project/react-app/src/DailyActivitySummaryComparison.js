import React, {useState} from "react";
import axios from "axios";
import {
  Cell,
  Legend,
  Pie,
  PieChart,
  ResponsiveContainer,
  Tooltip
} from "recharts";

const API_URL = process.env.REACT_APP_API_URL;

function DailyActivitySummaryComparison() {
  const [date, setDate] = useState('');
  const [summaries, setSummary] = useState([]);
  const [chartData, setChartData] = useState([]);

  const handleDateChange = (event) => {
    setDate(event.target.value);
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

  const handleFetchByDate = () => {
    axios
    .get(`${API_URL}/getListOfSummaryByDate?date=${date}`)
    .then((response) => {
      const updatedSummaries = response.data.map((summary) => ({
        ...summary,
        startTime: convertToReadableTime(summary.startTime),
        endTime: convertToReadableTime(summary.endTime),
      }));
      setSummary(updatedSummaries);
      const newChartData = generateChartData(updatedSummaries);
      setChartData(newChartData);
    })
    .catch((error) => console.log(error));
  };

  const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

  const generateChartData = (activitySummaries) => {

    const runValue = generateRunningCalories(activitySummaries);
    const cycleValue = generateCyclingCalories(activitySummaries);
    const walkValue = generatewalkingCalories(activitySummaries);

    return [
      {name: 'running', value: runValue},
      {name: 'cycling', value: cycleValue},
      {name: 'walking', value: walkValue},
    ];
  }


  const generateRunningCalories = (activitySummaries) => {
    let runningCalories = 0

    activitySummaries.forEach((summary) => {
      if (summary.category === 'running') {
        const newCal = summary.calories;
        runningCalories += newCal;
      }
    });

    return runningCalories;
  };

  const generateCyclingCalories = (activitySummaries) => {
    let cyclingCalories = 0

    activitySummaries.forEach((summary) => {
      if (summary.category === 'cycling') {
        const newCal = summary.calories;
        cyclingCalories += newCal;
      }
    });

    return cyclingCalories;
  };

  const generatewalkingCalories = (activitySummaries) => {
    let walkingCalories = 0

    activitySummaries.forEach((summary) => {
      if (summary.category === 'walking') {
        const newCal = summary.calories;
        walkingCalories += newCal;
      }
    });

    return walkingCalories;
  };

  return (
      <div className="container">
        <div className="row">
          <div className="col-xxl-12">
            <div className="form-group">
              <label> Enter date in YYYYMMDD to get your calories comparison for activities on that date: </label>
              <input
                  type="text"
                  className="form-control"
                  value={date}
                  onChange={handleDateChange}
              />
            </div>
            <button className="btn btn-primary" onClick={handleFetchByDate}>
              Get calories comparison by date
            </button>
          </div>
        </div>
        {summaries.length > 0 ? (
            <div className="row mt-5">
              <div className="col-xxl-12 text-center">
                <h2>Activity Summary Comparison in Calories</h2>
                <ResponsiveContainer width="100%" height={400}>
                  <PieChart width={400} height={400}>
                    <Pie
                        data={chartData}
                        fill="#8884d8"
                        dataKey="value"
                    >
                      {chartData.map((entry, index) => (
                          <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                      ))}
                    </Pie>
                    <Tooltip />
                    <Legend />
                  </PieChart>
                </ResponsiveContainer>
              </div>
            </div>
        ) : null}
      </div>
  );
}

export default DailyActivitySummaryComparison;