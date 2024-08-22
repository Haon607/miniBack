package org.example.wlback.trollers;

import org.example.wlback.QuestionInit;
import org.example.wlback.entities.Game;
import org.example.wlback.entities.Player;
import org.example.wlback.entities.questions.Answer;
import org.example.wlback.entities.questions.QuestionFirst;
import org.example.wlback.entities.questions.QuestionSecond;
import org.example.wlback.repos.GameRepository;
import org.example.wlback.repos.QuestionFirstRepository;
import org.example.wlback.repos.QuestionSecondRepository;
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
    private QuestionFirstRepository questionFirstRepository;
    @Autowired
    private QuestionSecondRepository questionSecondRepository;
    @Autowired
    private PlayerController playerController;

    @PostMapping
    public ResponseEntity<Game> openGame() {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(new Game(null, null, "/idle", null, null, null)));
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

    @PostMapping("/{id}/questions")
    public ResponseEntity<Game> addQuestions(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElseThrow();
        List<QuestionFirst> questionFirsts = questionFirstRepository.findAll();
        List<QuestionSecond> questionSeconds = questionSecondRepository.findAll();
        game.setQuestionFirsts(questionFirsts);
        game.setQuestionSecond(questionSeconds.getFirst());
        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/initquestions")
    public ResponseEntity<Void> initQuestions() {
        this.gameRepository.deleteAll();
        this.questionFirstRepository.deleteAll();
        this.questionFirstRepository.saveAll(QuestionInit.initQuestionFirsts());
        this.questionSecondRepository.deleteAll();
        this.questionSecondRepository.saveAll(QuestionInit.initQuestionSeconds());
        return ResponseEntity.ok(null);
    }
}
