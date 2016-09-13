package com.assignmenttp.service.customers.birthdayParty;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.birthdayParty.BirthdayParty;
import com.assignmenttp.repository.customer.birthday.BirthdayPartyRepository;
import com.assignmenttp.repository.customer.birthday.implem.BirthdayPartyRepositoryImplem;
import com.assignmenttp.service.customer.birthdayParty.BirthdaypartyService;
import com.assignmenttp.service.customer.birthdayParty.implem.BirthdayPartyServiceImplem;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/13.
 */
public class AddBirthdayPartyServiceTest extends AndroidTestCase {
    private BirthdaypartyService service;
    private BirthdayPartyRepository repo;
    private BirthdayParty party;


    @Override
    public void setUp() throws Exception {
        super.setUp();
         repo = new BirthdayPartyRepositoryImplem(this.getContext());
    }

    public void testAddParty() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), BirthdayPartyServiceImplem.class);
        BirthdaypartyService party = new BirthdayPartyServiceImplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        BirthdayParty party1 = new BirthdayParty.Builder()
                .name("Encore")
                .address(address)
                .build();

        party.addParty(App.getContext(), party1);
        App.getContext().startService(intent);
        id = party1.getId();

        Assert.assertNotNull("CREATE", party1);;
    }
}
