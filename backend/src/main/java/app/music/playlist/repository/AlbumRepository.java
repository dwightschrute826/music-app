package app.music.playlist.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.music.playlist.model.Album;
import jakarta.persistence.OrderBy;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {

    Optional<Album> findAlbumByTitle(String title);

    @OrderBy("title")
    List<Album> findAlbumsByOrderByTitleAsc();

    List<Album> findAlbumsByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(String query1, String query2);
}
