/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.park.newparkinglot.common;

import java.io.Serializable;

/**
 *
 * @author radvo
 */
public class CarDetails implements Serializable{
    
    private Integer id;
    private String licensePlate;
    private String parkingSpot;
    private String username;

    public CarDetails(Integer id, String licensePlate, String parkingSpot, String username) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
    
    
}
