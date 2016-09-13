package com.assignmenttp.domain.address;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/05/13.
 */
public class Address implements Serializable{
    String postalCode;
    String streetName;
    String suburb;

    public Address(Builder address){
        this.postalCode = address.postalCode;
        this.streetName = address.streetName;
        this.suburb = address.suburb;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void viewAddress(){
        System.out.println(postalCode);
        System.out.println(streetName);
        System.out.println(suburb);
    }

    /*Create builder*/
    public static class Builder{
        String postalCode;
        String streetName;
        String suburb;

        public Builder postalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public Builder streetName(String streetName){
            this.streetName = streetName;
            return this;
        }

        public Builder suburb(String suburb){
            this.suburb = suburb;
            return this;
        }

        public Builder copy(Address address){
            this.postalCode = address.postalCode;
            this.streetName = address.streetName;
            this.suburb = address.suburb;
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }
}
