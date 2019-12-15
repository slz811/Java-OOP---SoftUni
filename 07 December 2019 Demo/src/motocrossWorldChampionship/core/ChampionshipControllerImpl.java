package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static motocrossWorldChampionship.common.ExceptionMessages.*;
import static motocrossWorldChampionship.common.OutputMessages.*;

public class ChampionshipControllerImpl implements ChampionshipController {

    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Rider> riderRepository;
    private Repository<Race> raceRepository;

    public ChampionshipControllerImpl(
            Repository<Rider> riderRepository,
            Repository<Motorcycle> motorcycleRepository,
            Repository<Race> raceRepository) {

        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createRider(String riderName) {
        Rider rider = new RiderImpl(riderName);
        this.riderRepository.add(rider);
        return String.format(RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle = null;
        switch (type) {
            case "Speed":
                motorcycle = new SpeedMotorcycle(model, horsePower);
                break;

            case "Power":
                motorcycle = new PowerMotorcycle(model, horsePower);
                break;
        }
        this.motorcycleRepository.add(motorcycle);
        return String.format(MOTORCYCLE_CREATED, motorcycle.getClass().getSimpleName(), model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = this.riderRepository.getByName(riderName);

        if (rider == null) {
            throw new NullPointerException(String.format(RIDER_NOT_FOUND, riderName));
        }

        if (this.motorcycleRepository.getByName(motorcycleModel) == null) {
            throw new NullPointerException(String.format(MOTORCYCLE_NOT_FOUND, motorcycleModel));
        }

        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);

        rider.addMotorcycle(motorcycle);
        return String.format(MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new NullPointerException(String.format(RACE_NOT_FOUND, raceName));
        }

        Rider rider = this.riderRepository.getByName(riderName);

        if (rider == null) {
            throw new NullPointerException(String.format(RIDER_NOT_FOUND, riderName));
        }
        race.addRider(rider);

        return String.format(RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);

        if (race == null) {
            throw new NullPointerException(String.format(RACE_NOT_FOUND, raceName));
        }

        List<Rider> riders = new ArrayList<>(race.getRiders());

        if (riders.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, race.getName(), 3));
        }

        riders = riders.stream().sorted((a, b) -> {
            double sort = b.getMotorcycle().calculateRacePoints(race.getLaps()) -
                    a.getMotorcycle().calculateRacePoints(race.getLaps());
            return (int) (sort * 100);
        }).collect(Collectors.toList());

        riders.get(0).winRace();

        this.raceRepository.remove(race);

        return String.format(RIDER_FIRST_POSITION, riders.get(0).getName(), raceName) +
                System.lineSeparator() +
                String.format(RIDER_SECOND_POSITION, riders.get(1).getName(), raceName) +
                System.lineSeparator() +
                String.format(RIDER_THIRD_POSITION, riders.get(2).getName(), raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }
}
