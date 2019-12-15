package motocrossWorldChampionship;

import motocrossWorldChampionship.core.ChampionshipControllerImpl;
import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;
import motocrossWorldChampionship.repositories.interfaces.Repository;

public class Main {
    public static void main(String[] args) {

        Repository<Motorcycle> motorcycleRepository = new MotorcycleRepository();
        Repository<Rider> riderRepository = new RiderRepository();
        Repository<Race> raceRepository = new RaceRepository();

        ChampionshipController controller = new ChampionshipControllerImpl(
                riderRepository,
                motorcycleRepository,
                raceRepository);
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
