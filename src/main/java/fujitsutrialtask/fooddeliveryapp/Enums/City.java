package fujitsutrialtask.fooddeliveryapp.Enums;

public enum City {
    Tallinn("Tallinn"),
    Tartu("Tartu"),
    Parnu("Pärnu");

    private final String name;

    City(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
