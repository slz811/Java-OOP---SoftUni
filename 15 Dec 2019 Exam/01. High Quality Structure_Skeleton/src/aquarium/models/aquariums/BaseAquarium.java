package aquarium.models.aquariums;

import aquarium.common.ExceptionMessages;
import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;


    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    protected int getCapacity() {
        return this.capacity;
    }

    @Override
    public int calculateComfort() {
        int sum = 0;
        for (Decoration decoration : this.decorations) {
            sum += decoration.getComfort();
        }
        return sum;
    }


    @Override
    public void addFish(Fish fish) {
        if (this.getFish().size() == this.getCapacity()) {
            throw new IllegalStateException("Not enough capacity.");
        }
        this.fish.add(fish);

    }

    @Override
    public void removeFish(Fish fish) {
        Fish fishToRemove = this.fish.stream()
                .filter(f -> name.equals(f.getName()))
                .findAny()
                .orElse(null);

        //  if (fishToRemove != null) {
        this.fish.remove(fishToRemove);
        //  }

    }


    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        String type = " (" + this.getClass().getSimpleName() + "):";
        sb.append(this.getName()).append(type);
        sb.append(System.lineSeparator());

        String fishCollection = "none";
        if (this.fish.size() > 0) {
            fishCollection = this.fish
                    .stream()
                    .map(Fish::getName)
                    .collect(Collectors.joining(" "));
        }
        sb.append("Fish: ").append(fishCollection);
        sb.append(System.lineSeparator());
        sb.append("Decorations: ").append(this.decorations.size());
        sb.append(System.lineSeparator());
        sb.append("Comfort: ").append(this.calculateComfort());

        return sb.toString();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}