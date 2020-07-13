package com.example.tintuc24h;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.tintuc24h.Adapters.PagerAdapter;
import com.example.tintuc24h.Fragments.HealthFragment;
import com.example.tintuc24h.Fragments.HomePageFragment;
import com.example.tintuc24h.Fragments.SportFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements HealthFragment.OnFragmentInteractionListener, HomePageFragment.OnFragmentInteractionListener, SportFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ImageButton imageButtonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonMenu = findViewById(R.id.buttonMenu);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("BUNDLE");
//        ArrayList<TopicArticleModel> listTopic = (ArrayList<TopicArticleModel>) bundle.getSerializable("ARRAYLIST");
//        Log.e("list fragment", String.valueOf(listTopic.size()));


        // Add fragment here
        pagerAdapter.AddFragment(new HomePageFragment(), "Trang chu");
        pagerAdapter.AddFragment(new SportFragment(), "The thao");
        pagerAdapter.AddFragment(new HealthFragment(), "Suc khoe");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        mSelectTopicArticle = new SelectTopicArticle();

        imageButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectArticleTopic.class);
//                intent.putExtra("adapter", (Serializable) pagerAdapter);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
