public class Promotion {
    private String type;
    private String[] items;

    public Promotion(String type, String[] items) {
        this.type = type;
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public String[] getItems() {
        return items;
    }
}
