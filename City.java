import java.util.ArrayList;
public class City {
    ArrayList<Connection> neighbourList;
    String name;
    int number;
    public City(int number) {
        this.number = number;
        neighbourList =new ArrayList();
    }
    public void setName(String name){
       this.name = name;
    }
    public void addConnection(City city, int length) {
        neighbourList.add(new Connection(length, this, city));
    }
    public String toString() {
        return "Miasto nr "+ number;
    }
    public void getNeighbours(){
        System.out.println("Sąsiedzi miasta " + number + ":");
        for(Connection connection : neighbourList){
            System.out.print(connection);
        }
    }
}
