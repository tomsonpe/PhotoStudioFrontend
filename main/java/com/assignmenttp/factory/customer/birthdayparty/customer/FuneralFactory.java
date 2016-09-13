package com.assignmenttp.factory.customer.birthdayparty.customer;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.funeral.Funeral;

/**
 * Created by Administrator on 2016/05/13.
 */
public class FuneralFactory {
    public static Funeral getFuneral(Long id,String name,Address address){
        return new Funeral.Builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }
}
