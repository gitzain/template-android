package com.iamzain.template_android.activities;

import android.os.Bundle;

import com.iamzain.template_android.R;
import com.iamzain.template_android.authenticator.SessionManager;

public class MainActivity extends BaseActivity {

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        //session.checkLogin();
        //HashMap<String, String> user = session.getUserDetails();
        //String email = user.get(SessionManager.KEY_EMAIL);

        setTitle(R.string.cards_title);
    }
}


