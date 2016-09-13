package com.assignmenttp.service.customer.rel.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.customer.religion.Religion;
import com.assignmenttp.repository.customer.birthday.ReligionRepository;
import com.assignmenttp.repository.customer.birthday.implem.ReligionRepositoryImplem;
import com.assignmenttp.service.customer.rel.UpdateReligionService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/05/14.
 */
public class UpdateReligionServiceImplem extends IntentService implements UpdateReligionService {
    private final ReligionRepository repository;
    private static  UpdateReligionServiceImplem service = null;


    private static final String ACTION_ADD ="package com.assignment_6_2.services.serviceImplementation.action.ADD";
    private static final String ACTION_UPDATE="package com.assignment_6_2.services.serviceImplementation.action.Update";

    private static final String EXTRA_ADD = "package com.assignment_6_2.services.serviceImplementation.extra.ADD";

    public UpdateReligionServiceImplem() {
        super("UpdateReligionServiceImplem");
        repository = new ReligionRepositoryImplem(App.getContext());
    }

    private static UpdateReligionServiceImplem getInstance(){
        if(service == null)
            service = new UpdateReligionServiceImplem();
        return service;
    }

    @Override
    public void updateReligion(Context context,Religion religion){
        Intent intent = new Intent(context,UpdateReligionServiceImplem.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,religion);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){

        Religion religionResources = (Religion)intent.getSerializableExtra(EXTRA_ADD);
        Religion religion = new Religion.Builder()
                .id(religionResources.getId())
                .name(religionResources.getName())
                .address(religionResources.getAddress())
                .build();
        try {
            repository.update(religion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Religion Details has been updated", Toast.LENGTH_LONG).show();
    }
}
