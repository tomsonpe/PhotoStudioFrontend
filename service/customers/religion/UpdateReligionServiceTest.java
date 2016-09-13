package com.assignmenttp.service.customers.religion;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.religion.Religion;
import com.assignmenttp.repository.customer.birthday.ReligionRepository;
import com.assignmenttp.repository.customer.birthday.implem.ReligionRepositoryImplem;
import com.assignmenttp.service.customer.rel.UpdateReligionService;
import com.assignmenttp.service.customer.rel.implem.UpdateReligionServiceImplem;

import junit.framework.Assert;

/**
 * Created by Phinda Encore Tomson on 2016/05/14.
 */

public class UpdateReligionServiceTest extends AndroidTestCase {
    private UpdateReligionService service;
    private ReligionRepository repo;
    private Religion graduation;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new ReligionRepositoryImplem(this.getContext());
    }

    public void testUpdateReligion() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), UpdateReligionServiceImplem.class);
        UpdateReligionService addGraduationService = new UpdateReligionServiceImplem();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Religion religion = new Religion.Builder()
                .name("Encore")
                .address(address)
                .build();

        addGraduationService.updateReligion(App.getContext(), religion);
        App.getContext().startService(intent);
        id = religion.getId();

        Assert.assertNotNull("CREATE", religion);
    }
}
