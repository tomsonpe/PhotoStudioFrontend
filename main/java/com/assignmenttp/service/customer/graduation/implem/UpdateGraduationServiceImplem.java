package com.assignmenttp.service.customer.graduation.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.customer.graduation.Graduation;
import com.assignmenttp.repository.customer.birthday.GraduationRepository;
import com.assignmenttp.repository.customer.birthday.implem.GraduationRepositoryImplem;
import com.assignmenttp.service.customer.graduation.UpdateGraduationService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/05/13.
 */
public class UpdateGraduationServiceImplem extends IntentService implements UpdateGraduationService {
    private final GraduationRepository repository;
    private static  UpdateGraduationServiceImplem service = null;


    private static final String ACTION_ADD ="package com.assignment_6_2.services.serviceImplementation.action.ADD";
    private static final String ACTION_UPDATE="package com.assignment_6_2.services.serviceImplementation.action.Update";

    private static final String EXTRA_ADD = "package com.assignment_6_2.services.serviceImplementation.extra.ADD";

    public UpdateGraduationServiceImplem() {
        super("UpdateGraduationServiceImplem");
        repository = new GraduationRepositoryImplem(App.getContext());
    }

    private static UpdateGraduationServiceImplem getInstance(){
        if(service == null)
            service = new UpdateGraduationServiceImplem();
        return service;
    }

    @Override
    public void updateGraduation(Context context,Graduation graduation){
        Intent intent = new Intent(context,UpdateGraduationServiceImplem.class);
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
            repository.update(graduation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Graduation Details has been updated", Toast.LENGTH_LONG).show();
    }
}
