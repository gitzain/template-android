package com.iamzain.template_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.ButterKnife;

import static com.iamzain.template_android.R.drawable.*;

public class MainActivity extends AppCompatActivity {

    // Session Manager Class
    SessionManager session;

    private Toolbar toolbar;
    private Drawer navigationDrawer;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        //session.checkLogin();
        //HashMap<String, String> user = session.getUserDetails();
        //String email = user.get(SessionManager.KEY_EMAIL);

        AccountHeader accountHeader = createAccountHeader("test");
        createNavigationDrawer(accountHeader);

        ButterKnife.bind(this);
        setTitle(R.string.cards_title);

        createToolbar();
    }

    private void createToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            mDrawer = navigationDrawer.getDrawerLayout();
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, 0, 0);
            navigationDrawer.setActionBarDrawerToggle(mDrawerToggle);
            mDrawer.setDrawerListener(mDrawerToggle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mDrawerToggle.syncState();
        }
    }

    private AccountHeader createAccountHeader(String username)
    {
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Username:")
                                .withEmail(username)
                                .withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        return headerResult;
    }

    private void createNavigationDrawer(AccountHeader account_header)
    {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withIcon(ic_home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_cards).withIcon(ic_dashboard);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.drawer_item_video).withIcon(ic_dashboard);

        SecondaryDrawerItem settings = new SecondaryDrawerItem().withIdentifier(4).withName(R.string.drawer_item_settings).withIcon(ic_settings);
        SecondaryDrawerItem feedback = new SecondaryDrawerItem().withIdentifier(5).withName(R.string.drawer_item_feedback).withIcon(ic_feedback);
        SecondaryDrawerItem about = new SecondaryDrawerItem().withIdentifier(6).withName(R.string.drawer_item_about).withIcon(ic_info);

        //create the drawer and remember the `Drawer` navigationDrawer object
        navigationDrawer = new DrawerBuilder()
                .withAccountHeader(account_header)
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        new DividerDrawerItem(),
                        settings,
                        feedback,
                        about
                )
                .build();

        navigationDrawer.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
            {
                navigationDrawer.closeDrawer();
                Intent intent = null;

                switch((int) drawerItem.getIdentifier()) {
                    case 1:
                        break;

                    case 2:
                        intent = new Intent(MainActivity.this, CardsActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(MainActivity.this, VideoActivity.class);
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        break;

                    case 5:
                        //Start a new activity for sending a feedback email
                        intent = new Intent(android.content.Intent.ACTION_SEND);
                        intent.setType("text/html");
                        intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
                        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
                        intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
                        startActivity(Intent.createChooser(intent, getString(R.string.title_send_feedback)));
                        break;

                    case 6:
                        intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }

                return true;
            }
        });
    }

}


