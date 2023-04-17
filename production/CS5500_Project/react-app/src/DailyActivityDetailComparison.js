import React, {useRef, useState} from "react";
import axios from "axios";
import {PieChart, Pie, Cell, ResponsiveContainer, Tooltip, Legend} from "recharts";
const API_URL = process.env.REACT_APP_API_URL;

function DailyActivityDetailComparison() {
    const [date, setDate] = useState('');
    const [details, setDetails] = useState([]);
    const [chartData, setChartData] = useState([]);
    const totalCo2EmissionsRef = useRef(0);

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
            .get(`${API_URL}/dailyActivityDetailByDate?date=${date}`)
            .then((response) => {
                const updatedDetails = response.data.map((detail) => ({
                    ...detail,
                    startTime: convertToReadableTime(detail.startTime),
                    endTime: convertToReadableTime(detail.endTime),
                }));
                setDetails(updatedDetails);
                const newChartData = generateChartData(updatedDetails);
                setChartData(newChartData);
            })
            .catch((error) => console.log(error));
    };

    const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

    const generateChartData = (activityDetails) => {
        const data = [
            { name: 'running', value: 0 },
            { name: 'cycling', value: 0 },
            { name: 'transport', value: 0 },
            { name: 'walking', value: 0 },
        ];

        totalCo2EmissionsRef.current = 0;

        activityDetails.forEach((detail) => {
            if (detail.category === 'transport') {
                const newEmission = calculateCarCo2Emissions(detail.distance);
                totalCo2EmissionsRef.current += newEmission;
            }

            data.find((item) => item.name === detail.category).value += detail.distance;
        });

        return data;
    };

    const calculateCarCo2Emissions = (distance) => {
        // Assumes an average emission rate of 0.18 kg CO2 per km for gasoline cars
        const emissionRate = 0.18;
        const carCo2Emissions = distance * emissionRate;
        return carCo2Emissions;
    };

    const calculateTreesNeeded = (totalCo2EmissionsRef) => {
        // Assumes an average carbon sequestration rate of 48 pounds (21.77 kg) of CO2 per tree per year
        const sequestrationRate = 21.77;
        const treesNeeded = Math.ceil(totalCo2EmissionsRef.current / sequestrationRate);
        return treesNeeded;
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-xxl-12">
                    <div className="form-group">
                        <label> Enter date in YYYYMMDD to get your distance comparison for activities on that date: </label>
                        <input
                            type="text"
                            className="form-control"
                            value={date}
                            onChange={handleDateChange}
                        />
                    </div>
                    <button className="btn btn-primary" onClick={handleFetchByDate}>
                        Get comparison by date
                    </button>
                </div>
            </div>
            {details.length > 0 ? (
                <div className="row mt-5">
                    <div className="col-xxl-12 text-center">
                        <h2>Activity Details Comparison in Distance</h2>
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
                                <h2>CO2 Emissions Today If Transport by Car</h2>
                                <p>Total CO2 emissions: {totalCo2EmissionsRef.current.toFixed(2)} kg</p>
                                <p>Number of trees needed to neutralize CO2 emissions: {calculateTreesNeeded(totalCo2EmissionsRef).toFixed(2)}</p>
                            </div>
                        </div>
                    </div>
                </div>
            ) : null}
        </div>

    );
}

export default DailyActivityDetailComparison;