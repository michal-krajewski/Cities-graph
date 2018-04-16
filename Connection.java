import java.util.ArrayList;
public class Connection {
    int length;
    City from;
    City to;
    ArrayList<Connection> connectionsArray;
    public Connection(int length, City from, City to) {
        this.length=length;
        this.from=from;
        this.to=to;
        connectionsArray = new ArrayList();
        connectionsArray.add(this);
    }
    public Connection(City from, City to, Connection prevConnection, Connection current) {
        this.from = from;
        this.to = to;
        this.length = current.length + prevConnection.length;
        this.connectionsArray = new ArrayList(prevConnection.connectionsArray);
        this.connectionsArray.add(current);
    }

    public String toString() {
        return (from.number + " do " + to.number + ": " + length + "\n");
    }
}