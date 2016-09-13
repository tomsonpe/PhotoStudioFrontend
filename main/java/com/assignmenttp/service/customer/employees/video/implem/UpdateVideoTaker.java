package com.assignmenttp.service.customer.employees.video.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.employees.videos.Video;
import com.assignmenttp.repository.employees.video.VideoRepository;
import com.assignmenttp.repository.employees.video.implem.VideoRepositoryImplem;
import com.assignmenttp.service.customer.employees.video.UpdateVideoTakerService;

/**
 * Created by Administrator on 2016/06/07.
 */
public class UpdateVideoTaker  extends IntentService implements UpdateVideoTakerService{
    private final VideoRepository repository;
    private static  UpdateVideoTaker service = null;


    private static final String ACTION_UPDATE ="package com.assignmenttp.service.customer.employees.video.implem.action.Update";
    private static final String EXTRA_UPDATE="package com.assignmenttp.service.customer.employees.video.implem.extra.Update";

    public UpdateVideoTaker() {
        super("UpdateVideoTaker");
        repository = new VideoRepositoryImplem(App.getContext());
    }

    private static UpdateVideoTaker getInstance(){
        if(service == null)
            service = new UpdateVideoTaker();
        return service;
    }

    @Override
    public void updateVideoTaker(Context context,Video video){
        Intent intent = new Intent(context,DeleteVideoTakerService.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE,video);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
