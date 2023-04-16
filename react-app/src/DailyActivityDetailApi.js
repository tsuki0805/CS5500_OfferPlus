import React, {useState} from 'react';
import axios from 'axios';
const API_URL = process.env.REACT_APP_API_URL;

function DailyActivityDetail() {
    const [date, setDate] = useState('');
    const [category, setCategory] = useState('');
    const [details, setDetails] = useState([]);

    const handleDateChange = (event) => {
        setDate(event.target.value);
    };

    const handleCategoryChange = (event) => {
        setCategory(event.target.value);
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
            })
            .catch((error) => console.log("Error in fetch by date"));
    };

    const handleFetchByCategory = () => {
        axios
            .get(`${API_URL}/dailyActivityDetailByCategory?category=${category}`)
            .then((response) => {
                const sortedDetails = response.data.sort((a, b) => b.date.localeCompare(a.date));
                const limitedDetails = sortedDetails.slice(0, 20).map((detail) => ({
                    ...detail,
                    startTime: convertToReadableTime(detail.startTime),
                    endTime: convertToReadableTime(detail.endTime),
                }));
                setDetails(limitedDetails);
            })
            .catch((error) => console.log("Error in fetch by category"));
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

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                        <label> Enter date in YYYYMMDD to get your activity details on that date: </label>
                        <input
                            type="text"
                            className="form-control"
                            value={date}
                            onChange={handleDateChange}
                        />
                    </div>
                    <button className="btn btn-primary" onClick={handleFetchByDate}>
                        Get detail by date
                    </button>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label> Select from the categories to get your 20 most recent activity details: </label>
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
                    <button className="btn btn-primary" onClick={handleFetchByCategory}>
                        Get detail by category
                    </button>
                </div>
            </div>
            {details.length > 0 ? (
                <div className="row">
                    <div className="col-md-12">
                        <table className="table">
                            <thead>
                            <tr>
                                <th>Date</th>
                                <th>Category</th>
                                <th>Calories</th>
                                <th>Distance</th>
                                <th>Start Time</th>
                                <th>End Time</th>
                                <th>Duration</th>
                                <th>Steps</th>
                            </tr>
                            </thead>
                            <tbody>
                            {details.map((detail) => (
                                <tr key={detail.id}>
                                    <td>{detail.date}</td>
                                    <td>{detail.category}</td>
                                    <td>{detail.calories}</td>
                                    <td>{detail.distance}</td>
                                    <td>{detail.startTime}</td>
                                    <td>{detail.endTime}</td>
                                    <td>{detail.duration}</td>
                                    <td>{detail.steps}</td>
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

export default DailyActivityDetail;