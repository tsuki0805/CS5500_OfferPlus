import React, {useRef,useState} from "react";
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
  const totalRunning = useRef(0);
  const totalCycling = useRef(0);
  const totalWalking = useRef(0);


  const handleDateChange = (event) => {
    setDate(event.target.value);
  };

  const handleFetchByDate = () => {
    axios
    .get(`${API_URL}/getListOfSummaryByDate?date=${date}`)
    .then((response) => {
      const updatedSummaries = response.data.map((summary) => ({
        ...summary,
        date: date,
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
    totalRunning.current = 0;

    activitySummaries.forEach((summary) => {
      if (summary.category === 'running') {
        const newCal = summary.calories;
        totalRunning.current += newCal;
      }
    });

    return totalRunning.current;
  };

  const generateCyclingCalories = (activitySummaries) => {
    totalCycling.current = 0;

    activitySummaries.forEach((summary) => {
      if (summary.category === 'cycling') {
        const newCal = summary.calories;
        totalCycling.current += newCal;
      }
    });

    return totalCycling.current;
  };

  const generatewalkingCalories = (activitySummaries) => {
    totalWalking.current = 0;

    activitySummaries.forEach((summary) => {
      if (summary.category === 'walking') {
        const newCal = summary.calories;
        totalWalking.current += newCal;
      }
    });

    return totalWalking.current;
  };

  return (
      <div className="container">
        <div className="row">
          <div className="col-xxl-12">
            <div className="form-group">
              <label> Enter date in YYYYMMDD to get your calories comparison for physical activities on that date: </label>
              <input
                  type="text"
                  className="form-control"
                  value={date}
                  onChange={handleDateChange}
              />
            </div>
            <button className="btn btn-primary" onClick={handleFetchByDate}>
              Get physical activity summary calories comparison by date
            </button>
          </div>
        </div>
        {summaries.length > 0 ? (
            <div className="row mt-5">
              <div className="col-xxl-12 text-center">
                <h2>Physical Activity Summary Comparison in Calories</h2>
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
                <div className="mt-5">
                  <div>
                    <h2>Physical Activity Calories Comparison by Date</h2>
                    <p>Total running: {totalRunning.current} calories</p>
                    <p>Total cycling: {totalCycling.current} calories</p>
                    <p>Total walking: {totalWalking.current} calories</p>
                  </div>
                </div>
              </div>
            </div>
        ) : null}
      </div>
  );
}

export default DailyActivitySummaryComparison;