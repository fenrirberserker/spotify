package com.api.spotify.controller;

import com.api.spotify.domain.dto.TrackDTO;
import com.api.spotify.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.specification.Track;

@RestController
@RequestMapping("/api/spotify")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SpotifyController {

    private final SpotifyService spotifyService;

    @GetMapping("/search")
    public Track[] searchTracks(@RequestParam String query) {
        return spotifyService.searchTracks(query);
    }

    @GetMapping("/metadata")
    public TrackDTO getTrackMetadata(@RequestParam String query) {
        return spotifyService.getTrackMetadata(query);
    }

    @GetMapping("/cover")
    public String getCover(@RequestParam String query) {
        //TODO
        // Extract uri path from json
        // Extract image from uri path

        String response = spotifyService.getAlbum(query).toString();
        return response;
    }
    
    
}