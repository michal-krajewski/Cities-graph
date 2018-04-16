import java.util.ArrayList;

public class PriorityQueue {
    ArrayList<Connection> list;
    public PriorityQueue() {
        list=new ArrayList();
    }
    public void add(Connection c) {
        if (list.isEmpty()) {
            list.add(c);
        }
        else {
            for (int i=0; i<list.size(); i++) {
                Connection temp=list.get(i);
                if (temp.length >= c.length) {
                    list.add(i, c);
                    return;
                }
            }
            list.add(c);
        }
    }
    public void addAll(ArrayList<Connection> cList) {
        for (Connection c:cList) {
            if (list.isEmpty()) {
                list.add(c);
            }
            else {
                int i=0;
                for (; i<list.size(); i++) {
                    Connection temp=list.get(i);
                    if (temp.length >= c.length) {
                        break;
                    }
                }
                list.add(i, c);
            }
        }
    }
    public Connection get() {
        Connection out=list.get(0);
        list.remove(0);
        return out;
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public String toString() {
        String out="";
        for (Connection c:list)
        {
            out+=c.toString();
        }
        return out;
    }
}
