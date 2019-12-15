package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {
    @Test(expected = NullPointerException.class)
    public void validateNameWithNullParametersThrowException() {
        new Aquarium(null, 10);
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        Aquarium aquarium = new Aquarium("Aqua", 10);
        Assert.assertEquals("Aqua", aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateCapacityWithNegativeNumberThrowException() {
        new Aquarium("Aqua", -2);
    }

    @Test
    public void validateCapacityShouldReturnItCorrectValue() {
        Aquarium aquarium = new Aquarium("Aqua", 10);
        Assert.assertEquals(10, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateCapacityOverloadedThrowException() {
        Aquarium aquarium = new Aquarium("Aqua", 1);
        aquarium.add(new Fish("Salmon"));
        aquarium.add(new Fish("Tsatsaa"));

    }

    @Test
    public void addingNewObjectShouldIncreaseCountCorrectly() {
        Aquarium aquarium = new Aquarium("Aqua", 10);
        aquarium.add(new Fish("Salmon"));
        aquarium.add(new Fish("Tsatsaa"));
        Assert.assertEquals(2, aquarium.getCount());

    }


    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingElementThrowException() {
        Aquarium aquarium = new Aquarium("Aqua", 1);
        Fish fish = new Fish("Salmon");
        Fish fish2 = new Fish("Nemo");
        aquarium.add(fish);
        aquarium.remove(fish2.getName());

    }

    @Test
    public void removeElementReturnsCorrectCount() {
        Aquarium aquarium = new Aquarium("Aqua", 10);
        Fish fish = new Fish("Salmon");
        Fish fish2 = new Fish("Nemo");
        aquarium.add(fish);
        aquarium.add(fish2);
        aquarium.remove(fish2.getName());
        Assert.assertEquals(1, aquarium.getCount());

    }


    @Test
    public void sellFishShouldSetAvailiableFalse() {
        Aquarium aquarium = new Aquarium("Aqua", 1);
        Fish fish = new Fish("Salmon");
        Fish fish2 = new Fish("Nemo");
        aquarium.add(fish);
        aquarium.sellFish(fish.getName());
        Assert.assertFalse(fish.isAvailable());

    }

    @Test(expected = IllegalArgumentException.class)
    public void sellFishWithNonExistingFishThrowsException() {
        Aquarium aquarium = new Aquarium("Aqua", 1);
        Fish fish = new Fish("Salmon");
        aquarium.add(fish);
        aquarium.sellFish("Nemo");

    }
    @Test
    public void reportShouldReturnTheCorrectResult() {
        Aquarium aquarium = new Aquarium("Aqua", 10);
        Fish fish = new Fish("Salmon");
        Fish fish2 = new Fish("Nemo");
        aquarium.add(fish);
        aquarium.add(fish2);
        Assert.assertEquals("Fish available at Aqua: Salmon, Nemo",aquarium.report());

    }
}