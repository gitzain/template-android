package com.iamzain.template_android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.iamzain.template_android.models.Card;
import com.iamzain.template_android.fragments.CardsViewFragment;
import com.iamzain.template_android.R;


import butterknife.BindView;
import butterknife.ButterKnife;


public class CardsActivity extends AppCompatActivity {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    CardsViewFragment cardTab1 = new CardsViewFragment();
    CardsViewFragment cardTab2 = new CardsViewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        ButterKnife.bind(this);
        setTitle(R.string.cards_title);

        createToolbar();
        createCardsUI();

        addData();
    }

    private void createToolbar()
    {
        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void createCardsUI()
    {
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return cardTab1;
                    case 1:
                        return cardTab2;
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Card 1";
                    case 1:
                        return "Card 2";
                }
                return "";
            }
        });

        // show title strip
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

    }

    private void addData()
    {
        Card firstCard = new Card("thumbnail 1", "Title 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "Meta 1");
        cardTab1.addCard(firstCard);

        Card secondCard = new Card("thumbnail 2", "Title 2", "Description 2", "Meta 2");
        cardTab2.addCard(secondCard);
    }

    private void setFancyHeader()
    {
        // chnage header when tab changes
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
