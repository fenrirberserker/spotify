package com.api.spotify.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "tracks")
public class Track {

    @Id
    private String id;
    private String isrc;
    private String name;
    private String artistName;
    private String albumName;
    private String albumId;
    private boolean isExplicit;
    private long playbackSeconds;
}
