package org.example.wlback;

import org.example.wlback.entities.questions.Answer;
import org.example.wlback.entities.questions.QuestionFirst;

import java.util.List;

public class QuestionInit {
    public static List<QuestionFirst> initQuestionFirsts() {
        return List.of(
                new QuestionFirst(null, "Wähle ein Italienisches Wort", List.of(
                        new Answer(null, "Arrivederci", true, Byte.parseByte("1")),
                        new Answer(null, "Xәтта", false, Byte.parseByte("1")),
                        new Answer(null, "Tx'exb'il", false, Byte.parseByte("1")),

                        new Answer(null, "Piacere", true, Byte.parseByte("2")),
                        new Answer(null, "Ntawm", false, Byte.parseByte("2")),
                        new Answer(null, "Senjata", false, Byte.parseByte("2")),

                        new Answer(null, "Buongiorno", true, Byte.parseByte("3")),
                        new Answer(null, "Ot", false, Byte.parseByte("3")),
                        new Answer(null, "Ijiir", false, Byte.parseByte("3")),

                        new Answer(null, "Spensieratezza", true, Byte.parseByte("4")),
                        new Answer(null, "Hus", false, Byte.parseByte("4")),
                        new Answer(null, "Papalagae", false, Byte.parseByte("4")),

                        new Answer(null, "Apericena", true, Byte.parseByte("5")),
                        new Answer(null, "Marques", false, Byte.parseByte("5")),
                        new Answer(null, "Buba", false, Byte.parseByte("5")),

                        new Answer(null, "Magari", true, Byte.parseByte("6")),
                        new Answer(null, "Banis", false, Byte.parseByte("6")),
                        new Answer(null, "Villapaita", false, Byte.parseByte("6"))
                        )),


                new QuestionFirst(null, "Wähle ein IKEA-Produkt", List.of(
                        new Answer(null, "KALLAX", true, Byte.parseByte("1")),
                        new Answer(null, "HAUS", false, Byte.parseByte("1")),
                        new Answer(null, "SAHALEMANTIUS", false, Byte.parseByte("1")),

                        new Answer(null, "BILLY", true, Byte.parseByte("2")),
                        new Answer(null, "KAKKU", false, Byte.parseByte("2")),
                        new Answer(null, "MASCHIEN", false, Byte.parseByte("2")),

                        new Answer(null, "MALM", true, Byte.parseByte("3")),
                        new Answer(null, "TÄTI", false, Byte.parseByte("3")),
                        new Answer(null, "KAHDEKSANTOISTA", false, Byte.parseByte("3")),

                        new Answer(null, "DJUNGELSKOG", true, Byte.parseByte("4")),
                        new Answer(null, "MINUN", false, Byte.parseByte("4")),
                        new Answer(null, "KOLMAS", false, Byte.parseByte("4")),

                        new Answer(null, "SÖDERHAMN", true, Byte.parseByte("5")),
                        new Answer(null, "SERKKU", false, Byte.parseByte("5")),
                        new Answer(null, "KIITOS", false, Byte.parseByte("5")),

                        new Answer(null, "STOFTMOLN", true, Byte.parseByte("6")),
                        new Answer(null, "APUA", false, Byte.parseByte("6")),
                        new Answer(null, "HARMI", false, Byte.parseByte("6"))
                )),


                new QuestionFirst(null, "Wähle eine amerikanische Fluggesellschaft", List.of(
                        new Answer(null, "United", true, Byte.parseByte("1")),
                        new Answer(null, "Air France", false, Byte.parseByte("1")),
                        new Answer(null, "Lufthansa", false, Byte.parseByte("1")),

                        new Answer(null, "Spirit", true, Byte.parseByte("2")),
                        new Answer(null, "IndiGo", false, Byte.parseByte("2")),
                        new Answer(null, "Cathay Pacific", false, Byte.parseByte("2")),

                        new Answer(null, "Southwest", true, Byte.parseByte("3")),
                        new Answer(null, "Aeroflot", false, Byte.parseByte("3")),
                        new Answer(null, "Cargolux", false, Byte.parseByte("3")),

                        new Answer(null, "American", true, Byte.parseByte("4")),
                        new Answer(null, "FedEx", false, Byte.parseByte("4")),
                        new Answer(null, "Emirates", false, Byte.parseByte("4")),

                        new Answer(null, "Delta", true, Byte.parseByte("5")),
                        new Answer(null, "Qantas", false, Byte.parseByte("5")),
                        new Answer(null, "Cortana", false, Byte.parseByte("5")),

                        new Answer(null, "Alaska", true, Byte.parseByte("6")),
                        new Answer(null, "Ryanair", false, Byte.parseByte("6")),
                        new Answer(null, "easyJet", false, Byte.parseByte("6"))
                )),


                new QuestionFirst(null, "Wähle ein Italienisches Wort", List.of(
                        new Answer(null, "Arrivederci", true, Byte.parseByte("1")),
                        new Answer(null, "Xәтта", false, Byte.parseByte("1")),
                        new Answer(null, "Tx'exb'il", false, Byte.parseByte("1")),

                        new Answer(null, "Piacere", true, Byte.parseByte("2")),
                        new Answer(null, "Ntawm", false, Byte.parseByte("2")),
                        new Answer(null, "Senjata", false, Byte.parseByte("2")),

                        new Answer(null, "Buongiorno", true, Byte.parseByte("3")),
                        new Answer(null, "Ot", false, Byte.parseByte("3")),
                        new Answer(null, "Ijiir", false, Byte.parseByte("3")),

                        new Answer(null, "Spensieratezza", true, Byte.parseByte("4")),
                        new Answer(null, "Hus", false, Byte.parseByte("4")),
                        new Answer(null, "Papalagae", false, Byte.parseByte("4")),

                        new Answer(null, "Apericena", true, Byte.parseByte("5")),
                        new Answer(null, "Marques", false, Byte.parseByte("5")),
                        new Answer(null, "Buba", false, Byte.parseByte("5")),

                        new Answer(null, "Magari", true, Byte.parseByte("6")),
                        new Answer(null, "Banis", false, Byte.parseByte("6")),
                        new Answer(null, "Villapaita", false, Byte.parseByte("6"))
                )),


                new QuestionFirst(null, "Wähle ein Italienisches Wort", List.of(
                        new Answer(null, "Arrivederci", true, Byte.parseByte("1")),
                        new Answer(null, "Xәтта", false, Byte.parseByte("1")),
                        new Answer(null, "Tx'exb'il", false, Byte.parseByte("1")),

                        new Answer(null, "Piacere", true, Byte.parseByte("2")),
                        new Answer(null, "Ntawm", false, Byte.parseByte("2")),
                        new Answer(null, "Senjata", false, Byte.parseByte("2")),

                        new Answer(null, "Buongiorno", true, Byte.parseByte("3")),
                        new Answer(null, "Ot", false, Byte.parseByte("3")),
                        new Answer(null, "Ijiir", false, Byte.parseByte("3")),

                        new Answer(null, "Spensieratezza", true, Byte.parseByte("4")),
                        new Answer(null, "Hus", false, Byte.parseByte("4")),
                        new Answer(null, "Papalagae", false, Byte.parseByte("4")),

                        new Answer(null, "Apericena", true, Byte.parseByte("5")),
                        new Answer(null, "Marques", false, Byte.parseByte("5")),
                        new Answer(null, "Buba", false, Byte.parseByte("5")),

                        new Answer(null, "Magari", true, Byte.parseByte("6")),
                        new Answer(null, "Banis", false, Byte.parseByte("6")),
                        new Answer(null, "Villapaita", false, Byte.parseByte("6"))
                )),


                new QuestionFirst(null, "Wähle ein Italienisches Wort", List.of(
                        new Answer(null, "Arrivederci", true, Byte.parseByte("1")),
                        new Answer(null, "Xәтта", false, Byte.parseByte("1")),
                        new Answer(null, "Tx'exb'il", false, Byte.parseByte("1")),

                        new Answer(null, "Piacere", true, Byte.parseByte("2")),
                        new Answer(null, "Ntawm", false, Byte.parseByte("2")),
                        new Answer(null, "Senjata", false, Byte.parseByte("2")),

                        new Answer(null, "Buongiorno", true, Byte.parseByte("3")),
                        new Answer(null, "Ot", false, Byte.parseByte("3")),
                        new Answer(null, "Ijiir", false, Byte.parseByte("3")),

                        new Answer(null, "Spensieratezza", true, Byte.parseByte("4")),
                        new Answer(null, "Hus", false, Byte.parseByte("4")),
                        new Answer(null, "Papalagae", false, Byte.parseByte("4")),

                        new Answer(null, "Apericena", true, Byte.parseByte("5")),
                        new Answer(null, "Marques", false, Byte.parseByte("5")),
                        new Answer(null, "Buba", false, Byte.parseByte("5")),

                        new Answer(null, "Magari", true, Byte.parseByte("6")),
                        new Answer(null, "Banis", false, Byte.parseByte("6")),
                        new Answer(null, "Villapaita", false, Byte.parseByte("6"))
                ))
        );
    }
}
