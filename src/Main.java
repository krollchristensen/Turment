import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Opretter en instans af repository til at håndtere CRUD-operationer.
        TournamentRepository repository = new TournamentRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Udskriver menuen.
            System.out.println("\n--- Gaming Tournament Menu ---");
            System.out.println("1. Tilføj en ny turnering");
            System.out.println("2. Vis alle turneringer");
            System.out.println("3. Opdater en turnering");
            System.out.println("4. Slet en turnering");
            System.out.println("5. Afslut");
            System.out.print("Vælg en mulighed: ");

            int choice = scanner.nextInt(); // Henter brugerens valg.
            scanner.nextLine(); // Rydder scanner-bufferen.

            switch (choice) {
                case 1 -> {
                    System.out.print("Indtast navn: ");
                    String name = scanner.nextLine();
                    System.out.print("Indtast spil: ");
                    String game = scanner.nextLine();
                    System.out.print("Indtast antal deltagere: ");
                    int participants = scanner.nextInt();
                    System.out.print("Indtast præmiesum: ");
                    double prizePool = scanner.nextDouble();

                    // Opretter et nyt turneringsobjekt og tilføjer det til databasen.
                    Tournament tournament = new Tournament(0, name, game, participants, prizePool);
                    repository.createTournament(tournament);
                }
                case 2 -> {
                    // Henter og viser alle turneringer fra databasen.
                    List<Tournament> tournaments = repository.readTournaments();
                    tournaments.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Indtast ID for turnering, der skal opdateres: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Indtast nyt navn: ");
                    String name = scanner.nextLine();
                    System.out.print("Indtast nyt spil: ");
                    String game = scanner.nextLine();
                    System.out.print("Indtast nyt antal deltagere: ");
                    int participants = scanner.nextInt();
                    System.out.print("Indtast ny præmiesum: ");
                    double prizePool = scanner.nextDouble();

                    // Opdaterer den valgte turnering i databasen.
                    Tournament tournament = new Tournament(id, name, game, participants, prizePool);
                    repository.updateTournament(tournament);
                }
                case 4 -> {
                    System.out.print("Indtast ID for turnering, der skal slettes: ");
                    int id = scanner.nextInt();
                    repository.deleteTournament(id);
                }
                case 5 -> {
                    System.out.println("Afslutter programmet...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }
}
