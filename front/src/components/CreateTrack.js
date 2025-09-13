import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const CreateTrack = () => {
    const navigate = useNavigate();
    const [track, setTrack] = useState({
        isrc: '',
        name: '',
        artistName: '',
        albumName: '',
        albumId: '',
        isExplicit: false
    });

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/codechallenge/createTrack?isrc=${track.isrc}`, {
                method: 'POST'
            });

            if (response.ok) {
                navigate('/track-metadata');
            }
        } catch (error) {
            console.error('Error creating track:', error);
        }
    };

    return (
        <div className="create-track">
            <h2>Create New Track</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>ISRC:</label>
                    <input
                        type="text"
                        value={track.isrc}
                        onChange={(e) => setTrack({...track, isrc: e.target.value})}
                    />
                </div>
                <button type="submit">Create Track</button>
            </form>
        </div>
    );
};

export default CreateTrack;
