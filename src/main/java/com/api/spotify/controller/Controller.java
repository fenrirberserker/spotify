package com.api.spotify.controller;

import com.api.spotify.domain.dto.TrackDTO;
import com.api.spotify.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codechallenge")
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

    @Autowired
    TrackService trackService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/createTrack")
    public void createTrack(@RequestParam String isrc) {

        TrackDTO track =
                new TrackDTO(isrc,
                        "Track Name",
                        "Artist Name",
                        "Album Name",
                        "Album ID",
                        false,
                        180);
        trackService.saveTrack(track);
    }

    @GetMapping("/getTrackMetadata")
    public TrackDTO getTrackMetadata(@RequestParam String isrc) {
       return trackService.findByIsrc(isrc).orElseThrow(() -> new RuntimeException("Track not found"));


    }

    @GetMapping("/getCover")
    public void getCover(@RequestParam String isrc) {

    }
}
