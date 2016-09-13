package com.assignmenttp.service.customer.others.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.customer.others.Others;
import com.assignmenttp.repository.customer.birthday.OthersRepository;
import com.assignmenttp.repository.customer.birthday.implem.OthersRepositoryImplem;
import com.assignmenttp.service.customer.others.UpdateOthersService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/05/14.
 */
public class UpdateOthersServiceImplem extends IntentService implements UpdateOthersService {
    private final OthersRepository repository;
    private static  UpdateOthersServiceImplem service = null;


    private static final String ACTION_ADD ="package com.assignment_6_2.services.serviceImplementation.action.ADD";
    private static final String ACTION_UPDATE="package com.assignment_6_2.services.serviceImplementation.action.Update";

    private static final String EXTRA_ADD = "package com.assignment_6_2.services.serviceImplementation.extra.ADD";

    public UpdateOthersServiceImplem() {
        super("AddOthersServiceimplem");
        repository = new OthersRepositoryImplem(App.getContext());
    }

    private static UpdateOthersServiceImplem getInstance(){
        if(service == null)
            service = new UpdateOthersServiceImplem();
        return service;
    }

    @Override
    public void addOthers(Context context,Others others){
        Intent intent = new Intent(context,UpdateOthersServiceImplem.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,others);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){

        Others othersResources = (Others)intent.getSerializableExtra(EXTRA_ADD);
        Others religion = new Others.Builder()
                .id(othersResources.getId())
                .name(othersResources.getName())
                .address(othersResources.getAddress())
                .build();
        try {
            repository.update(religion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Religion Details has been added", Toast.LENGTH_LONG).show();
    }
}
