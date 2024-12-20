package app.music.playlist.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.music.playlist.APIController;
import app.music.playlist.dto.SongDto;
import app.music.playlist.model.Album;
import app.music.playlist.model.Song;
import app.music.playlist.repository.AlbumRepository;
import app.music.playlist.repository.SongRepository;
import app.music.playlist.service.SongService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/song/")
public class SongController implements APIController<Song, UUID> {

    private final SongService service;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    @Autowired
    public SongController(SongService service, AlbumRepository albumRepository, SongRepository songRepository) {
        this.service = service;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @GetMapping("allSongs")
    @Override
    public List<Song> getAll() {
        return service.all();
    }

    @PostMapping("addSong")
    @Override
    public String add(@RequestBody Song song) {
        service.addSong(song);
        return "Song is successfully added!";
    }

    @PostMapping("/add")
public ResponseEntity<?> addSong(@RequestBody SongDto songDTO) {
    Album album = albumRepository.findById(UUID.fromString(songDTO.getAlbumId()))
            .orElseThrow(() -> new IllegalArgumentException("Invalid album ID"));

    Song song = new Song();
    song.setTitle(songDTO.getTitle());
    song.setAlbum(album); // Associate the album entity

    songRepository.save(song);

    return ResponseEntity.ok("Song added successfully!");
}


    @GetMapping("{id}")
    public Song getSongById(@PathVariable String id) {
        return service.getSongById(UUID.fromString(id));
    }

    @GetMapping("all")
    public List<Song> getSongsByAlbumId(@RequestParam UUID albumId) {

        if (albumId != null) {
            return service.getSongsByAlbumId(albumId);
        }

        return service.all();
    }

    @PutMapping("update/{id}")
    public String update(@PathVariable UUID id,
                         @RequestBody Song song) {

        service.updateSong(id, song);
        return "Song is successfully updated!";
    }

    @PostMapping("delete/{id}")
    @Override
    public String delete(@PathVariable UUID id) {
        service.deleteSong(id);
        return "Song is successfully deleted!";
    }
}
