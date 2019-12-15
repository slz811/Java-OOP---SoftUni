package aquarium.models.aquariums;

import aquarium.models.fish.Fish;

public class SaltwaterAquarium extends BaseAquarium {
    public SaltwaterAquarium(String name) {
        super(name, 25);
    }


    @Override
    public void addFish(Fish fish) {
        if (this.getFish().size() == this.getCapacity()) {
            throw new IllegalStateException("Not enough capacity.");
        }

        if (fish.getClass().getSimpleName().equals("SaltwaterFish")) {
            this.getFish().add(fish);
        }
    }
}
