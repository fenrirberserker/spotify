import React from 'react';
import './App.css';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './components/Home';
import CreateTrack from './components/CreateTrack';
import TrackMetadata from './components/TrackMetadata';

function App() {
  return (
      <Router>
        <div className="App">
          <Navbar />
          <div className="content">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/create-track" element={<CreateTrack />} />
              <Route path="/track-metadata" element={<TrackMetadata />} />
            </Routes>
          </div>
        </div>
      </Router>
  );
}

export default App;
