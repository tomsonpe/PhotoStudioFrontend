package com.assignmenttp.factory.customer.birthdayparty.customer;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.religion.Religion;

/**
 * Created by Administrator on 2016/05/13.
 */
public class ReligionFactory {
    public static Religion getFuneral(Long id,String name,Address address){
        return new Religion.Builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }
}
