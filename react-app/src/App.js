import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import DailyActivityDetail from './DailyActivityDetailApi';
import DailyActivityDetailComparison from './DailyActivityDetailComparison';

function App() {
    return (
        <BrowserRouter>
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <span className="navbar-brand">5500 Fit!</span>
                    <div>
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <Link className="nav-link" to="/details">
                                    Daily Activity Detail
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/comparison">
                                    Daily Activity Distance Comparison & CO2 Emission
                                </Link>
                            </li>
                        </ul>
                    </div>
                </nav>

                <div className="container">
                    <Routes>
                        <Route exact path="/details" element={<DailyActivityDetail />} />
                        <Route exact path="/comparison" element={<DailyActivityDetailComparison />} />
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;
