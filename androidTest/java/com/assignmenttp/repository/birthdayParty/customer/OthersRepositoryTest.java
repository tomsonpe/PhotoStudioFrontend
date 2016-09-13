package com.assignmenttp.repository.birthdayParty.customer;

import android.test.AndroidTestCase;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.others.Others;
import com.assignmenttp.repository.customer.birthday.OthersRepository;
import com.assignmenttp.repository.customer.birthday.implem.OthersRepositoryImplem;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2016/05/13.
 */
public class OthersRepositoryTest extends AndroidTestCase {
    public static final String TAG = "RELIGION TEST";
    private Long id;

    public void testcreateUpdateDelete() throws Exception {
        OthersRepository repo = new OthersRepositoryImplem(this.getContext());


        //create address
        Address address = new Address.Builder()
                .postalCode("7100")
                .streetName("katali street")
                .suburb("Mfuleni")
                .build();

        //Create object
        Others createEntity = new Others.Builder()
                .name("Encore")
                .address(address)
                .build();

        //write object to database
        Others insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);


        //READ ALL
        Set<Others> others = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", others.size() > 0);

        //READ ENTITY
        Others entity = repo.findByid(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        //UPDATE ENTITY
        Others updateEntity = new Others.Builder()
                .copy(entity)
                .name("Phinda")
                .build();
        repo.update(updateEntity);
        Others newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Phinda",newEntity.getName());


        //DELETE ENTITY
        repo.delete(updateEntity);
        Others deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();
    }
}
