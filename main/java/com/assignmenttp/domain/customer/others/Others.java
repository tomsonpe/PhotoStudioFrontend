package com.assignmenttp.domain.customer.others;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.Customer;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/05/13.
 */
public class Others implements Customer,Serializable{
    private Long id;
    private String event_name;
    private Address address=new Address.Builder().streetName("katali").suburb("Mfuleni").postalCode("7100").build();

    public Long getId(){return id;}

    public String getName(){
        return event_name;
    }
    public Address getAddress(){return address;}
    public String getPostalCode(){
        return address.getPostalCode();
    }

    public String getStreetName(){
        return address.getStreetName();
    }

    public String getSuburb(){
        return address.getSuburb();
    }
    public Others(Builder builder){
        this.id=builder.id;
        this.event_name=builder.event_name;
    }

    public static class Builder{
        private Long id;
        private String event_name;
        private Address address;

        public Builder id(Long id){
            this.id=id;
            return this;
        }


        public Builder name(String event_name){
            this.event_name=event_name;
            return this;
        }
        public Builder address(Address address){
            this.address=address;
            return this;
        }

        public Builder copy(Others grad){
            this.id=grad.getId();
            this.event_name=grad.getName();
            this.address=grad.address;
            return this;
        }

        public Others build(){
            return new Others(this);
        }
    }
    public void displayTypeOfCustomer(){
        System.out.println("Type of Customer: others");
    }
}
