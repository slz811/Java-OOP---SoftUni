package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

import static motocrossWorldChampionship.common.ExceptionMessages.RIDER_EXISTS;

public class RiderRepository implements Repository<Rider> {

    private Collection<Rider> models;

    public RiderRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Rider getByName(String name) {
        Rider rider = null;
        for (Rider model : models) {
            if(model.getName().equals(name)){
                rider = model;
            }
        }
        return rider;
    }

    @Override
    public Collection<Rider> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Rider model) {
        if(this.models.contains(model)) {
            throw new IllegalArgumentException(String.format(RIDER_EXISTS, model.getName()));
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Rider model) {
        return this.models.remove(model);
    }
}
