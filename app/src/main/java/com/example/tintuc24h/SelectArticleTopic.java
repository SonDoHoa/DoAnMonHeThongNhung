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
import com.example.tintuc24h.Fragments.BusinessFragment;
import com.example.tintuc24h.Fragments.HealthFragment;
import com.example.tintuc24h.Fragments.HomePageFragment;
import com.example.tintuc24h.Fragments.SportFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class SelectArticleTopic extends AppCompatActivity {

    Toolbar mToolbar;
    GridLayout mainGrid;
    CardView cvThoiSu, cvTheGioi, cvTheThao, cvGiaoDuc, cvGiaiTri,
            cvKinhDoanh, cvPhapLuat, cvSucKhoe, cvCongNghe;

    ImageView tickThoiSu, tickTheThao, tickSucKhoe, tickTheGioi, tickGiaoDuc, tickGiaiTri,
            tickKinhDoanh, tickPhapLuat, tickCongNghe;

    Button buttonSelectedTopic;
    ArrayList<TopicArticleModel> topicArticleModelArrayList = new ArrayList<>();
    TopicArticleModel topicArticleModel, topicArticleModelSport, topicArticleModelHealth,
                    topicArticleModelBusiness, topicArticleModelEduca, topicArticleModelEntertainment,
                    topicArticleModelLaw, topicArticleModelTech, topicArticleModelWorld;

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

        topicArticleModel = new TopicArticleModel(false, "Thoi su");
        topicArticleModelSport = new TopicArticleModel(false, "The thao");
        topicArticleModelHealth = new TopicArticleModel(false, "Suc Khoe");
        topicArticleModelBusiness = new TopicArticleModel(false, "Kinh doanh");
        topicArticleModelEduca = new TopicArticleModel(false, "Giao duc");
        topicArticleModelEntertainment = new TopicArticleModel(false, "Giai tri");
        topicArticleModelLaw = new TopicArticleModel(false, "Phap luat");
        topicArticleModelTech = new TopicArticleModel(false, "Cong nghe");
        topicArticleModelWorld = new TopicArticleModel(false, "The gioi");

        buttonSelectedTopic = findViewById(R.id.buttonSelectedTopic);
        buttonSelectedTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectArticleTopic.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(MainActivity.ARRAYLIST, topicArticleModelArrayList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void AnhXa(){
        //card view
        cvThoiSu = findViewById(R.id.cvThoiSu);
        cvTheThao = findViewById(R.id.cvTheThao);
        cvSucKhoe = findViewById(R.id.cvSucKhoe);
        cvCongNghe = findViewById(R.id.cvCongNghe);
        cvGiaiTri = findViewById(R.id.cvGiaiTri);
        cvGiaoDuc = findViewById(R.id.cvGiaoDuc);
        cvKinhDoanh = findViewById(R.id.cvKinhDoanh);
        cvTheGioi = findViewById(R.id.cvTheGioi);
        cvPhapLuat = findViewById(R.id.cvPhapLuat);
        //tick choose topic
        tickThoiSu = findViewById(R.id.selectThoiSu);
        tickTheThao = findViewById(R.id.selectTheThao);
        tickSucKhoe = findViewById(R.id.selectSucKhoe);
        tickCongNghe = findViewById(R.id.selectCongNghe);
        tickGiaiTri = findViewById(R.id.selectGiaiTri);
        tickGiaoDuc = findViewById(R.id.selectGiaoDuc);
        tickKinhDoanh = findViewById(R.id.selectKinhDoanh);
        tickTheGioi = findViewById(R.id.selectTheGioi);
        tickPhapLuat = findViewById(R.id.selectPhapLuat);
    }

    private void setSingleEvent(GridLayout mainGrid) {

        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()){
                        case R.id.cvThoiSu:
                            ImageView imageView = cardView.findViewById(R.id.selectThoiSu);
                            chooseTopic(topicArticleModel, "Thoi su", imageView);
                            break;
                        case R.id.cvTheThao:
                            ImageView imageViewTheThao = cardView.findViewById(R.id.selectTheThao);
                            chooseTopic(topicArticleModelSport, "The thao", imageViewTheThao);
                            break;
                        case R.id.cvSucKhoe:
                            ImageView imageViewSucKhoe = cardView.findViewById(R.id.selectSucKhoe);
                            chooseTopic(topicArticleModelHealth, "Suc Khoe", imageViewSucKhoe);
                            break;
                        case R.id.cvKinhDoanh:
                            ImageView imageViewKinhDoanh = cardView.findViewById(R.id.selectKinhDoanh);
                            chooseTopic(topicArticleModelBusiness, "Kinh doanh", imageViewKinhDoanh);
                            break;
                        case R.id.cvGiaoDuc:
                            ImageView imageViewGiaoDuc = cardView.findViewById(R.id.selectGiaoDuc);
                            chooseTopic(topicArticleModelEduca, "Giao duc", imageViewGiaoDuc);
                            break;
                        case R.id.cvGiaiTri:
                            ImageView imageViewGiaiTri = cardView.findViewById(R.id.selectGiaiTri);
                            chooseTopic(topicArticleModelEntertainment, "Giai tri", imageViewGiaiTri);
                            break;
                        case R.id.cvPhapLuat:
                            ImageView imageViewPhapLuat = cardView.findViewById(R.id.selectPhapLuat);
                            chooseTopic(topicArticleModelLaw, "Phap luat", imageViewPhapLuat);
                            break;
                        case R.id.cvCongNghe:
                            ImageView imageViewCongNghe = cardView.findViewById(R.id.selectCongNghe);
                            chooseTopic(topicArticleModelTech, "Cong nghe", imageViewCongNghe);
                            break;
                        case R.id.cvTheGioi:
                            ImageView imageViewTheGioi = cardView.findViewById(R.id.selectTheGioi);
                            chooseTopic(topicArticleModelWorld, "The gioi", imageViewTheGioi);
                            break;
                    }

                }
            });
        }
    }

    private void chooseTopic(TopicArticleModel topicArticleModel, String title, ImageView imageView) {
        Log.e("TAG", "123");
        if (topicArticleModel.isTopic() == false){
            topicArticleModelArrayList.add(topicArticleModel);
            imageView.setImageResource(R.drawable.tick_box_selected);
            topicArticleModel.setTopic(true);
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
            topicArticleModel.setTopic(false);
        }
    }
}
