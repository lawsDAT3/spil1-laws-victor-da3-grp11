public class Road implements Comparable<Road>
{
    private City from;
    private City to;
    private int length;

    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return from.toString() + " -> " + to.toString() + " : " + length;
    }

    public int compareTo(Road r) {
        if(from.getName().equals(r.from.getName())) {
            if (to.getName().equals(r.to.getName())) {
                return length - r.length;
            }
            return to.getName().compareTo(r.to.getName());
        }
            return from.getName().compareTo(r.from.getName());
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
        Road r = (Road) o;
        return from.getName().equals(r.from.getName()) && to.getName().equals(r.to.getName()) && length == r.length;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = result + 31 * from.getName().hashCode();
        return result;
    }
}
