package com.iamzain.template_android.activities;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.iamzain.template_android.R;
import com.iamzain.template_android.fragments.TestFragment;

public class FragmentsActivity extends BaseActivity implements TestFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fragments);

        RelativeLayout da_layout_main = (RelativeLayout) findViewById( R.id.baseactivity);
        View view_child = getLayoutInflater().inflate( R.layout.activity_fragments, null);
        da_layout_main.addView(view_child);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        TestFragment fragment = new TestFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
