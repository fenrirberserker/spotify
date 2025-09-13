import { useState } from 'react';

const TrackMetadata = () => {
    const [isrc, setIsrc] = useState('');
    const [trackData, setTrackData] = useState(null);

    const fetchTrackData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/codechallenge/getTrackMetadata?isrc=${isrc}`);
            if (response.ok) {
                const data = await response.json();
                setTrackData(data);
            }
        } catch (error) {
            console.error('Error fetching track:', error);
        }
    };

    return (
        <div className="track-metadata">
            <h2>Track Metadata</h2>
            <div>
                <input
                    type="text"
                    value={isrc}
                    onChange={(e) => setIsrc(e.target.value)}
                    placeholder="Enter ISRC"
                />
                <button onClick={fetchTrackData}>Search</button>
            </div>
            {trackData && (
                <div className="track-details">
                    <h3>{trackData.name}</h3>
                    <p>Artist: {trackData.artistName}</p>
                    <p>Album: {trackData.albumName}</p>
                    <p>ISRC: {trackData.isrc}</p>
                </div>
            )}
        </div>
    );
};

export default TrackMetadata;
