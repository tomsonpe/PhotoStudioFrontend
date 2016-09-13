package com.assignmenttp.service.customers.others;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.others.Others;
import com.assignmenttp.repository.customer.birthday.OthersRepository;
import com.assignmenttp.repository.customer.birthday.implem.OthersRepositoryImplem;
import com.assignmenttp.service.customer.others.UpdateOthersService;
import com.assignmenttp.service.customer.others.implem.UpdateOthersServiceImplem;

import junit.framework.Assert;

/**
 * Created by Administrator on 2016/05/14.
 */
public class UpdateOthersServiceTest extends AndroidTestCase {
    private UpdateOthersService service;
    private OthersRepository repo;
    private Others others;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new OthersRepositoryImplem(this.getContext());
    }

    public void testUpdateOthers() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), UpdateOthersServiceImplem.class);
        UpdateOthersService addOthersService = new UpdateOthersServiceImplem();
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
