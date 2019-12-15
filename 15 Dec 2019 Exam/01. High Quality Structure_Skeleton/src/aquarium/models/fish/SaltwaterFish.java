package aquarium.models.fish;

public class SaltwaterFish extends BaseFish {
    private final static int INITIAL_SIZE = 5;
    private final static int INCREASING_MOD = 2;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + INCREASING_MOD);
    }
}
