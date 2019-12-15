package unitTesting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class RaceEntryTest {
    private static final String RIDER_ADDED = "Rider %s added in race.";

    @Test
    public void constructorShouldReturnEmptyLinkedHashMap() {
        RaceEntry raceEntry = new RaceEntry();

        Assert.assertEquals(0, raceEntry.getRiders().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingRiderThrowException() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myBestRider = new UnitRider("Sultan",
                new UnitMotorcycle("Yamaha MT09", 90, 950));
        raceEntry.addRider(myBestRider);
        raceEntry.addRider(myBestRider);
    }

    @Test(expected = NullPointerException.class)
    public void addRiderWithNonValidRiderThrowException() {
        RaceEntry raceEntry = new RaceEntry();
        raceEntry.addRider(null);
    }

    @Test
    public void addRiderShouldIncreaseRidersCount() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myRider = new UnitRider("Miro",
                new UnitMotorcycle("Yamaha YZ", 60, 450));
        raceEntry.addRider(myRider);
        Assert.assertEquals(1, raceEntry.getRiders().size());
    }

    @Test
    public void addRiderShouldReturnTheCorrectRidersName() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myRider = new UnitRider("Miro",
                new UnitMotorcycle("Yamaha YZ", 60, 450));
        raceEntry.addRider(myRider);
        UnitRider rider = raceEntry.getRiders().stream().findFirst().orElse(null);
        Assert.assertEquals("Rider Miro added in race.", String.format(RIDER_ADDED, rider.getName()));
    }

    @Test
    public void addRiderShouldReturnTheCorrectString() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myRider = new UnitRider("Miro",
                new UnitMotorcycle("Yamaha YZ", 60, 450));
        raceEntry.addRider(myRider);
        UnitRider rider = raceEntry.getRiders().stream().findFirst().orElse(null);
        String nameOfTheRider = rider.getName();
        Assert.assertEquals("Miro", nameOfTheRider);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateAverageHorsePowerWithNotEnoughParticipantsThrowException() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myBestRider = new UnitRider("Sultan",
                new UnitMotorcycle("Yamaha MT09", 90, 950));
        raceEntry.addRider(myBestRider);
        raceEntry.calculateAverageHorsePower();
    }

    @Test
    public void calculateAverageHorsePowerWithEnoughParticipants() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myBestRider = new UnitRider("Sultan",
                new UnitMotorcycle("Yamaha MT09", 95, 950));
        raceEntry.addRider(myBestRider);
        UnitRider myRider = new UnitRider("Miro",
                new UnitMotorcycle("Yamaha YZ", 60, 450));
        raceEntry.addRider(myRider);

        Assert.assertEquals(77.5, raceEntry.calculateAverageHorsePower(), 0.009);
    }

    @Test
    public void calculateAverageHorsePowerWithMoreThanMinParticipants() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myBestRider = new UnitRider("Sultan",
                new UnitMotorcycle("Yamaha MT09", 50, 950));
        raceEntry.addRider(myBestRider);
        UnitRider myRider = new UnitRider("Miro",
                new UnitMotorcycle("Yamaha YZ", 100, 450));
        raceEntry.addRider(myRider);
        UnitRider myDirtRider = new UnitRider("Miroslav",
                new UnitMotorcycle("Yamaha YZ 2012", 60, 250));
        raceEntry.addRider(myDirtRider);

        Assert.assertEquals(70, raceEntry.calculateAverageHorsePower(), 0.009);
    }

    @Test
    public void getRidersShouldReturnRidersCorrectly() {
        RaceEntry raceEntry = new RaceEntry();
        UnitRider myRider = new UnitRider("Miro",
                new UnitMotorcycle("Yamaha YZ", 60, 450));
        raceEntry.addRider(myRider);
        UnitRider myBestRider = new UnitRider("Sultan",
                new UnitMotorcycle("Yamaha MT09", 100, 950));
        raceEntry.addRider(myBestRider);

        Map<String, UnitRider> riders = new LinkedHashMap<>();
        riders.put("Miro", myRider);
        riders.put("Sultan", myBestRider);

        Assert.assertArrayEquals(riders.values().toArray(), raceEntry.getRiders().toArray());
    }

    @Test
    public void getRidersWithNoRiderExistingShouldReturnAnEmptyCollection() {
        RaceEntry raceEntry = new RaceEntry();
        Map<String, UnitRider> riders = new LinkedHashMap<>();
        Assert.assertArrayEquals(riders.values().toArray(), raceEntry.getRiders().toArray());
    }

    @Test
    public void shouldReturnUnmodifiableCollection() {
        RaceEntry raceEntry = new RaceEntry();
        Collection<UnitRider> collection = raceEntry.getRiders();
        Assert.assertEquals("UnmodifiableCollection", collection.getClass().getSimpleName());
    }
}

