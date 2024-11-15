public class Tournament {
    // Felter til at repræsentere en turnering. Disse matcher databasekolonnerne.
    private int id;
    private String name;
    private String game;
    private int participants;
    private double prizePool;

    // Constructor til at initialisere en Tournament-objekt.
    public Tournament(int id, String name, String game, int participants, double prizePool) {
        this.id = id;
        this.name = name;
        this.game = game;
        this.participants = participants;
        this.prizePool = prizePool;
    }

    // Gettere og settere gør det muligt at få og sætte værdier for felterne.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public double getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(double prizePool) {
        this.prizePool = prizePool;
    }

    // toString() gør det nemt at udskrive objektet som en læsbar tekststreng.
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Game: %s, Participants: %d, Prize Pool: %.2f",
                id, name, game, participants, prizePool);
    }
}
