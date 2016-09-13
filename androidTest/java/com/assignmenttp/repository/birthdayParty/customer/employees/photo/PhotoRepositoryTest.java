package com.assignmenttp.repository.birthdayParty.customer.employees.photo;

import android.test.AndroidTestCase;
import android.text.AndroidCharacter;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.employees.photo.Photo;
import com.assignmenttp.repository.employees.photo.PhotoRepository;
import com.assignmenttp.repository.employees.photo.implem.PhotoReapositoryImpem;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2016/06/07.
 */
public class PhotoRepositoryTest extends AndroidTestCase {
    public static final String TAG = "PHOTO TEST";
    private Long id;

    public void testCreateUpdateDelete() throws Exception {
        PhotoRepository repo = new PhotoReapositoryImpem(this.getContext());


        //create address
        Address address = new Address.Builder()
                .postalCode("7100")
                .streetName("katali street")
                .suburb("Mfuleni")
                .build();

        //Create object
        Photo createEntity = new Photo.Builder()
                .first("Encore")
                .last("Tomson")
                .address(address)
                .build();

        //write object to database
        Photo insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);


        //READ ALL
        Set<Photo> birthdayParty = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", birthdayParty.size() > 0);

        //READ ENTITY
        Photo entity = repo.findByid(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        //UPDATE ENTITY
        Photo updateEntity = new Photo.Builder()
                .copy(entity)
                .first("Phinda")
                .build();
        repo.update(updateEntity);
        Photo newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Phinda",newEntity.getFirstName());


        //DELETE ENTITY
        repo.delete(updateEntity);
        Photo deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();
    }
}
