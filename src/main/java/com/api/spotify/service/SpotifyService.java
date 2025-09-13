package com.api.spotify.service;

import com.api.spotify.domain.dto.TrackDTO;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpEntity;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final SpotifyApi spotifyApi;

    public void authenticate() {
        try {
            ClientCredentialsRequest request = spotifyApi.clientCredentials().build();
            ClientCredentials credentials = request.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());
        } catch (Exception e) {
            throw new RuntimeException("Failed to authenticate with Spotify", e);
        }
    }

    public Track[] searchTracks(String query) {
        try {
            authenticate();
            SearchTracksRequest request = spotifyApi.searchTracks(query).limit(10).build();
            return request.execute().getItems();
        } catch (Exception e) {
            throw new RuntimeException("Failed to search tracks", e);
        }
    }

    public TrackDTO getTrackMetadata(String id) {
        try {
            authenticate();
            System.out.println("Fetching metadata for track ID: " + id);
            GetTrackRequest request = spotifyApi.getTrack(id).build();
            System.out.println("Metadata built: " + request.getJson());
            TrackDTO trackDTO = new TrackDTO(
                    request.execute().getId(),
                    request.execute().getName(),
                    request.execute().getArtists()[0].getName(),
                    request.execute().getAlbum().getName(),
                    request.execute().getAlbum().getId(),
                    request.execute().getIsExplicit(),
                    request.execute().getDurationMs() / 1000
            );

            return trackDTO;
        } catch (Exception e) {
            throw new RuntimeException("Failed find track metadata", e);
        }
    }

    public String getAlbum(String query) {
        try {
            authenticate();
            String response = spotifyApi.searchTracks(query).limit(1).build().getJson();
            System.out.println("Album response: " + response);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to search album", e);
        }
    }
}