package aquarium.models.fish;

public class FreshwaterFish extends BaseFish {
    private final static int INITIAL_SIZE = 3;
    private final static int INCREASING_MOD = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + INCREASING_MOD);
    }


}
