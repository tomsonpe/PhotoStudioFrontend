package com.assignmenttp.service.customer.graduation.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.customer.graduation.Graduation;
import com.assignmenttp.repository.customer.birthday.GraduationRepository;
import com.assignmenttp.repository.customer.birthday.implem.GraduationRepositoryImplem;
import com.assignmenttp.service.customer.graduation.AddGraduationService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/05/13.
 */
public class AddGraduationServiceImplem extends IntentService implements AddGraduationService {
    private final GraduationRepository repository;
    private static  AddGraduationServiceImplem service = null;


    private static final String ACTION_ADD ="package com.assignment_6_2.services.serviceImplementation.action.ADD";
    private static final String ACTION_UPDATE="package com.assignment_6_2.services.serviceImplementation.action.Update";

    private static final String EXTRA_ADD = "package com.assignment_6_2.services.serviceImplementation.extra.ADD";

    public AddGraduationServiceImplem() {
        super("AddGraduationServiceImplem");
        repository = new GraduationRepositoryImplem(App.getContext());
    }

    private static AddGraduationServiceImplem getInstance(){
        if(service == null)
            service = new AddGraduationServiceImplem();
        return service;
    }

    @Override
    public void addGraduation(Context context,Graduation graduation){
        Intent intent = new Intent(context,AddGraduationServiceImplem.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,graduation);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){

        Graduation graduationResources = (Graduation)intent.getSerializableExtra(EXTRA_ADD);
        Graduation graduation = new Graduation.Builder()
                .id(graduationResources.getId())
                .name(graduationResources.getName())
                .address(graduationResources.getAddress())
                .build();
        try {
            repository.save(graduation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Party Details has been added", Toast.LENGTH_LONG).show();
    }
}
