package com.assignmenttp.service.customers.funeral;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.funeral.Funeral;
import com.assignmenttp.repository.customer.birthday.FuneralReapository;
import com.assignmenttp.repository.customer.birthday.implem.FuneralRepositoryImplem;
import com.assignmenttp.service.customer.funeral.FuneralService;
import com.assignmenttp.service.customer.funeral.implem.FuneralServiceImplem;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/13.
 */
public class FuneralServiceTest extends AndroidTestCase {
    private FuneralService service;
    private FuneralReapository repo;
    private Funeral funeral;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new FuneralRepositoryImplem(this.getContext());
    }

    public void testAddFuneral() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), FuneralServiceImplem.class);
        FuneralService funeralService = new FuneralServiceImplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Funeral fun = new Funeral.Builder()
                .name("Encore")
                .address(address)
                .build();

        funeralService.addFuneral(App.getContext(), fun);
        App.getContext().startService(intent);
        id = fun.getId();

        Assert.assertNotNull("CREATE", fun);;
    }
}
