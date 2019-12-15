package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static motocrossWorldChampionship.common.ExceptionMessages.*;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Map<String, Rider> riders;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.riders = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS, 1));
        }
        this.laps = laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return new ArrayList<>(this.riders.values());
    }

    @Override
    public void addRider(Rider rider) {

        if (rider == null) {
            throw new NullPointerException(RIDER_INVALID);
        }

        if (rider.getMotorcycle() == null) {
            throw new IllegalArgumentException(String.format(RIDER_NOT_PARTICIPATE, rider.getName()));
        }

        if (this.riders.containsKey(rider.getName())) {
            throw new IllegalArgumentException(String.format(RIDER_ALREADY_ADDED, rider.getName(), this.name));
        }

        this.riders.put(rider.getName(), rider);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Race)) {
            return false;
        }
        Race race = (Race) obj;
        return this.getName().equals(race.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
