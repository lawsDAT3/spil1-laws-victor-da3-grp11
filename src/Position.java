public class Position {
    private City from;
    private City to;
    private int distance;
    private int total;

    public Position(City from, City to, int distance) {
        total = distance;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    public int getTotal() {
        return total;
    }

    public boolean move() {
        if(distance > 0) {
            distance --;
            return true;
        }
        return false;
    }

    public void turnAround() {
        City temp = from;
        from = to;
        to = temp;
        distance = total - distance;

    }

    public boolean hasArrived() {
        return distance == 0;
    }


    @Override
    public String toString() {
        return from.toString() + " -> " + to.toString() + " : " + distance + "/" + total;
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
        Position p = (Position) o;
        return from.getName().equals(p.from.getName())
                && to.getName().equals(p.to.getName())
                && distance == p.distance
                && total == p.total;
    }

    @Override
    public int hashCode() {
        int result = 23;
            result += 13 * from.getName().hashCode()
                    + 31 * to.getName().hashCode()
                    + 19 * Integer.valueOf(distance).hashCode()
                    + 17 * Integer.valueOf(total).hashCode();
        return result;
    }
}
