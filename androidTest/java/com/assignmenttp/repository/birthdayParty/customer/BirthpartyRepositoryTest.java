package com.assignmenttp.repository.birthdayParty.customer;

import android.test.AndroidTestCase;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.birthdayParty.BirthdayParty;
import com.assignmenttp.repository.customer.birthday.BirthdayPartyRepository;
import com.assignmenttp.repository.customer.birthday.implem.BirthdayPartyRepositoryImplem;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Administrator on 2016/05/13.
 */
public class BirthpartyRepositoryTest extends AndroidTestCase {
    public static final String TAG = "PARTY TEST";
    private Long id;

    public void testCreateUpdateDelete() throws Exception {
        BirthdayPartyRepository repo = new BirthdayPartyRepositoryImplem(this.getContext());


        //create address
        Address address = new Address.Builder()
                .postalCode("7100")
                .streetName("katali street")
                .suburb("Mfuleni")
                .build();

        //Create object
        BirthdayParty createEntity = new BirthdayParty.Builder()
                .name("Encore")
                .address(address)
                .build();

        //write object to database
        BirthdayParty insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);


        //READ ALL
        Set<BirthdayParty> birthdayParty = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", birthdayParty.size() > 0);

        //READ ENTITY
        BirthdayParty entity = repo.findByid(id);
        Assert.assertNotNull(TAG+" READ ENTITY", entity);

        //UPDATE ENTITY
        BirthdayParty updateEntity = new BirthdayParty.Builder()
                .copy(entity)
                .name("Phinda")
                .build();
        repo.update(updateEntity);
        BirthdayParty newEntity  = repo.findByid(id);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Phinda",newEntity.getNameOfPerson());


        //DELETE ENTITY
        repo.delete(updateEntity);
        BirthdayParty deletedEntity = repo.findByid(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

        //Detele All
        repo.deleteAll();
    }
}
