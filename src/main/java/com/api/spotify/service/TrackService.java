package com.api.spotify.service;

import com.api.spotify.domain.dto.TrackDTO;
import com.api.spotify.domain.entity.Track;
import com.api.spotify.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    private final SpotifyService spotifyService;

    @Transactional
    public Track saveTrack(TrackDTO track) {
        //fetch metadata from spotify
        System.out.println("Fetching metadata for ISRC: " + track.isrc());
        TrackDTO meta = spotifyService.getTrackMetadata(track.isrc());
        System.out.println("Metadata fetched from Spotify: " + meta);

        Track trackFromSpotify = new Track();
        trackFromSpotify.setIsrc(track.isrc());
        trackFromSpotify.setName(meta.name());
        trackFromSpotify.setArtistName(meta.artistName());
        trackFromSpotify.setAlbumName(meta.albumName());
        trackFromSpotify.setAlbumId(meta.albumId());
        trackFromSpotify.setExplicit(meta.isExplicit());

        return trackRepository.save(trackFromSpotify);
    }

    public Optional<TrackDTO> findByIsrc(String isrc) {

        TrackDTO trackDTO = trackRepository.findByIsrc(isrc)
                .map(track -> new TrackDTO(
                        track.getIsrc(),
                        track.getName(),
                        track.getArtistName(),
                        track.getAlbumName(),
                        track.getAlbumId(),
                        track.isExplicit(),
                        track.getPlaybackSeconds()
                )).orElse(null);

        return Optional.of(trackDTO);
    }

   /* public Optional<TrackDTO> findCover(String isrc) {

        TrackDTO trackDTO = trackRepository.findByIsrc(isrc).stream().findFirst().get().get
                .map(track -> new TrackDTO(
                        track.getIsrc(),
                        track.getName(),
                        track.getArtistName(),
                        track.getAlbumName(),
                        track.getAlbumId(),
                        track.isExplicit(),
                        track.getPlaybackSeconds()
                )).orElse(null);

        return Optional.of(trackDTO);
    }*/

}
