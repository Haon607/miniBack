package org.example.miniBack.trollers;

import org.example.miniBack.entities.Player;
import org.example.miniBack.repos.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity.ok().body(playerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            player.setId(id);
            return ResponseEntity.ok().body(playerRepository.save(player));
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @PostMapping("/{id}")
    public ResponseEntity<Player> setInput(@PathVariable Long id, @RequestBody String input) {
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            Player player = existingPlayer.get();
            player.setInput(input);
            playerRepository.save(player);
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> setColor(@PathVariable Long id, @RequestBody String color) {
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            Player player = existingPlayer.get();
            player.setColor(color);
            playerRepository.save(player);
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<List<Player>> deleteInputs() {
        List<Player> x = playerRepository.findAll();
        x.forEach(player -> player.setInput(""));
        return ResponseEntity.ok(playerRepository.saveAll(x));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deleteInput(@PathVariable Long id) {
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            Player player = existingPlayer.get();
            player.setInput("");
            playerRepository.save(player);
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return ResponseEntity.ok().body(playerRepository.save(player));
    }
}
