import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Atres on 04.06.2017.
 */
public class Graph {
    ArrayList <City> citiesList;
    int citiesAmount;
    public Graph(int citiesAmount){
        this.citiesAmount = citiesAmount;
        citiesList = new ArrayList();
        try{
            createGraph();
        }
        catch (Exception e){
            System.out.println("Wystąpił błąd!");
        }
    }
    public String getPossibleRoute(int fromNumber) {
        City from = getCity(fromNumber);
        String out = ("Możliwe połączenia z miasta nr " + from.number +"\n");
        ArrayList<City> neighbours = new ArrayList();
        FIFO fifo = new FIFO();
        for (Connection connection : from.neighbourList) {
            fifo.add(connection.to);
        }
        while (!fifo.isEmpty()) {
            City tempCity = fifo.get();
            if (!neighbours.contains(tempCity)){
                neighbours.add(tempCity);
            }
            for (Connection connection : tempCity.neighbourList) {
                if (!neighbours.contains(connection.to)&&connection.to!=from) {
                    fifo.add(connection.to);
                }
            }
        }
        neighbours.sort(new CityComparator());
        for (City c:neighbours)
        {
            out+=c+"\n";
        }
        return out;
    }
    public String shortestPath(int fromNumber, int toNumber) {
        City from = getCity(fromNumber);
        City to = getCity(toNumber);
        String out = "Najkrótsza droga:\n";
        ArrayList<Connection> list=findPath(from, to);
        if (list == null) {
            return out + "Brak połączenia pomiędzy miastami!";
        }
        out += "Z miasta " + from.number + " do miasta " + toNumber;
        int length = 0;
        for (Connection connection:list) {
            length += connection.length;
        }
        out+=" wynosi "+length+"\n";
        return out;
    }
    public void getCityNeighbours(int cityNumber){
        getCity(cityNumber).getNeighbours();
    }
    public void wglab(){
        FIFO stos = new FIFO();
        ArrayList <City> visited = new ArrayList<>();
        stos.add(getCity(0));
        while(!stos.isEmpty()){
            City city = stos.get();
            visited.add(city);
            for(Connection connection : city.neighbourList){
                if(!visited.contains(connection.to) && !stos.list.contains(connection.to)){
                    stos.list.add(0, connection.to);
                }
            }
        }
        System.out.println("Wyświetlanie wgłąb: ");
        for(City city : visited){
            System.out.println(city);
        }
    }
    private void createGraph() throws Exception{
        FileReader fr = new FileReader("cities.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        addCities();
        int cityNumber = 0;
        while (line != null){
            if(!line.equals("")) {
                String tempArray[] = line.split(" ");
                for (int i = 0; i < tempArray.length; i++) {
                    City destination = citiesList.get(Integer.parseInt(tempArray[i]));
                    citiesList.get(cityNumber).addConnection(destination, Integer.parseInt(tempArray[++i]));
                }
                cityNumber++;
            }
            else {
                cityNumber++;
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
    }
    private void addCities(){
        for(int i = 0; i < citiesAmount; i++){
            citiesList.add(new City(i));
        }
    }
    private City getCity(int code) {
        for (City city:citiesList) {
            if (city.number == code) return city;
        }
        return null;
    }
    private ArrayList<Connection> findPath(City from, City to) {
        ArrayList<City> visited = new ArrayList();
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.addAll(from.neighbourList);
        visited.add(from);
        while (!priorityQueue.isEmpty()) {
            Connection temp=priorityQueue.get();
            if (temp.to == to) {
                return temp.connectionsArray;
            }
            for (Connection con:temp.to.neighbourList) {
                if (!visited.contains(con.to)) {
                    priorityQueue.add(new Connection(from, con.to, temp, con));
                }
            }
            visited.add(temp.to);
        }
        return null;
    }


}
