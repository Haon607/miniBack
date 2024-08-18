package org.example.wlback.trollers;

import org.example.wlback.entities.Game;
import org.example.wlback.entities.Player;
import org.example.wlback.entities.questions.Answer;
import org.example.wlback.entities.questions.QuestionFirst;
import org.example.wlback.repos.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerController playerController;

    @PostMapping
    public ResponseEntity<Game> openGame() {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(new Game(null, null, "/idle", null, List.of(new QuestionFirst(null, "Question", List.of(
                new Answer(null, "Correct1", true, Byte.parseByte("1")),
                new Answer(null, "Correct2", true, Byte.parseByte("2")),
                new Answer(null, "Correct3", true, Byte.parseByte("3")),
                new Answer(null, "Correct4", true, Byte.parseByte("4")),
                new Answer(null, "Correct5", true, Byte.parseByte("5")),
                new Answer(null, "Correct6", true, Byte.parseByte("6")),

                new Answer(null, "WrongL1", true, Byte.parseByte("1")),
                new Answer(null, "WrongL1", true, Byte.parseByte("1")),
                new Answer(null, "WrongL1", true, Byte.parseByte("1")),

                new Answer(null, "WrongL2", true, Byte.parseByte("2")),
                new Answer(null, "WrongL2", true, Byte.parseByte("2")),
                new Answer(null, "WrongL2", true, Byte.parseByte("2")),

                new Answer(null, "WrongL3", true, Byte.parseByte("3")),
                new Answer(null, "WrongL3", true, Byte.parseByte("3")),
                new Answer(null, "WrongL3", true, Byte.parseByte("3")),

                new Answer(null, "WrongL4", true, Byte.parseByte("4")),
                new Answer(null, "WrongL4", true, Byte.parseByte("4")),
                new Answer(null, "WrongL4", true, Byte.parseByte("4")),

                new Answer(null, "WrongL5", true, Byte.parseByte("5")),
                new Answer(null, "WrongL5", true, Byte.parseByte("5")),
                new Answer(null, "WrongL5", true, Byte.parseByte("5")),

                new Answer(null, "WrongL6", true, Byte.parseByte("6")),
                new Answer(null, "WrongL6", true, Byte.parseByte("6")),
                new Answer(null, "WrongL6", true, Byte.parseByte("6"))
        ))) /*debug*/)));
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
    public ResponseEntity<Game> setRouteData(@PathVariable Long id, @RequestBody String[] route_data) {
        Game game = gameRepository.findById(id).orElseThrow();
        game.setRoute(route_data[0]);
        game.setData(route_data[1]);
        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }
}
