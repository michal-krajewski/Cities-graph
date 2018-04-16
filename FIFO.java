import java.util.ArrayList;

public class FIFO {
    ArrayList<City> list;
    public FIFO() {
        list=new ArrayList();
    }
    public City get() {
        City out=list.get(0);
        list.remove(0);
        return out;
    }
    public void add(City c) {
        list.add(c);
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
