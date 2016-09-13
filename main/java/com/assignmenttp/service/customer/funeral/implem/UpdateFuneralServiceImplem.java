package com.assignmenttp.service.customer.funeral.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.customer.funeral.Funeral;
import com.assignmenttp.repository.customer.birthday.FuneralReapository;
import com.assignmenttp.repository.customer.birthday.implem.FuneralRepositoryImplem;
import com.assignmenttp.service.customer.funeral.UpdateFuneralService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/05/13.
 */
public class UpdateFuneralServiceImplem extends IntentService implements UpdateFuneralService{
    private final FuneralReapository repository;
    private static  UpdateFuneralServiceImplem service = null;


    private static final String ACTION_ADD ="package com.assignment_6_2.services.serviceImplementation.action.ADD";
    private static final String ACTION_UPDATE="package com.assignment_6_2.services.serviceImplementation.action.Update";

    private static final String EXTRA_ADD = "package com.assignment_6_2.services.serviceImplementation.extra.ADD";

    public UpdateFuneralServiceImplem() {
        super("UpdateFuneralServiceImplem");
        repository = new FuneralRepositoryImplem(App.getContext());
    }

    private static UpdateFuneralServiceImplem getInstance(){
        if(service == null)
            service = new UpdateFuneralServiceImplem();
        return service;
    }

    @Override
    public void updateFuneral(Context context,Funeral funeral){
        Intent intent = new Intent(context,UpdateFuneralServiceImplem.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,funeral);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){

        Funeral funeralResources = (Funeral)intent.getSerializableExtra(EXTRA_ADD);
        Funeral funeral = new Funeral.Builder()
                .id(funeralResources.getId())
                .name(funeralResources.getName())
                .address(funeralResources.getAddress())
                .build();
        try {
            repository.update(funeral);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Party Details has been updated", Toast.LENGTH_LONG).show();
    }
}
