public class App {
    public static void main(String[] args) throws Exception {
        World earth = new World("Earth");
        System.out.println(earth.getName());
    }
}

class World {
    private String _name;

    public World(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
        this._name = name;
    }
}