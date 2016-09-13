package com.assignmenttp.repository.birthdayParty.customer.employees.video;

import android.test.AndroidTestCase;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.employees.videos.Video;
import com.assignmenttp.repository.employees.video.VideoRepository;
import com.assignmenttp.repository.employees.video.implem.VideoRepositoryImplem;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2016/06/07.
 */
public class VideoRepositoryTest extends AndroidTestCase {
    public static final String TAG = "VIDEO TEST";
    private Long id;

    public void testCreateUpdateDelete() throws Exception {
        VideoRepository repo = new VideoRepositoryImplem(this.getContext());


        //create address
        Address address = new Address.Builder()
                .postalCode("7100")
                .streetName("katali street")
                .suburb("Mfuleni")
                .build();

        //Create object
        Video createEntity = new Video.Builder()
                .first("Encore")
                .last("Tomson")
                .address(address)
                .build();

        //write object to database
        Video insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);


        //READ ALL
        Set<Video> birthdayParty = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", birthdayParty.size() > 0);

        //READ ENTITY
        Video entity = repo.findByid(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        //UPDATE ENTITY
        Video updateEntity = new Video.Builder()
                .copy(entity)
                .first("Phinda")
                .build();
        repo.update(updateEntity);
        Video newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Phinda",newEntity.getFirstName());


        //DELETE ENTITY
        repo.delete(updateEntity);
        Video deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();
    }
}
