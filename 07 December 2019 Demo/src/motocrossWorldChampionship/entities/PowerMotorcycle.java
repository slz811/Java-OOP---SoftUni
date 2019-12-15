package motocrossWorldChampionship.entities;

import static motocrossWorldChampionship.common.ExceptionMessages.INVALID_HORSE_POWER;

public class PowerMotorcycle extends MotorcycleImpl {
    private static final int DEFAULT_CUBIC_CENTIMETERS = 450;
    private static final int MINIMUM_HORSEPOWER = 70;
    private static final int MAXIMUM_HORSEPOWER = 100;

    public PowerMotorcycle(String model, int horsePower) {
        super(model, setHorsePower(horsePower), DEFAULT_CUBIC_CENTIMETERS);
    }


    private static int setHorsePower(int horsePower) {
        if(horsePower < MINIMUM_HORSEPOWER || horsePower > MAXIMUM_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }

        return horsePower;
    }


}
