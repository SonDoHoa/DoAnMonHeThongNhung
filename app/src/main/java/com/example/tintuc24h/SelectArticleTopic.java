package com.example.tintuc24h;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tintuc24h.Adapters.PagerAdapter;
import com.example.tintuc24h.Fragments.HealthFragment;
import com.example.tintuc24h.Fragments.HomePageFragment;
import com.example.tintuc24h.Fragments.SportFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class SelectArticleTopic extends AppCompatActivity {

    Toolbar mToolbar;
    GridLayout mainGrid;
    private boolean thoiSu = false, theGioi = false, theThao = false,
            giaoDuc = false, giaiTri = false, kinhDoanh = false, duLich = false,
            phapLuat = false, sucKhoe = false, congNghe = false, khoaHoc = false, xe = false;
    CardView cvThoiSu, cvTheGioi, cvTheThao, cvGiaoDuc, cvGiaiTri,
            cvKinhDoanh, cvDuLich, cvPhapLuat,
            cvSucKhoe, cvCongNghe, cvKhoaHoc, cvXe;

    HomePageFragment homePageFragment;
    SportFragment sportFragment;
    HealthFragment healthFragment;

    ImageView tickThoiSu, tickTheThao, tickSucKhoe;
    Button buttonSelectedTopic;
    public static ArrayList<TopicArticleModel> topicArticleModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_article_topic);

        mToolbar = findViewById(R.id.mToolBar);
        mToolbar.setTitle("Chon chu de");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AnhXa();

        mainGrid = findViewById(R.id.grid_layout);
        //Set Event
        setSingleEvent(mainGrid);

        Log.e("TAG", String.valueOf(topicArticleModelArrayList.size()));

        buttonSelectedTopic = findViewById(R.id.buttonSelectedTopic);
        buttonSelectedTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SelectArticleTopic.this, MainActivity.class);
//                Bundle args = new Bundle();
//                args.putSerializable("ARRAYLIST",(Serializable)topicArticleModelArrayList);
//                intent.putExtra("BUNDLE",args);
//                startActivity(intent);
            }
        });
    }

    public void AnhXa(){
        //card view
        cvThoiSu = findViewById(R.id.cvThoiSu);
        cvTheThao = findViewById(R.id.cvTheThao);
        cvSucKhoe = findViewById(R.id.cvSucKhoe);
        //tick choose topic
        tickThoiSu = findViewById(R.id.selectThoiSu);
        tickTheThao = findViewById(R.id.selectTheThao);
        tickSucKhoe = findViewById(R.id.selectSucKhoe);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        final Boolean[] isTopic = {false};
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()){
                        case R.id.cvThoiSu:
                            homePageFragment = new HomePageFragment();
                            ImageView imageView = cardView.findViewById(R.id.selectThoiSu);
                            chooseTopic(homePageFragment, isTopic, "Thoi su", imageView);
                            break;
                        case R.id.cvTheThao:
                            sportFragment = new SportFragment();
                            ImageView imageViewTheThao = cardView.findViewById(R.id.selectTheThao);
                            chooseTopic(sportFragment, isTopic, "The thao", imageViewTheThao);
                            break;
                        case R.id.cvSucKhoe:
                            healthFragment = new HealthFragment();
                            ImageView imageViewSucKhoe = cardView.findViewById(R.id.selectSucKhoe);
                            chooseTopic(sportFragment, isTopic, "Suc Khoe", imageViewSucKhoe);
                            break;
                    }

                }
            });
        }
    }

    private void chooseTopic(Fragment fragment, Boolean[] isTopic, String title, ImageView imageView) {
        if (isTopic[0] == false){
            topicArticleModelArrayList.add(new TopicArticleModel(fragment, true, title));
            imageView.setImageResource(R.drawable.tick_box_selected);
            isTopic[0] = true;
            for (int i = 0; i<topicArticleModelArrayList.size(); i++){
                if (topicArticleModelArrayList.get(i).getTitle().equals(title)){
                    topicArticleModelArrayList.get(i).setTopic(true);
                    Log.e("123", String.valueOf(topicArticleModelArrayList.size())+ i + title);
                }
            }

        } else {
            if (topicArticleModelArrayList.size() !=1){
                for (int i = 0; i<topicArticleModelArrayList.size(); i++){
                    if (topicArticleModelArrayList.get(i).getTitle().equals(title)){
                        if (i==0){
                            topicArticleModelArrayList.remove(0);
                        }else {
                            topicArticleModelArrayList.remove(topicArticleModelArrayList.get(i));
                        }
                        Log.e("remove", String.valueOf(topicArticleModelArrayList.size())+ i + title);
                    }
                }
            } else {
                topicArticleModelArrayList.remove(0);
                Log.e("remove", String.valueOf(topicArticleModelArrayList.size())+ 0 + title);
            }
            imageView.setImageResource(R.drawable.tick_box_unselected);
            isTopic[0] = false;
        }
    }


//    public void clickCardView(CardView cardView, final Fragment fragment, final String title, final ImageView imageView){
//        final Boolean[] isTopic = {false};
//
//
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isTopic[0] == false){
//                    topicArticleModelArrayList.add(new TopicArticleModel(fragment, true, title));
//                    imageView.setImageResource(R.drawable.tick_box_selected);
//                    isTopic[0] = true;
//                    for (int i = 0; i<topicArticleModelArrayList.size(); i++){
//                        if (topicArticleModelArrayList.get(i).getTitle().equals(title)){
//                            topicArticleModelArrayList.get(i).setTopic(true);
//                            Log.e("123", String.valueOf(topicArticleModelArrayList.size())+ i + title);
//                        }
//                    }
//
//                } else {
//                    if (topicArticleModelArrayList.size() !=1){
//                        for (int i = 0; i<topicArticleModelArrayList.size(); i++){
//                            if (topicArticleModelArrayList.get(i).getTitle().equals(title)){
//                                if (i==0){
//                                    topicArticleModelArrayList.remove(0);
//                                }else {
//                                    topicArticleModelArrayList.remove(topicArticleModelArrayList.get(i));
//                                }
//                                Log.e("remove", String.valueOf(topicArticleModelArrayList.size())+ i + title);
//                            }
//                        }
//                    } else {
//                        topicArticleModelArrayList.remove(0);
//                        Log.e("remove", String.valueOf(topicArticleModelArrayList.size())+ 0 + title);
//                    }
//
//                    imageView.setImageResource(R.drawable.tick_box_unselected);
//                    isTopic[0] = false;
//                }
//            }
//        });
//    }
}
