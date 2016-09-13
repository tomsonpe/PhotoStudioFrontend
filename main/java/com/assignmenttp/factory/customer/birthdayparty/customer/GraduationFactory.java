package com.assignmenttp.factory.customer.birthdayparty.customer;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.graduation.Graduation;

/**
 * Created by Administrator on 2016/05/13.
 */
public class GraduationFactory {
    public static Graduation getFuneral(Long id,String name,Address address){
        return new Graduation.Builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }
}
