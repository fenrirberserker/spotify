package com.api.spotify.repository;

import com.api.spotify.domain.entity.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository extends MongoRepository<Track, String> {
    Optional<Track> findByIsrc(String isrc);

}
