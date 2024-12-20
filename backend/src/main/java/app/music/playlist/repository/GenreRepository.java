package app.music.playlist.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.music.playlist.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
