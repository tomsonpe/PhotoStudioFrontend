package com.assignmenttp.repository.birthdayParty.customer;

import android.test.AndroidTestCase;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.graduation.Graduation;
import com.assignmenttp.repository.customer.birthday.implem.GraduationRepositoryImplem;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2016/05/13.
 */
public class GraduationRepository extends AndroidTestCase {
    public static final String TAG = "Graduation TEST";
    private Long id;

    public void testcreateUpdateDelete() throws Exception {
        GraduationRepositoryImplem repo=new GraduationRepositoryImplem(this.getContext());


        //create address
        Address address = new Address.Builder()
                .postalCode("7100")
                .streetName("katali street")
                .suburb("Mfuleni")
                .build();

        //Create object
        Graduation createEntity = new Graduation.Builder()
                .name("Encore")
                .address(address)
                .build();

        //write object to database
        Graduation insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);


        //READ ALL
        Set<Graduation> funeral = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", funeral.size() > 0);

        //READ ENTITY
        Graduation entity = repo.findByid(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        //UPDATE ENTITY
        Graduation updateEntity = new Graduation.Builder()
                .copy(entity)
                .name("Phinda")
                .build();
        repo.update(updateEntity);
        Graduation newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Phinda",newEntity.getName());


        //DELETE ENTITY
        repo.delete(updateEntity);
        Graduation deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();
    }
}
