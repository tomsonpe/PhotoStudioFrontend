package com.assignmenttp.repository.birthdayParty.customer;

import android.test.AndroidTestCase;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.funeral.Funeral;
import com.assignmenttp.repository.customer.birthday.FuneralReapository;
import com.assignmenttp.repository.customer.birthday.implem.FuneralRepositoryImplem;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2016/05/13.
 */
public class FuneralRepositoryTest extends AndroidTestCase {
    public static final String TAG = "FUNERAL TEST";
    private Long id;

    public void testcreateUpdateDelete() throws Exception {
        FuneralReapository repo = new FuneralRepositoryImplem(this.getContext());


        //create address
        Address address = new Address.Builder()
                .postalCode("7100")
                .streetName("katali street")
                .suburb("Mfuleni")
                .build();

        //Create object
        Funeral createEntity = new Funeral.Builder()
                .name("Encore")
                .address(address)
                .build();

        //write object to database
        Funeral insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);


        //READ ALL
        Set<Funeral> funeral = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", funeral.size() > 0);

        //READ ENTITY
        Funeral entity = repo.findByid(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        //UPDATE ENTITY
        Funeral updateEntity = new Funeral.Builder()
                .copy(entity)
                .name("Phinda")
                .build();
        repo.update(updateEntity);
        Funeral newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Phinda",newEntity.getName());


        //DELETE ENTITY
        repo.delete(updateEntity);
        Funeral deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();
    }
}
