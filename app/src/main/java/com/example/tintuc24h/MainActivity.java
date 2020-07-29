package com.example.tintuc24h;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.tintuc24h.Adapters.PagerAdapter;
import com.example.tintuc24h.Fragments.BusinessFragment;
import com.example.tintuc24h.Fragments.EducationFragment;
import com.example.tintuc24h.Fragments.EntertainmentFragment;
import com.example.tintuc24h.Fragments.HealthFragment;
import com.example.tintuc24h.Fragments.HomePageFragment;
import com.example.tintuc24h.Fragments.LawFragment;
import com.example.tintuc24h.Fragments.SportFragment;
import com.example.tintuc24h.Fragments.TechnologyFragment;
import com.example.tintuc24h.Fragments.WorldFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HealthFragment.OnFragmentInteractionListener, HomePageFragment.OnFragmentInteractionListener, SportFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ImageButton imageButtonMenu;
    public static final String ARRAYLIST = "arrayList";
    ArrayList<TopicArticleModel> listTopic;
    Fragment fragment;
//    String title;
    List<String> title;
    TopicArticleModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonMenu = findViewById(R.id.buttonMenu);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        Bundle bundle = getIntent().getExtras();
        listTopic = new ArrayList<>();
        listTopic = (ArrayList<TopicArticleModel>) bundle.getSerializable(ARRAYLIST);


        Log.e("list fragment", String.valueOf(listTopic.size()));
        // Add fragment here
        for (int i=0;i<listTopic.size();i++){
            if (listTopic.get(i).getTitle().equals("Thoi su")){
                pagerAdapter.AddFragment(new HomePageFragment(), listTopic.get(i).getTitle());
            }
            else if (listTopic.get(i).getTitle().equals("The thao")){
                pagerAdapter.AddFragment(new SportFragment(), listTopic.get(i).getTitle());
            }
            else if (listTopic.get(i).getTitle().equals("Suc Khoe")){
                pagerAdapter.AddFragment(new HealthFragment(), listTopic.get(i).getTitle());
            }
            else if (listTopic.get(i).getTitle().equals("Kinh doanh")){
                pagerAdapter.AddFragment(new BusinessFragment(), listTopic.get(i).getTitle());
            }
            else if (listTopic.get(i).getTitle().equals("Giao duc")){
                pagerAdapter.AddFragment(new EducationFragment(), listTopic.get(i).getTitle());
            }
            else if (listTopic.get(i).getTitle().equals("Giai tri")){
                pagerAdapter.AddFragment(new EntertainmentFragment(), listTopic.get(i).getTitle());
            }
            else if (listTopic.get(i).getTitle().equals("Phap luat")){
                pagerAdapter.AddFragment(new LawFragment(), listTopic.get(i).getTitle());
            }
            else if (listTopic.get(i).getTitle().equals("Cong nghe")){
                pagerAdapter.AddFragment(new TechnologyFragment(), listTopic.get(i).getTitle());
            }
            else{
                pagerAdapter.AddFragment(new WorldFragment(), listTopic.get(i).getTitle());
            }
        }
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
