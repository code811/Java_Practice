public abstract class Mendec {

    private String name;
    private String description;
    private static int nextId = 0;
    private int id;

    public Mendec(String name, String description) {
        this.name = name;
        this.description = description;
        id = nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String decrypt(String sentence);

    public abstract String encrypt(String sentence);

    @Override
    public String toString() {
        return String.format("#%d: %s - ", id, description);
    }
}
