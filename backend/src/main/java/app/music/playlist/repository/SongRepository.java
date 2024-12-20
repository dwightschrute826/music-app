package app.music.playlist.repository;

import java.util.List;
import java.util.UUID;
import app.music.playlist.model.Song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {

    List<Song> findAllByAlbum_Id(UUID albumId);

}
