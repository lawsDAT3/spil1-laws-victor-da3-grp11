import java.util.*;

public class Country {
    private String name;
    private Map<City, Set<Road>> network;
    private Game game;


    public Country(String name) {
        network = new TreeMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public int bonus(int value) {
        if (value > 0) {
            return game.getRandom().nextInt(value + 1);
        } else return 0;
    }

    public Set<City> getCities() {
        return network.keySet();
    }

    public City getCity(String name) {
        return getCities().stream()
                .filter(n -> name.equals(n.getName()))
                .findAny()
                .orElse(null);
    }

    public void addCity(City c)  {
        network.put(c, new TreeSet<>());
    }

    public void addRoads(City a, City b, int length) {
        if(length <= 0 || a.equals(b)) {
            return;
        }
        if(network.containsKey(a)) {
            Road r = new Road(a, b, length);
            network.get(a).add(r);
        }
        if(network.containsKey(b)) {
            Road r = new Road(b, a, length);
            network.get(b).add(r);
        }
    }

    public Set<Road> getRoads(City c) {
        return network.get(c);
    }

    public void reset() {
        getCities().stream()
                   .forEach(c -> c.reset());
    }

    public Position position(City city) {
        Position p = new Position(city, city, 0);
        return p;
    }

    public Position readyToTravel(City from, City to) {
        if (from.equals(to)) {
            return position(from);
        }
        if (network.containsKey(from)) {
            Iterator<Road> it = network.get(from).iterator();
            while (it.hasNext()) {
                Road r = it.next();
                if (r.getTo().equals(to)) {
                    int dist = r.getLength();
                    Position p = new Position(from, to, dist);
                    return p;
                }
            }
        }
        return position(from);
    }

    @Override
    public String toString() {
        return name;
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
        Country c = (Country) o;
        return name.equals(c.name);
    }

    @Override
    public int hashCode() {
        int result = 31;
            result = result + 37 * name.hashCode();
        return result;
    }

}
