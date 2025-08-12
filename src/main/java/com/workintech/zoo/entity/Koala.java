package com.workintech.zoo.entity;

import java.util.Objects;


public class Koala {
    private int id;
    private String name;
    private double weight;
    private double sleepHour;
    private String gender;

    public Koala() {
    }

    public Koala(int id, String name, double sleepHour, double weight, String gender) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.sleepHour = sleepHour;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSleepHour() {
        return sleepHour;
    }

    public void setSleepHour(double sleepHour) {
        this.sleepHour = sleepHour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Koala koala = (Koala) o;
        return id == koala.id && Double.compare(weight, koala.weight) == 0 && Double.compare(sleepHour, koala.sleepHour) == 0 && Objects.equals(name, koala.name) && Objects.equals(gender, koala.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, sleepHour, gender);
    }

    @Override
    public String toString() {
        return "Koala{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", sleepHour=" + sleepHour +
                ", gender='" + gender + '\'' +
                '}';
    }
}
