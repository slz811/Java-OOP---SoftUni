package aquarium.models.aquariums;

import aquarium.models.fish.Fish;

public class FreshwaterAquarium extends BaseAquarium {
    public FreshwaterAquarium(String name) {
        super(name, 50);
    }


    @Override
    public void addFish(Fish fish) {
        if (this.getFish().size() == this.getCapacity()) {
            throw new IllegalStateException("Not enough capacity.");
        }

        if (fish.getClass().getSimpleName().equals("FreshwaterFish")) {
            this.getFish().add(fish);
        }
    }
}
