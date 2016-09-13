package com.assignmenttp.factory.employees.photo;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.employees.photo.Photo;

/**
 * Created by Administrator on 2016/06/06.
 */
public class PhotoFactory {
    public static Photo getPhotoTakers(Long id,String firstName,String lastName,Address address){
        return new Photo.Builder()
                .id(id)
                .first(firstName)
                .last(lastName)
                .address(address)
                .build();
    }
}
