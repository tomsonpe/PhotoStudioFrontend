package com.assignmenttp.domain.employees.photo;

import com.assignmenttp.domain.address.Address;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/06/06.
 */
public class Photo implements Serializable{
    Long id;
    String firstName;
    String lastName;
    Address address;

    public Photo(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.firstName = builder.lastName;
        this.address = builder.address;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPostalCode() {
        return address.getPostalCode();
    }

    public String getStreetName() {
        return address.getStreetName();
    }

    public String getSuburb() {
        return address.getSuburb();
    }

    public static class Builder {
        Long id;
        String firstName;
        String lastName;
        Address address;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder first(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder last(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder copy(Photo photo) {
            this.id = photo.id;
            this.firstName = photo.firstName;
            this.lastName = photo.lastName;
            this.address = photo.address;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }
}
