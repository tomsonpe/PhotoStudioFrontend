package com.assignmenttp.service.employees.photo;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.employees.photo.Photo;
import com.assignmenttp.repository.employees.photo.PhotoRepository;
import com.assignmenttp.repository.employees.photo.implem.PhotoReapositoryImpem;
import com.assignmenttp.service.customer.employees.photo.DeletePhotoService;
import com.assignmenttp.service.customer.employees.photo.PhotoService;
import com.assignmenttp.service.customer.employees.photo.implem.DeletePhotoTakerService;

import junit.framework.Assert;

/**
 * Created by Administrator on 2016/06/07.
 */
public class DeletePhotoServiceTest extends AndroidTestCase {
    private DeletePhotoService service;
    private PhotoRepository repo;
    private Photo party;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new PhotoReapositoryImpem(this.getContext());
    }

    public void testDeletePhotoTaker() throws Exception{
        Long id;
        Intent intent = new Intent(App.getContext(), DeletePhotoTakerService.class);
        DeletePhotoService photo = new DeletePhotoTakerService();
        Address address =new Address.Builder()
                .postalCode("7100")
                .streetName("Katali Street")
                .suburb("Mfuleni")
                .build();
        Photo photos = new Photo.Builder()
                .first("Encore")
                .last("Tomson")
                .address(address)
                .build();

        photo.deletePhotoTaker(App.getContext(), photos);
        App.getContext().startService(intent);
        id = photos.getId();

        Assert.assertNotNull("CREATE", photos);;
    }
}
