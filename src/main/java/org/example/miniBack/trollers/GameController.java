package org.example.miniBack.trollers;

import org.example.miniBack.entities.Game;
import org.example.miniBack.entities.Player;
import org.example.miniBack.entities.Round;
import org.example.miniBack.repos.GameRepository;
import org.example.miniBack.repos.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RoundRepository roundRepository;
    @Autowired
    private PlayerController playerController;

    @PostMapping
    public ResponseEntity<Game> openGame() {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(new Game(null, null, "/initial", null, null)));
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

    @PostMapping("/{id}/rounds/{amount}/{amountLargeRounds}")
    public ResponseEntity<Game> addRound(@PathVariable Long id, @PathVariable Integer amount, @PathVariable Integer amountLargeRounds) {
        Game game = gameRepository.findById(id).orElseThrow();

        List<Round> rounds = new ArrayList<>();

        List<Round> allRound = roundRepository.findAll();

        List<Round> smallRounds = allRound.stream().filter(round -> !round.getLarge()).toList();
        List<Round> largeRounds = allRound.stream().filter(Round::getLarge).toList();

        Collections.shuffle(smallRounds);
        Collections.shuffle(largeRounds);

        for (int i = 0; i < amountLargeRounds; i++) {
            rounds.add(largeRounds.getFirst());
            largeRounds.removeFirst();
        }
        for (int i = rounds.size(); i < amount-1; i++) {
            rounds.add(smallRounds.getFirst());
            smallRounds.removeFirst();
        }

        Collections.shuffle(rounds);
        rounds.addFirst(smallRounds.getFirst());

        game.setRounds(rounds);

        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }
}
