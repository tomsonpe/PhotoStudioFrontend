package com.assignmenttp.repository.birthdayParty.customer;

import android.test.AndroidTestCase;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.religion.Religion;
import com.assignmenttp.repository.customer.birthday.ReligionRepository;
import com.assignmenttp.repository.customer.birthday.implem.ReligionRepositoryImplem;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2016/05/13.
 */
public class ReligionRepositoryTest extends AndroidTestCase {
    public static final String TAG = "RELIGION TEST";
    private Long id;

    public void testcreateUpdateDelete() throws Exception {
        ReligionRepository repo = new ReligionRepositoryImplem(this.getContext());


        //create address
        Address address = new Address.Builder()
                .postalCode("7100")
                .streetName("katali street")
                .suburb("Mfuleni")
                .build();

        //Create object
        Religion createEntity = new Religion.Builder()
                .name("Encore")
                .address(address)
                .build();

        //write object to database
        Religion insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);


        //READ ALL
        Set<Religion> birthdayParty = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", birthdayParty.size() > 0);

        //READ ENTITY
        Religion entity = repo.findByid(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        //UPDATE ENTITY
        Religion updateEntity = new Religion.Builder()
                .copy(entity)
                .name("Phinda")
                .build();
        repo.update(updateEntity);
        Religion newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Phinda",newEntity.getName());


        //DELETE ENTITY
        repo.delete(updateEntity);
        Religion deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();
    }
}
