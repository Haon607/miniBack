package org.example.wlback.trollers;

import org.example.wlback.entities.Game;
import org.example.wlback.entities.Player;
import org.example.wlback.repos.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerController playerController;

    @PostMapping
    public ResponseEntity<Game> openGame() {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(new Game()));
    }

    @GetMapping("/{id}") //Pseudo Websocket
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameRepository.findById(id).orElse(null));
    }

    @PutMapping("/{id}/{pId}")
    public ResponseEntity<Game> addPlayerToGame(@PathVariable Long id, @PathVariable Long pId) {
        Game game = gameRepository.findById(id).orElseThrow();
        Player player = playerController.getPlayer(pId).getBody();
        game.getPlayers().add(player);
        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> setData(@PathVariable Long id, @RequestBody String data) {
        Game game = gameRepository.findById(id).orElseThrow();
        game.setData(data);
        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }
}
