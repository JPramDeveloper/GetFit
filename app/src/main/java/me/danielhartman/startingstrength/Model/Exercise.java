package me.danielhartman.startingstrength.model;



import java.util.List;
import java.util.Set;

public class Exercise{
    String name;
    List<Set> sets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }
}
