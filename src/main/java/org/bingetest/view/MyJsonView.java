package org.bingetest.view;

public class MyJsonView { // Créer des "vues" qu'on appellera dans les beans
public static class Saison{};
public static class SaisonDB extends Saison{};
public static class Serie{};
public static class SerieDB extends Serie{};
public static class Episode{};
public static class EpisodeDB extends Episode{};
public static class EvalEpisode{};
public static class EvalEpisodeDB extends EvalEpisode{};
public static class EvalSerie{};
public static class EvalSerieDB extends EvalEpisode{};
public static class Utilisateur{};
public static class UtilisateurDB extends Utilisateur{};
public static class UtilisateurComplet extends UtilisateurDB{};
public static class PlageHoraireDispo{};
public static class PlageHoraireDispoDB extends PlageHoraireDispo{};
public static class Role{};
public static class RoleDB extends Role{};
public static class Genre{};
public static class GenreDB extends Genre{};
public static class Plateforme{};
public static class PlateformeDB extends Plateforme{};
}
