package com.doderasoft.onlinebooking.model;

import java.time.LocalDate;
import java.util.UUID;

public class Event {


    private final UUID id;
    private final String title;
    private final Artist artist;
    private final EventLocation location;
    private int availableTickets;
    private int ticketsSold;
    private float ticketPrice;
    private LocalDate date;


    public Event(UUID id, String title, Artist artist, EventLocation location, int availableTickets, int ticketsSold, float ticketPrice, LocalDate date) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.location = location;
        this.availableTickets = availableTickets;
        this.ticketsSold = ticketsSold;
        this.ticketPrice = ticketPrice;
        this.date = date;
    }



    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public EventLocation getLocation() {
        return location;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }



    public static class Artist {
        private final String name;
        private final String age;
        private final String nickname;


        public Artist(String name, String age, String nickname) {
            this.name = name;
            this.age = age;
            this.nickname = nickname;
        }



        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }

        public String getNickname() {
            return nickname;
        }
    }

    public static class EventLocation {

        private final String placeName;
        private final String city;
        private final String country;


        public EventLocation(String placeName, String city, String country) {
            this.placeName = placeName;
            this.city = city;
            this.country = country;
        }


        public String getPlaceName() {
            return placeName;
        }

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }
    }

}
