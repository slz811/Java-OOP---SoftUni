package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;

import java.util.Scanner;

public class EngineImpl implements Engine {

    private ChampionshipController controller;
    private Scanner sc;

    public EngineImpl(ChampionshipController controller) {
        this.controller = controller;
        this.sc = new Scanner(System.in);
    }

    @Override
    public void run() {

        while (true) {
            String result = null;
            try {
                result = processInput();
                if("End".equals(result)) {
                    break;
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }
            System.out.println(result);
        }
    }


    private String processInput() {
        String input = this.sc.nextLine();
        String[] data = input.split("\\s+");
        String command = data[0];
        String result = "";
        switch (command) {
            case "CreateRider":
                result = this.controller.createRider(data[1]);
                break;

            case "CreateMotorcycle":
                result = this.controller.createMotorcycle(data[1], data[2], Integer.parseInt(data[3]));
                break;

            case "AddMotorcycleToRider":
                result = this.controller.addMotorcycleToRider(data[1], data[2]);
                break;

            case "AddRiderToRace":
                result = this.controller.addRiderToRace(data[1], data[2]);
                break;

            case "CreateRace":
                result = this.controller.createRace(data[1], Integer.parseInt(data[2]));
                break;

            case "StartRace":
                result = this.controller.startRace(data[1]);
                break;
            case "End":
                result = "End";
                break;
        }
        return result.trim();
    }
}


