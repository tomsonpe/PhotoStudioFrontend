package com.assignmenttp.factory.customer.birthdayparty.customer;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.birthdayParty.BirthdayParty;

/**
 * Created by Administrator on 2016/05/13.
 */
public class BirthdayPartyFactory {
    public static BirthdayParty getCustomer(Long id,String name,Address address){
        return new BirthdayParty.Builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }
}
