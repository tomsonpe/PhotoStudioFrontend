package com.assignmenttp.factory.employees.video;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.employees.videos.Video;

/**
 * Created by Administrator on 2016/06/06.
 */
public class VideoFactory {
    public static Video getVideoTakers(Long id,String firstName,String lastName,Address address){
        return new Video.Builder()
                .id(id)
                .first(firstName)
                .last(lastName)
                .address(address)
                .build();
    }
}
