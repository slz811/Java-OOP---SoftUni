package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

import static motocrossWorldChampionship.common.ExceptionMessages.RACE_EXISTS;

public class RaceRepository implements Repository<Race> {

    private Collection<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        Race race = null;
        for (Race model : models) {
            if(model.getName().equals(name)){
                race = model;
            }
        }
        return race;
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Race model) {
        if (this.models.contains(model)) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, model.getName()));
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return this.models.remove(model);
    }
}
