import { Link } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
    return (
        <nav className="navbar">
            <div className="nav-brand">
                <Link to="/">Spotify Track Manager</Link>
            </div>
            <div className="nav-links">
                <Link to="/" className="nav-link">Home</Link>
                <Link to="/create-track" className="nav-link">Create Track</Link>
                <Link to="/track-metadata" className="nav-link">Track Metadata</Link>
            </div>
        </nav>
    );
};

export default Navbar;
