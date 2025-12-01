package library.model;

public class Tablet extends Item implements Borrowable {
    private String brand;
    private String specs;

    public Tablet(String callNumber, String title, String brand, String specs) {
        super(callNumber, title);
        this.brand = brand;
        this.specs = specs;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getSpecs() { return specs; }
    public void setSpecs(String specs) { this.specs = specs; }

    @Override
    public String getDetails() {
        return String.format("Tablet: %s (%s) - %s", getTitle(), brand, specs);
    }
}

