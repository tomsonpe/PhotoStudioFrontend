package com.assignmenttp.service.customer.funeral.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.customer.birthdayParty.BirthdayParty;
import com.assignmenttp.domain.customer.funeral.Funeral;
import com.assignmenttp.repository.customer.birthday.FuneralReapository;
import com.assignmenttp.repository.customer.birthday.implem.FuneralRepositoryImplem;
import com.assignmenttp.service.customer.funeral.FuneralService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/05/13.
 */
public class FuneralServiceImplem extends IntentService implements FuneralService {
    private final FuneralReapository repository;
    private static  FuneralServiceImplem service = null;


    private static final String ACTION_ADD ="package com.assignment_6_2.services.serviceImplementation.action.ADD";
    private static final String ACTION_UPDATE="package com.assignment_6_2.services.serviceImplementation.action.Update";

    private static final String EXTRA_ADD = "package com.assignment_6_2.services.serviceImplementation.extra.ADD";

    public FuneralServiceImplem() {
        super("FuneralServiceImplem");
        repository = new FuneralRepositoryImplem(App.getContext());
    }

    private static FuneralServiceImplem getInstance(){
        if(service == null)
            service = new FuneralServiceImplem();
        return service;
    }

    @Override
    public void addFuneral(Context context,Funeral funeral){
        Intent intent = new Intent(context,FuneralServiceImplem.class);
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
            repository.save(funeral);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Party Details has been added", Toast.LENGTH_LONG).show();
    }
}
