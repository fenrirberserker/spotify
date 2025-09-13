package com.api.spotify.domain.dto;

public record TrackDTO(
        String isrc,
        String name,
        String artistName,
        String albumName,
        String albumId,
        boolean isExplicit,
        long playbackSeconds
) { }

