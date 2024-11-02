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
                                new Question(null, "Welche Farbe hat das Gas Sauerstoff in flüssiger Form?§Hellblau;Farblos;Grün;Gelb"),
                                new Question(null, "Wie nennt man die Geschwindigkeit, die ein Objekt braucht, um der Erdanziehung zu entkommen?§Fluchtgeschwindigkeit;Schallgeschwindigkeit;Lichtgeschwindigkeit;Überschallgeschwindigkeit"),
                                new Question(null, "Was misst ein Barometer?§Luftdruck;Temperatur;Luftfeuchtigkeit;Windgeschwindigkeit"),
                                new Question(null, "Welches chemische Element hat das Symbol 'Fe'?§Eisen;Zink;Blei;Gold"),
                                new Question(null, "Wer erfand die Glühbirne?§Thomas Edison;Nikola Tesla;Albert Einstein;Isaac Newton"),
                                new Question(null, "Wie viele Planeten hat unser Sonnensystem?§8;7;9;10"),
                                new Question(null, "Was wird mit einem Thermometer gemessen?§Temperatur;Luftdruck;Feuchtigkeit;Dichte"),
                                new Question(null, "In welchem Jahr wurde der erste Mensch auf den Mond geschickt?§1969;1972;1963;1959"),
                                new Question(null, "Woraus besteht Wasser?§Wasserstoff und Sauerstoff;Stickstoff und Helium;Kohlenstoff und Sauerstoff;Natrium und Chlor"),
                                new Question(null, "Welche Erfindung wird James Watt zugeschrieben?§Dampfmaschine;Fernrohr;Glühbirne;Auto"),
                                new Question(null, "Wie lautet das Symbol des chemischen Elements Gold?§Au;Ag;Pb;Fe"),
                                new Question(null, "Was ist die Basis-Einheit für die elektrische Stromstärke?§Ampere;Volt;Watt;Ohm"),
                                new Question(null, "Wer entdeckte die Relativitätstheorie?§Albert Einstein;Isaac Newton;Galileo Galilei;Nikola Tesla"),
                                new Question(null, "Was ist das stärkste bekannte Magnetfeld in der Natur?§Neutronenstern;Schwarzes Loch;Jupiter;Magnetit"),
                                new Question(null, "Wer entdeckte das Elektron?§J.J. Thomson;Niels Bohr;James Clerk Maxwell;Erwin Schrödinger"),
                                new Question(null, "Welche Säure ist in Ameisen zu finden?§Ameisensäure;Essigsäure;Milchsäure;Salzsäure"),
                                new Question(null, "Welche Art von Welle ist Licht?§Elektromagnetische Welle;Schallwelle;Oberflächenwelle;Mechanische Welle"),
                                new Question(null, "Welches Metall hat die höchste elektrische Leitfähigkeit?§Silber;Kupfer;Gold;Aluminium"),
                                new Question(null, "Wer formulierte das Ohmsche Gesetz?§Georg Simon Ohm;Alessandro Volta;Michael Faraday;James Watt"),
                                new Question(null, "Welches Element ist das häufigste im Universum?§Wasserstoff;Helium;Sauerstoff;Kohlenstoff"),
                                new Question(null, "Wie viele Basenpaare enthält das menschliche Genom?§Rund 3 Milliarden;10 Milliarden;500 Millionen;2 Milliarden"),
                                new Question(null, "Was beschreibt die Schrödinger-Gleichung?§Wellenfunktion von Teilchen;Elektromagnetische Felder;Thermodynamische Prozesse;Elektrostatik"),
                                new Question(null, "Wie lautet die SI-Einheit für Arbeit?§Joule;Watt;Newton;Pascal"),
                                new Question(null, "Was ist ein Photon?§Lichtteilchen;Elektron;Neutron;Atomkern"),
                                new Question(null, "Was ist die häufigste Form von Strahlung im Weltall?§Kosmische Mikrowellenhintergrundstrahlung;Gammastrahlen;Röntgenstrahlen;UV-Strahlen"),
                                new Question(null, "Welches Element wird in Atomkraftwerken als Brennstoff verwendet?§Uran;Plutonium;Thorium;Radium"),
                                new Question(null, "Wie lautet das chemische Symbol für Blei?§Pb;Sn;Pl;Bi"),
                                new Question(null, "Welche Theorie beschreibt die Anziehungskraft von Massen?§Allgemeine Relativitätstheorie;Quantenmechanik;Elektromagnetische Theorie;Kinetische Gastheorie"),
                                new Question(null, "Wer entwickelte die Impfung gegen Tollwut?§Louis Pasteur;Robert Koch;Edward Jenner;Marie Curie")
                        ), "#4fa626"),

                        new Round(null, "Einfach: Geschichte", "Beantworte fünf Fragen in der Kategorie Geschichte. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Wer war der erste Präsident der Vereinigten Staaten?§George Washington;Abraham Lincoln;Thomas Jefferson;John Adams"),
                                new Question(null, "Welches Bauwerk entstand im alten Ägypten?§Die Pyramiden von Gizeh;Kolosseum;Chinesische Mauer;Eiffelturm"),
                                new Question(null, "In welchem Jahr endete der Zweite Weltkrieg?§1945;1939;1918;1950"),
                                new Question(null, "Wer war der Anführer der NSDAP?§Adolf Hitler;Joseph Stalin;Benito Mussolini;Winston Churchill"),
                                new Question(null, "Welches Reich wurde von Alexander dem Großen erobert?§Perserreich;Römisches Reich;Chinesisches Reich;Indisches Reich"),
                                new Question(null, "Was war der Auslöser des Ersten Weltkriegs?§Ermordung von Franz Ferdinand;Sturm auf die Bastille;Angriff auf Pearl Harbor;Fall der Berliner Mauer"),
                                new Question(null, "Woher stammte der Reformator Martin Luther?§Deutschland;England;Italien;Frankreich"),
                                new Question(null, "Welches Jahr markiert den Fall der Berliner Mauer?§1989;1990;1961;1949"),
                                new Question(null, "Wer war der erste Kaiser des Römischen Reiches?§Augustus;Julius Caesar;Nero;Tiberius"),
                                new Question(null, "Welcher Krieg fand im 19. Jahrhundert statt?§Amerikanischer Bürgerkrieg;Hundertjähriger Krieg;Erster Weltkrieg;Krimkrieg"),
                                new Question(null, "Welches Land wurde 1776 unabhängig?§USA;Kanada;Australien;Brasilien"),
                                new Question(null, "Wie hieß der erste Mensch, der den Südpol erreichte?§Roald Amundsen;Ernest Shackleton;Robert Scott;James Cook"),
                                new Question(null, "Welches Land wurde 1991 unabhängig von der Sowjetunion?§Ukraine;Polen;DDR;Finnland"),
                                new Question(null, "Wer schrieb das 'Kommunistische Manifest'?§Karl Marx;Friedrich Engels;Wladimir Lenin;Josef Stalin"),
                                new Question(null, "Welches Reich wurde 1453 von den Osmanen erobert?§Byzantinisches Reich;Römisches Reich;Fränkisches Reich;Persisches Reich"),
                                new Question(null, "Wer war der erste Kaiser von China?§Qin Shi Huang;Sun Tzu;Kublai Khan;Liu Bang"),
                                new Question(null, "Wann begann die Französische Revolution?§1789;1799;1776;1804"),
                                new Question(null, "Welches Land war das Hauptziel des D-Day im Zweiten Weltkrieg?§Frankreich;Belgien;Deutschland;Italien"),
                                new Question(null, "Welcher Pharao ließ die große Pyramide von Gizeh bauen?§Cheops;Tutanchamun;Ramses II;Echnaton"),
                                new Question(null, "Welche antike Stadt wurde durch den Ausbruch des Vesuvs zerstört?§Pompeji;Athen;Karthago;Pergamon"),
                                new Question(null, "Wer führte die Inquisition in Spanien ein?§Ferdinand II. und Isabella;Karl V.;Philipp II.;Alfonso X."),
                                new Question(null, "Wann wurde die erste Atombombe abgeworfen?§1945;1944;1943;1946"),
                                new Question(null, "Wer gründete das Mongolische Reich?§Dschingis Khan;Kublai Khan;Attila;Temür Khan"),
                                new Question(null, "Welches Land war das Zentrum des Heiligen Römischen Reiches?§Deutschland;Italien;Spanien;Frankreich"),
                                new Question(null, "Was war die erste Zivilisation in Mesopotamien?§Sumerer;Babylonier;Ägypter;Assyrer"),
                                new Question(null, "In welchem Jahr fand der Prager Frühling statt?§1968;1945;1978;1956"),
                                new Question(null, "Wer war der letzte russische Zar?§Nikolaus II.;Alexander III.;Peter der Große;Katharina die Große"),
                                new Question(null, "Welche Königin regierte im 16. Jahrhundert England?§Elisabeth I.;Mary I.;Victoria;Anne"),
                                new Question(null, "Welcher persische König verlor gegen die Griechen bei der Schlacht von Marathon?§Darius I.;Xerxes;Cyrus;Artaxerxes"),
                                new Question(null, "Welches antike Volk entwickelte das erste Alphabet?§Phönizier;Ägypter;Griechen;Römer")
                        ), "#fedf01"),

                        new Round(null, "Einfach: Sport und Freizeit", "Beantworte fünf Fragen in der Kategorie Sport und Freizeit. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Wie viele Spieler hat ein Fußballteam auf dem Feld?§11;10;9;12"),
                                new Question(null, "Wie viele Minuten dauert ein Basketballspiel (NBA)?§48 Minuten;40 Minuten;90 Minuten;60 Minuten"),
                                new Question(null, "Welcher Sportler ist bekannt als 'The Greatest'?§Muhammad Ali;Michael Jordan;Usain Bolt;Tiger Woods"),
                                new Question(null, "In welchem Land wurden die ersten modernen Olympischen Spiele ausgetragen?§Griechenland;Frankreich;England;Deutschland"),
                                new Question(null, "Wie viele Punkte gibt ein Tor im Fußball?§1 Punkt;2 Punkte;3 Punkte;0 Punkte"),
                                new Question(null, "Welcher Tennisspieler hat die meisten Grand Slam-Titel gewonnen?§Rafael Nadal;Novak Djokovic;Roger Federer;Pete Sampras"),
                                new Question(null, "Wie viele Ringe hat das olympische Symbol?§5;6;7;4"),
                                new Question(null, "Welcher Sport wird mit einem Schläger und einem Federball gespielt?§Badminton;Tennis;Cricket;Squash"),
                                new Question(null, "In welchem Sport gewinnt man den Stanley Cup?§Eishockey;Basketball;Football;Baseball"),
                                new Question(null, "Welche Farbe hat das Trikot des Führenden in der Tour de France?§Gelb;Rot;Blau;Grün"),
                                new Question(null, "Wer ist Rekord-Weltmeister in der Formel 1?§Lewis Hamilton;Michael Schumacher;Ayrton Senna;Sebastian Vettel"),
                                new Question(null, "In welchem Land wurde Karate entwickelt?§Japan;China;Thailand;Indien"),
                                new Question(null, "Wie viele Sätze braucht man im Tennis für den Sieg (bei Grand Slams)?§3 für Frauen, 5 für Männer;2 für Frauen, 3 für Männer;3 für Frauen, 4 für Männer;4 für beide"),
                                new Question(null, "Wie lang ist ein Marathon?§42,195 km;50 km;10 km;21,097 km"),
                                new Question(null, "Welches Land gewann die Fußball-Weltmeisterschaft 2018?§Frankreich;Deutschland;Brasilien;Argentinien"),
                                new Question(null, "Welcher Tennisspieler hat die meisten Siege auf Sandplätzen?§Rafael Nadal;Roger Federer;Novak Djokovic;Pete Sampras"),
                                new Question(null, "Welches ist der härteste Marathon der Welt?§Marathon des Sables;Boston Marathon;New York Marathon;Berlin Marathon"),
                                new Question(null, "Wie viele Goldmedaillen gewann Michael Phelps bei den Olympischen Spielen 2008?§8;7;6;5"),
                                new Question(null, "Wer gewann die erste Fußball-Weltmeisterschaft?§Uruguay;Deutschland;Brasilien;Argentinien"),
                                new Question(null, "Welche Stadt hat die meisten NBA-Meisterschaften gewonnen?§Los Angeles;Chicago;Miami;Boston"),
                                new Question(null, "Welcher Golfer ist als 'The Golden Bear' bekannt?§Jack Nicklaus;Tiger Woods;Arnold Palmer;Phil Mickelson"),
                                new Question(null, "Wie oft wurde Roger Federer Wimbledon-Champion?§8;6;5;9"),
                                new Question(null, "In welcher Sportart gibt es den Begriff 'Turkey'?§Bowling;Baseball;Golf;Tennis"),
                                new Question(null, "Wer war der jüngste Formel-1-Weltmeister?§Sebastian Vettel;Fernando Alonso;Lewis Hamilton;Kimi Räikkönen"),
                                new Question(null, "Wie viele Punkte sind ein 'Safety' im American Football wert?§2;1;3;6"),
                                new Question(null, "Welcher Schwimmer brach den Rekord für die meisten olympischen Goldmedaillen?§Michael Phelps;Mark Spitz;Ryan Lochte;Ian Thorpe"),
                                new Question(null, "Welcher Fahrer hat die meisten Siege in der Formel 1?§Lewis Hamilton;Michael Schumacher;Ayrton Senna;Alain Prost"),
                                new Question(null, "Wie viele Spieler sind in einer Baseballmannschaft auf dem Feld?§9;8;10;11"),
                                new Question(null, "Wer hat die meisten Grand Slam-Titel bei den Frauen im Tennis?§Margaret Court;Serena Williams;Steffi Graf;Chris Evert"),
                                new Question(null, "Welcher Fußballer hat die meisten Tore in der Champions League erzielt?§Cristiano Ronaldo;Lionel Messi;Raúl;Robert Lewandowski")
                        ), "#f49515"),

                        new Round(null, "Einfach: Geografie", "Beantworte fünf Fragen in der Kategorie Geografie. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Welches ist das größte Land der Welt?§Russland;Kanada;China;USA"),
                                new Question(null, "In welchem Kontinent liegt Ägypten?§Afrika;Asien;Europa;Nordamerika"),
                                new Question(null, "Welcher ist der längste Fluss der Welt?§Nil;Amazonas;Mississippi;Donau"),
                                new Question(null, "Wie viele Kontinente gibt es?§7;6;5;8"),
                                new Question(null, "Welche Stadt wird als 'Die Stadt der Liebe' bezeichnet?§Paris;Venedig;New York;Rom"),
                                new Question(null, "Welches Land ist kein Nachbar von Deutschland?§Spanien;Polen;Frankreich;Tschechien"),
                                new Question(null, "Wo befindet sich das Himalaya-Gebirge?§Asien;Afrika;Europa;Australien"),
                                new Question(null, "Welche Wüste ist die größte der Welt?§Sahara;Gobi;Kalahari;Namib"),
                                new Question(null, "Was ist die Hauptstadt von Japan?§Tokio;Kyoto;Osaka;Hiroshima"),
                                new Question(null, "Welche Insel ist die größte der Welt?§Grönland;Australien;Madagaskar;Borneo"),
                                new Question(null, "Welches Meer liegt zwischen Afrika und Europa?§Mittelmeer;Ostsee;Nordsee;Schwarzes Meer"),
                                new Question(null, "In welchem Land liegt der Kilimandscharo?§Tansania;Kenya;Uganda;Äthiopien"),
                                new Question(null, "Wie viele Länder gibt es in Afrika?§54;52;50;48"),
                                new Question(null, "Welche Hauptstadt liegt am Tiber?§Rom;Athen;Kairo;Berlin"),
                                new Question(null, "Welches Land hat die meisten Einwohner?§China;Indien;USA;Brasilien"),
                                new Question(null, "Welcher Fluss fließt durch die meisten Länder?§Donau;Nil;Amazonas;Jangtsekiang"),
                                new Question(null, "Welche ist die größte Insel im Mittelmeer?§Sizilien;Kreta;Zypern;Sardinien"),
                                new Question(null, "Welches ist das südlichste Land der Welt?§Chile;Argentinien;Neuseeland;Südafrika"),
                                new Question(null, "Welches Gebirge trennt Europa und Asien?§Ural;Himalaya;Anden;Rocky Mountains"),
                                new Question(null, "Welcher Kontinent hat die größte Bevölkerung?§Asien;Afrika;Europa;Nordamerika"),
                                new Question(null, "Welches Land hat die längste Küstenlinie?§Kanada;Australien;USA;Russland"),
                                new Question(null, "Was ist die kleinste Hauptstadt der Welt?§Ngerulmud;Funafuti;Valletta;Vaduz"),
                                new Question(null, "In welchem Land befindet sich das Kaspische Meer?§Kasachstan;Iran;Turkmenistan;Aserbaidschan"),
                                new Question(null, "Welcher Vulkan ist der höchste aktive Vulkan der Welt?§Ojos del Salado;Ätna;Mount St. Helens;Kilauea"),
                                new Question(null, "In welchem Land befindet sich der Titicacasee?§Peru;Bolivien;Argentinien;Kolumbien"),
                                new Question(null, "Welcher See ist der tiefste der Welt?§Baikalsee;Tanganjikasee;Titicacasee;Victoriasee"),
                                new Question(null, "Welche ist die größte Wüste der Welt?§Antarktische Wüste;Sahara;Gobi;Arabische Wüste"),
                                new Question(null, "Welche Stadt hat den größten natürlichen Hafen?§Sydney;New York;Kapstadt;San Francisco"),
                                new Question(null, "Welche Stadt ist am Äquator am weitesten von einem Meer entfernt?§Kinshasa;Lima;Bangkok;Singapur"),
                                new Question(null, "Welches Land hat die meisten Inseln?§Schweden;Indonesien;Philippinen;Norwegen")
                        ), "#018fce"),

                        new Round(null, "Einfach: Unterhaltung", "Beantworte fünf Fragen in der Kategorie Unterhaltung. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Wer sang den Song 'Thriller'?§Michael Jackson;Elvis Presley;Madonna;Prince"),
                                new Question(null, "Wie heißt der Zauberlehrling von Hogwarts?§Harry Potter;Hermione Granger;Ron Weasley;Draco Malfoy"),
                                new Question(null, "Welches Instrument spielte Ludwig van Beethoven?§Klavier;Geige;Gitarre;Trompete"),
                                new Question(null, "In welchem Film spielt die Familie 'Skywalker' eine Rolle?§Star Wars;Star Trek;Herr der Ringe;Harry Potter"),
                                new Question(null, "Wer schrieb das Buch 'Der Herr der Ringe'?§J.R.R. Tolkien;George Orwell;J.K. Rowling;Ernest Hemingway"),
                                new Question(null, "Wer war der Hauptdarsteller in 'Terminator'?§Arnold Schwarzenegger;Sylvester Stallone;Bruce Willis;Tom Cruise"),
                                new Question(null, "Welche Stadt ist als 'die Stadt der Engel' bekannt?§Los Angeles;New York;San Francisco;Chicago"),
                                new Question(null, "Welche Serie spielt in der fiktiven Stadt 'Springfield'?§Die Simpsons;Family Guy;South Park;Futurama"),
                                new Question(null, "Wie heißt die Freundin von Micky Maus?§Minnie Maus;Daisy Duck;Goofy;Donald Duck"),
                                new Question(null, "Welcher Film gewann den Oscar für den besten Film 1997?§Titanic;Forrest Gump;Schindlers Liste;Pulp Fiction"),
                                new Question(null, "Wer ist der Hauptdarsteller von 'Fluch der Karibik'?§Johnny Depp;Leonardo DiCaprio;Brad Pitt;Tom Hanks"),
                                new Question(null, "Welche Band hat das Album 'Abbey Road' veröffentlicht?§The Beatles;The Rolling Stones;Queen;The Doors"),
                                new Question(null, "In welcher Stadt spielt der Film 'Der Pate'?§New York;Los Angeles;Chicago;Miami"),
                                new Question(null, "Wie heißt der Hauptcharakter in 'Breaking Bad'?§Walter White;Jesse Pinkman;Saul Goodman;Gus Fring"),
                                new Question(null, "Welches Monster ist der Gegner in vielen Filmen von Godzilla?§King Kong;Mothra;Ghidorah;Gamera"),
                                new Question(null, "Welche TV-Serie begann mit der Episode 'Der Winter naht'?§Game of Thrones;Breaking Bad;The Crown;Vikings"),
                                new Question(null, "Welcher Film erhielt als Erster einen Oscar?§Wings;Der Jazzsänger;Sunrise;Metropolis"),
                                new Question(null, "Wie lautet der vollständige Name von 'Mickey Mouse'?§Michel Mouse;Mickey Michael Mouse;Mickey Timothy Mouse;Mickey Mouse"),
                                new Question(null, "Wer ist der Vater von Simba in 'Der König der Löwen'?§Mufasa;Scar;Rafiki;Sarabi"),
                                new Question(null, "Wie lautet der Vorname des Charakters Bond aus den Filmen?§James;Jason;John;Jack"),
                                new Question(null, "In welchem Jahr kam 'Der weiße Hai' ins Kino?§1975;1980;1970;1985"),
                                new Question(null, "Wer spielt Jack Dawson in 'Titanic'?§Leonardo DiCaprio;Brad Pitt;Johnny Depp;Tom Hanks"),
                                new Question(null, "In welcher Serie spielt ein Mathematik-Genie, das für das FBI arbeitet?§Numb3rs;Sherlock;CSI;Lie to Me"),
                                new Question(null, "Wer spielt 'Die Braut' in 'Kill Bill'?§Uma Thurman;Angelina Jolie;Scarlett Johansson;Julia Roberts"),
                                new Question(null, "Welche Rockband veröffentlichte 'Bohemian Rhapsody'?§Queen;The Beatles;Led Zeppelin;Rolling Stones"),
                                new Question(null, "Wie heißt die Hauptfigur in 'Fight Club'?§Tyler Durden;Mark Gray;Jack Holden;David Fincher"),
                                new Question(null, "Welcher Filmregisseur ist für seine Spannung und Thriller bekannt?§Alfred Hitchcock;Stanley Kubrick;George Lucas;Steven Spielberg"),
                                new Question(null, "Wer war der erste weibliche Star Wars-Hauptcharakter?§Prinzessin Leia;Rey;Padmé Amidala;Ahsoka Tano"),
                                new Question(null, "Welcher Film brachte den Satz 'May the Force be with you' hervor?§Star Wars;Star Trek;Avatar;Herr der Ringe"),
                                new Question(null, "Welcher Schauspieler spielt den Joker in 'The Dark Knight'?§Heath Ledger;Joaquin Phoenix;Jared Leto;Jack Nicholson")
                        ), "#d20377"),

                        new Round(null, "Einfach: Kunst und Literatur", "Beantworte fünf Fragen in der Kategorie Kunst und Literatur. Jede Richtige Antwort gibt 2 Punkte.", "simple", false, null, null, List.of(
                                new Question(null, "Wer malte die Mona Lisa?§Leonardo da Vinci;Pablo Picasso;Vincent van Gogh;Claude Monet"),
                                new Question(null, "Wer schrieb 'Romeo und Julia'?§William Shakespeare;Homer;Goethe;Dante"),
                                new Question(null, "Welches Kunstwerk ist ein Gemälde von Edvard Munch?§Der Schrei;Die Nachtwache;Guernica;Sternennacht"),
                                new Question(null, "In welchem Land wurde der Impressionismus populär?§Frankreich;Deutschland;Spanien;England"),
                                new Question(null, "Wie lautet der Name des Schlosses von König Ludwig II. in Bayern?§Neuschwanstein;Hohenschwangau;Linderhof;Herrenchiemsee"),
                                new Question(null, "Wer schrieb die 'Odyssee'?§Homer;Sophokles;Vergil;Ovid"),
                                new Question(null, "Welches Werk schrieb Franz Kafka?§Der Prozess;Effi Briest;Faust;Krieg und Frieden"),
                                new Question(null, "Wer malte die Sternennacht?§Vincent van Gogh;Salvador Dalí;Claude Monet;Edvard Munch"),
                                new Question(null, "Welcher Stil ist Pablo Picasso zuzuordnen?§Kubismus;Expressionismus;Impressionismus;Barock"),
                                new Question(null, "Wer schrieb 'Die Verwandlung'?§Franz Kafka;Thomas Mann;Hermann Hesse;Friedrich Schiller"),
                                new Question(null, "Welcher Maler ist bekannt für Sonnenblumen-Gemälde?§Vincent van Gogh;Claude Monet;Rembrandt;Michelangelo"),
                                new Question(null, "Wer malte das letzte Abendmahl?§Leonardo da Vinci;Raphael;Michelangelo;Donatello"),
                                new Question(null, "Welches Buch schrieb Mark Twain?§Tom Sawyer;Moby Dick;Anna Karenina;Die Verwandlung"),
                                new Question(null, "Welche Kunstepoche folgt auf die Renaissance?§Barock;Gotik;Klassizismus;Romantik"),
                                new Question(null, "Wer schrieb 'Don Quijote'?§Miguel de Cervantes;Charles Dickens;Victor Hugo;Leo Tolstoi"),
                                new Question(null, "Welcher Künstler malte 'Das letzte Urteil'?§Michelangelo;Raphael;Leonardo da Vinci;Botticelli"),
                                new Question(null, "Welcher russische Schriftsteller schrieb 'Krieg und Frieden'?§Leo Tolstoi;Fyodor Dostojewski;Anton Tschechow;Nikolai Gogol"),
                                new Question(null, "Wer schrieb die 'Göttliche Komödie'?§Dante Alighieri;Boccaccio;Virgil;Petrarca"),
                                new Question(null, "Welcher Maler ist für seine Werke mit Wasserlilien bekannt?§Claude Monet;Paul Cézanne;Pierre-Auguste Renoir;Camille Pissarro"),
                                new Question(null, "Wer schrieb 'Auf der Suche nach der verlorenen Zeit'?§Marcel Proust;James Joyce;Virginia Woolf;Thomas Mann"),
                                new Question(null, "Welches Werk schrieb Goethe außer 'Faust'?§Die Leiden des jungen Werther;Der zerbrochene Krug;Die Räuber;Der Schimmelreiter"),
                                new Question(null, "Welche Kunstrichtung ist Salvador Dalí zuzuordnen?§Surrealismus;Kubismus;Impressionismus;Expressionismus"),
                                new Question(null, "Wer schrieb das Gedicht 'Der Rabe'?§Edgar Allan Poe;William Wordsworth;Robert Frost;Percy Shelley"),
                                new Question(null, "Welches Werk schrieb F. Scott Fitzgerald?§Der große Gatsby;Stolz und Vorurteil;Jane Eyre;Sturmhöhe"),
                                new Question(null, "Wer ist der Autor von 'Moby-Dick'?§Herman Melville;Mark Twain;Ernest Hemingway;John Steinbeck"),
                                new Question(null, "Welcher Künstler ist für die 'Campbell's Soup Cans' bekannt?§Andy Warhol;Roy Lichtenstein;Jean-Michel Basquiat;Jackson Pollock"),
                                new Question(null, "In welcher Epoche entstand der Barockstil?§17. Jahrhundert;19. Jahrhundert;15. Jahrhundert;18. Jahrhundert"),
                                new Question(null, "Wer schrieb 'Hundert Jahre Einsamkeit'?§Gabriel García Márquez;Mario Vargas Llosa;Pablo Neruda;Julio Cortázar"),
                                new Question(null, "Welcher Künstler ist bekannt für seine Blau-Periode?§Pablo Picasso;Henri Matisse;Georges Braque;Joan Miró"),
                                new Question(null, "Welcher italienische Dichter schrieb 'Das Decamerone'?§Giovanni Boccaccio;Dante Alighieri;Francesco Petrarca;Niccolò Machiavelli")
                        ), "#8d338d")

                )
        ));
    }
}
