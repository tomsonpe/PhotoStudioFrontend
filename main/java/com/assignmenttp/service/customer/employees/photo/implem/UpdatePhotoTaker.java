package com.assignmenttp.service.customer.employees.photo.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.employees.photo.Photo;
import com.assignmenttp.repository.employees.photo.PhotoRepository;
import com.assignmenttp.repository.employees.photo.implem.PhotoReapositoryImpem;
import com.assignmenttp.service.customer.employees.photo.UpdatePhotoTakerService;

/**
 * Created by Administrator on 2016/06/07.
 */
public class UpdatePhotoTaker extends IntentService implements UpdatePhotoTakerService {
    private final PhotoRepository repository;
    private static  UpdatePhotoTaker service = null;


    private static final String ACTION_UPDATE ="package com.assignmenttp.service.customer.employees.photo.implem.action.Update";
    private static final String EXTRA_UPDATE="package com.assignmenttp.service.customer.employees.photo.implem.extra.Update";


    public UpdatePhotoTaker() {
        super("UpdatePhotoTaker");
        repository = new PhotoReapositoryImpem(App.getContext());
    }

    private static UpdatePhotoTaker getInstance(){
        if(service == null)
            service = new UpdatePhotoTaker();
        return service;
    }

    @Override
    public void updatePhotoTaker(Context context,Photo photo){
        Intent intent = new Intent(context,AddPhotoTakerService.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE,photo);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){


    }
}
