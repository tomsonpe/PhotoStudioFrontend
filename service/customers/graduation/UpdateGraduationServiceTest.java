package com.assignmenttp.service.customers.graduation;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.graduation.Graduation;
import com.assignmenttp.repository.customer.birthday.GraduationRepository;
import com.assignmenttp.repository.customer.birthday.implem.GraduationRepositoryImplem;
import com.assignmenttp.service.customer.graduation.UpdateGraduationService;
import com.assignmenttp.service.customer.graduation.implem.UpdateGraduationServiceImplem;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/13.
 */
public class UpdateGraduationServiceTest extends AndroidTestCase {
    private UpdateGraduationService service;
    private GraduationRepository repo;
    private Graduation graduation;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new GraduationRepositoryImplem(this.getContext());
    }

    public void testUpdategraduation() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), UpdateGraduationServiceImplem.class);
        UpdateGraduationService updateGraduationService = new UpdateGraduationServiceImplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Graduation graduation = new Graduation.Builder()
                .name("Encore")
                .address(address)
                .build();

        updateGraduationService.updateGraduation(App.getContext(), graduation);
        App.getContext().startService(intent);
        id = graduation.getId();

        Assert.assertNotNull("CREATE", graduation);
    }
}
