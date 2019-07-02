package main.java.part01.lesson07.task01;

import java.io.Serializable;


/**
 * Class City with Serializable
 * @author L
 */
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    private String city;

    public City(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
