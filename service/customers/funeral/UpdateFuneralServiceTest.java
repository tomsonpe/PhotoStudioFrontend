package com.assignmenttp.service.customers.funeral;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.funeral.Funeral;
import com.assignmenttp.repository.customer.birthday.FuneralReapository;
import com.assignmenttp.repository.customer.birthday.implem.FuneralRepositoryImplem;
import com.assignmenttp.service.customer.funeral.UpdateFuneralService;
import com.assignmenttp.service.customer.funeral.implem.UpdateFuneralServiceImplem;
import com.assignmenttp.service.customer.graduation.UpdateGraduationService;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/13.
 */
public class UpdateFuneralServiceTest extends AndroidTestCase{
    private UpdateGraduationService service;
    private FuneralReapository repo;
    private Funeral funeral;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new FuneralRepositoryImplem(this.getContext());
    }

    public void testUpdateFuneral() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), UpdateFuneralServiceImplem.class);
        UpdateFuneralService updateFuneralService = new UpdateFuneralServiceImplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Funeral fun = new Funeral.Builder()
                .name("Encore")
                .address(address)
                .build();

        updateFuneralService.updateFuneral(App.getContext(), fun);
        App.getContext().startService(intent);
        id = fun.getId();

        Assert.assertNotNull("CREATE", fun);;
    }
}
