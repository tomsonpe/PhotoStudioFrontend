package com.assignmenttp.service.customer.employees.photo.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.employees.photo.Photo;
import com.assignmenttp.repository.employees.photo.PhotoRepository;
import com.assignmenttp.repository.employees.photo.implem.PhotoReapositoryImpem;
import com.assignmenttp.service.customer.employees.photo.DeletePhotoService;
import com.assignmenttp.service.customer.employees.photo.PhotoService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/06/07.
 */
public class DeletePhotoTakerService extends IntentService implements DeletePhotoService {
    private final PhotoRepository repository;
    private static  DeletePhotoTakerService service = null;


    private static final String ACTION_ADD ="package com.assignmenttp.service.customer.employees.photo.implem.action.ADD";
    private static final String EXTRA_ADD = "package com.assignmenttp.service.customer.employees.photo.implem.extra.ADD";

    public DeletePhotoTakerService() {
        super("DeletePhotoTakerService");
        repository = new PhotoReapositoryImpem(App.getContext());
    }

    private static DeletePhotoTakerService getInstance(){
        if(service == null)
            service = new DeletePhotoTakerService();
        return service;
    }

    @Override
    public void deletePhotoTaker(Context context,Photo photo){
        Intent intent = new Intent(context,AddPhotoTakerService.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,photo);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){

        Photo photoResources = (Photo)intent.getSerializableExtra(EXTRA_ADD);
        Photo photo = new Photo.Builder()
                .id(photoResources.getId())
                .first(photoResources.getFirstName())
                .last(photoResources.getLastName())
                .address(photoResources.getAddress())
                .build();
        try {
            repository.save(photo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Party Details has been added", Toast.LENGTH_LONG).show();
    }
}
