

public class City implements Comparable<City> {
    private String name;
    private int value;
    private int initialValue;
    private Country country;


    public City(String name, int value, Country country) {
        this.name = name;
        this.value = value;
        initialValue = value;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void changeValue(int amount) {
        value += amount;
    }

    public void reset() {
        value = initialValue;
    }

    public int arrive() {
        int bonus = country.bonus(value);
        value -= bonus;
        return bonus;
    }

    public Country getCountry() {
        return country;
    }

    public int compareTo(City c) {
        return name.compareTo(c.name);
    }

    @Override
    public String toString() {
        return name + " " + "(" + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(getClass() != o.getClass()) {
            return false;
        }
        City c = (City) o;
        return name.equals(c.name) && country.equals(c.country);
    }

    @Override
    public int hashCode() {
        int result = 13;
            result = result + 29 * name.hashCode() + 31 * country.hashCode();

        return result;
    }


}

