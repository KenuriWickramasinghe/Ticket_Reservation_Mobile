package com.example.mobile_travelreservation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    @SerializedName("Id")
    @Expose
    private String Id;

    @SerializedName("Departure")
    @Expose
    private String Departure;

    @SerializedName("Arrival")
    @Expose
    private String Arrival;

    @SerializedName("Date")
    @Expose
    private LocalDate Date;

    @SerializedName("Time")
    @Expose
    private String Time;

    @SerializedName("TicketCount")
    @Expose
    private int TicketCount;

    @SerializedName("TicketClass")
    @Expose
    private String TicketClass;

    @SerializedName("TrainId")
    @Expose
    private String TrainId;

    @SerializedName("TravelerId")
    @Expose
    private String TravelerId;

    public Reservation() {
    }

    public Reservation(String id, String departure, String arrival, LocalDate date, String time, int ticketCount, String ticketClass, String trainId, String travelerId) {
        Id = id;
        Departure = departure;
        Arrival = arrival;
        Date = date;
        Time = time;
        TicketCount = ticketCount;
        TicketClass = ticketClass;
        TrainId = trainId;
        TravelerId = travelerId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getArrival() {
        return Arrival;
    }

    public void setArrival(String arrival) {
        Arrival = arrival;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getTicketCount() {
        return TicketCount;
    }

    public void setTicketCount(int ticketCount) {
        TicketCount = ticketCount;
    }

    public String getTicketClass() {
        return TicketClass;
    }

    public void setTicketClass(String ticketClass) {
        TicketClass = ticketClass;
    }

    public String getTrainId() {
        return TrainId;
    }

    public void setTrainId(String trainId) {
        TrainId = trainId;
    }

    public String getTravelerId() {
        return TravelerId;
    }

    public void setTravelerId(String travelerId) {
        TravelerId = travelerId;
    }
}
