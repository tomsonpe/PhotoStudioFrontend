package com.assignmenttp.factory.customer.birthdayparty.customer;

import com.assignmenttp.domain.address.Address;

/**
 * Created by Administrator on 2016/05/13.
 */
public class OthersFactory {
    public static Address getAddress(String postalCode,String streetName,String suburb){
        return new Address.Builder()
                .postalCode(postalCode)
                .streetName(streetName)
                .suburb(suburb)
                .build();
    }
}
