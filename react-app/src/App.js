import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import DailyActivityDetail from './DailyActivityDetailApi';
import DailyActivityDetailComparison from './DailyActivityDetailComparison';
import DetailEdit from './DetailEdit';
import DailyActivitySummary from "./DailyActivitySummaryApi";
import DailyActivitySummaryComparison from "./DailyActivitySummaryComparison";
import SummaryEdit from './SummaryEdit';

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
                                    Get Your Daily Activities Details
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/comparison">
                                    Distance Comparsion&Environmental Impact
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/edit-detail">
                                    Edit Detail Info
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/summaries">
                                    Get your Daily Activity Summaries
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/calories">
                                    Daily Activity Summaries Calories Comparison
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/edit-summary">
                                    Edit Summary Info
                                </Link>
                            </li>
                        </ul>
                    </div>
                </nav>

                <div className="container">
                    <Routes>
                        <Route exact path="/details" element={<DailyActivityDetail />} />
                        <Route exact path="/comparison" element={<DailyActivityDetailComparison />} />
                        <Route exact path="/edit-detail" element={<DetailEdit />} />
                        <Route exact path="/edit-summary" element={<SummaryEdit />} />
                        <Route exact path="/summaries" element={<DailyActivitySummary />} />
                        <Route exact path="/calories" element={<DailyActivitySummaryComparison />} />
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;
