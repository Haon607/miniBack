package org.example.miniBack.trollers;

import org.example.miniBack.entities.Game;
import org.example.miniBack.entities.Player;
import org.example.miniBack.entities.Question;
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

    @PostMapping("/{id}/rounds/{playerCount}/{gameAmount}/{amountLargeRounds}")
    public ResponseEntity<Game> addRound(@PathVariable Long id, @PathVariable Integer playerCount, @PathVariable Integer gameAmount, @PathVariable Integer amountLargeRounds) {
        Game game = gameRepository.findById(id).orElseThrow();

        List<Round> rounds = new ArrayList<>();
        List<Round> allRound = roundRepository.findAll();

        allRound = allRound.stream().filter(round ->
                        (round.getMinPlayerCount() == null || playerCount >= round.getMinPlayerCount()) &&
                                (round.getMaxPlayerCount() == null || playerCount <= round.getMaxPlayerCount()))
                .toList();

        List<Round> smallRounds = new ArrayList<>(allRound.stream().filter(round -> !round.getLarge()).toList());
        List<Round> largeRounds = new ArrayList<>(allRound.stream().filter(Round::getLarge).toList());

        Collections.shuffle(smallRounds);
        Collections.shuffle(largeRounds);

        for (int i = 0; i < amountLargeRounds; i++) {
            rounds.add(largeRounds.getFirst());
            largeRounds.removeFirst();
        }
        for (int i = rounds.size(); i < gameAmount - 1; i++) {
            rounds.add(smallRounds.getFirst());
            smallRounds.removeFirst();
        }

        Collections.shuffle(rounds);
        rounds.addFirst(smallRounds.getFirst());

        game.setRounds(rounds);

        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/initrounds")
    public ResponseEntity<List<Round>> initRounds() {
        gameRepository.deleteAll();
        roundRepository.deleteAll();
        return ResponseEntity.ok(roundRepository.saveAll(
                List.of(
                        new Round(null, "Einfach: Wissenschaft und Technik", "Beantworte fünf Fragen in der Kategorie Wissenschaft und Technik. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Was ist die Einheit der elektrischen Induktivität?§Henry;Mho;Lumen;Volt"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4")
                        ), "#4fa626"),
                        new Round(null, "Einfach: Geschichte", "Beantworte fünf Fragen in der Kategorie Geschichte. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4")
                        ), "#f49515"),
                        new Round(null, "Einfach: Sport und Freizeit", "Beantworte fünf Fragen in der Kategorie Sport und Freizeit. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4")
                        ), "#f49515"),
                        new Round(null, "Einfach: Geografie", "Beantworte fünf Fragen in der Kategorie Geografie. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4")
                        ), "#018fce"),
                        new Round(null, "Einfach: Unterhaltung", "Beantworte fünf Fragen in der Kategorie Unterhaltung. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4")
                        ), "#d20377"),
                        new Round(null, "Einfach: Kunst und Literatur", "Beantworte fünf Fragen in der Kategorie Kunst und Literatur. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4"),
                                new Question(null, "Test§1;2;3;4")
                        ), "#8d338d")
                )
        ));
    }
}
