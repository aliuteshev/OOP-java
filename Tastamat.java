package Store;

public class Tastamat {
    private int index;
    private String city;
    private String location;

    public Tastamat(int index, String city, String location) {
        this.index = index;
        this.city = city;
        this.location = location;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return index + " " + city + " " + location;
    }
}
