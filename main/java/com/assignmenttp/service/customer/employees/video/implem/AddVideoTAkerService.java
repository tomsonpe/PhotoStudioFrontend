package com.assignmenttp.service.customer.employees.video.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.employees.videos.Video;
import com.assignmenttp.repository.employees.video.VideoRepository;
import com.assignmenttp.repository.employees.video.implem.VideoRepositoryImplem;
import com.assignmenttp.service.customer.employees.video.VideoService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/06/07.
 */
public class AddVideoTAkerService extends IntentService implements VideoService{
    private final VideoRepository repository;
    private static  AddVideoTAkerService service = null;


    private static final String ACTION_ADD ="package com.assignmenttp.service.customer.employees.video.implem.action.ADD";
    private static final String EXTRA_ADD = "package com.assignmenttp.service.customer.employees.video.implem.extra.ADD";

    public AddVideoTAkerService() {
        super("AddVideoTAkerService");
        repository = new VideoRepositoryImplem(App.getContext());
    }

    private static AddVideoTAkerService getInstance(){
        if(service == null)
            service = new AddVideoTAkerService();
        return service;
    }

    @Override
    public void addVideoTakers(Context context,Video video){
        Intent intent = new Intent(context,AddVideoTAkerService.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,video);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){

        Video videoResources = (Video)intent.getSerializableExtra(EXTRA_ADD);
        Video photo = new Video.Builder()
                .id(videoResources.getId())
                .first(videoResources.getFirstName())
                .last(videoResources.getLastName())
                .address(videoResources.getAddress())
                .build();
        try {
            repository.save(photo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Party Details has been added", Toast.LENGTH_LONG).show();
    }
}
