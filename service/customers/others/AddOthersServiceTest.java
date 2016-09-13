package com.assignmenttp.service.customers.others;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.others.Others;
import com.assignmenttp.repository.customer.birthday.OthersRepository;
import com.assignmenttp.repository.customer.birthday.implem.OthersRepositoryImplem;
import com.assignmenttp.service.customer.others.AddOthersService;
import com.assignmenttp.service.customer.others.implem.AddOthersServiceimplem;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/14.
 */
public class AddOthersServiceTest extends AndroidTestCase {
    private AddOthersService service;
    private OthersRepository repo;
    private Others others;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new OthersRepositoryImplem(this.getContext());
    }

    public void testAddOthers() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), AddOthersServiceimplem.class);
        AddOthersService addOthersService = new AddOthersServiceimplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Others other = new Others.Builder()
                .name("Encore")
                .address(address)
                .build();

        addOthersService.addOthers(App.getContext(), other);
        App.getContext().startService(intent);
        id = other.getId();

        Assert.assertNotNull("CREATE", other);
    }
}
