import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentRepository {

    // Opretter en ny turnering i databasen (CREATE i CRUD).
    public void createTournament(Tournament tournament) {
        // SQL-sætning med placeholders (?) for dynamiske værdier.
        String sql = "INSERT INTO tournaments (name, game, participants, prize_pool) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Indsætter værdier i placeholders i SQL-sætningen.
            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setString(2, tournament.getGame());
            preparedStatement.setInt(3, tournament.getParticipants());
            preparedStatement.setDouble(4, tournament.getPrizePool());

            // Udfører SQL-sætningen og returnerer antal rækker, der blev ændret.
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("En ny turnering blev tilføjet!");
            }
        } catch (SQLException e) {
            // Håndterer SQL-relaterede fejl.
            e.printStackTrace();
        }
    }

    // Læser alle turneringer fra databasen (READ i CRUD).
    public List<Tournament> readTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        String sql = "SELECT * FROM tournaments";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Gennemløber resultaterne og opretter Tournament-objekter.
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String game = resultSet.getString("game");
                int participants = resultSet.getInt("participants");
                double prizePool = resultSet.getDouble("prize_pool");

                tournaments.add(new Tournament(id, name, game, participants, prizePool));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    // Opdaterer en eksisterende turnering (UPDATE i CRUD).
    public void updateTournament(Tournament tournament) {
        String sql = "UPDATE tournaments SET name = ?, game = ?, participants = ?, prize_pool = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setString(2, tournament.getGame());
            preparedStatement.setInt(3, tournament.getParticipants());
            preparedStatement.setDouble(4, tournament.getPrizePool());
            preparedStatement.setInt(5, tournament.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Turneringen blev opdateret!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sletter en turnering fra databasen (DELETE i CRUD).
    public void deleteTournament(int id) {
        String sql = "DELETE FROM tournaments WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Turneringen blev slettet!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
