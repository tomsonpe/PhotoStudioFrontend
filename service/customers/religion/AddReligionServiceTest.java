package com.assignmenttp.service.customers.religion;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.religion.Religion;
import com.assignmenttp.repository.customer.birthday.ReligionRepository;
import com.assignmenttp.repository.customer.birthday.implem.ReligionRepositoryImplem;
import com.assignmenttp.service.customer.rel.AddReligionService;
import com.assignmenttp.service.customer.rel.implem.AddReligionServiceimplem;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/14.
 */
public class AddReligionServiceTest extends AndroidTestCase {
    private AddReligionService service;
    private ReligionRepository repo;
    private Religion graduation;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new ReligionRepositoryImplem(this.getContext());
    }

    public void testAddReligion() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), AddReligionServiceimplem.class);
        AddReligionService addGraduationService = new AddReligionServiceimplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Religion religion = new Religion.Builder()
                .name("Encore")
                .address(address)
                .build();

        addGraduationService.addReligion(App.getContext(), religion);
        App.getContext().startService(intent);
        id = religion.getId();

        Assert.assertNotNull("CREATE", religion);
    }
}
