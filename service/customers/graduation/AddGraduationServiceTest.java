package com.assignmenttp.service.customers.graduation;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.graduation.Graduation;
import com.assignmenttp.repository.customer.birthday.GraduationRepository;
import com.assignmenttp.repository.customer.birthday.implem.GraduationRepositoryImplem;
import com.assignmenttp.service.customer.graduation.AddGraduationService;
import com.assignmenttp.service.customer.graduation.implem.AddGraduationServiceImplem;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/13.
 */
public class AddGraduationServiceTest extends AndroidTestCase {
    private AddGraduationService service;
    private GraduationRepository repo;
    private Graduation graduation;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new GraduationRepositoryImplem(this.getContext());
    }

    public void testAddGraduation() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), AddGraduationServiceImplem.class);
        AddGraduationService addGraduationService = new AddGraduationServiceImplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Graduation graduation = new Graduation.Builder()
                .name("Encore")
                .address(address)
                .build();

        addGraduationService.addGraduation(App.getContext(), graduation);
        App.getContext().startService(intent);
        id = graduation.getId();

        Assert.assertNotNull("CREATE", graduation);
    }
}
