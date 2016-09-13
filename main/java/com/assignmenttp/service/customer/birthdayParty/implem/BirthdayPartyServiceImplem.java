package com.assignmenttp.service.customer.birthdayParty.implem;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.assignmenttp.config.util.App;
import com.assignmenttp.domain.customer.birthdayParty.BirthdayParty;
import com.assignmenttp.repository.customer.birthday.BirthdayPartyRepository;
import com.assignmenttp.repository.customer.birthday.implem.BirthdayPartyRepositoryImplem;
import com.assignmenttp.service.customer.birthdayParty.BirthdaypartyService;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/05/13.
 */
public class BirthdayPartyServiceImplem extends IntentService implements BirthdaypartyService {
    private final BirthdayPartyRepository repository;
    private static  BirthdayPartyServiceImplem service = null;


    private static final String ACTION_ADD ="package com.assignment_6_2.services.serviceImplementation.action.ADD";
    private static final String ACTION_UPDATE="package com.assignment_6_2.services.serviceImplementation.action.Update";

    private static final String EXTRA_ADD = "package com.assignment_6_2.services.serviceImplementation.extra.ADD";

    public BirthdayPartyServiceImplem() {
        super("BirthdayPartyServiceImplem");
        repository = new BirthdayPartyRepositoryImplem(App.getContext());
    }

    private static BirthdayPartyServiceImplem getInstance(){
        if(service == null)
            service = new BirthdayPartyServiceImplem();
        return service;
    }

    @Override
    public void addParty(Context context,BirthdayParty cash){
        Intent intent = new Intent(context,BirthdayPartyServiceImplem.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,cash);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent){

        BirthdayParty partyResources = (BirthdayParty)intent.getSerializableExtra(EXTRA_ADD);
        BirthdayParty party = new BirthdayParty.Builder()
                .id(partyResources.getId())
                .name(partyResources.getNameOfPerson())
                .address(partyResources.getAddress())
                .build();
        try {
            repository.save(party);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Party Details has been added", Toast.LENGTH_LONG).show();
    }
}
