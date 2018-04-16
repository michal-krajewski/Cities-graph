import java.util.Comparator;

public class CityComparator implements Comparator<City> {

    public int compare(City arg0, City arg1) {
        return arg0.number - arg1.number;
    }
}
