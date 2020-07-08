package com.example.tintuc24h.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tintuc24h.Adapters.ItemArticleAdapter;
import com.example.tintuc24h.R;
import com.example.tintuc24h.ReadArticleView;
import com.example.tintuc24h.XMLDOMParser;
import com.example.tintuc24h.itemArticleModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<itemArticleModel> itemArticleModelArrayList;
    SwipeRefreshLayout swipeRefreshLayout;

    public SportFragment() {
        // Required empty public constructor
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemArticleModelArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerViewSport);

        new ReadRss().execute("https://vnexpress.net/rss/the-thao.rss");

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshSport);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Reload RSS", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    public class ReadRss extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null){
                XMLDOMParser xmldomParser = new XMLDOMParser();
                Document document = xmldomParser.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");
                NodeList nodeListdescription = document.getElementsByTagName("description");
                String image = "";
                String title = "";
                String description = "";
                String link = "";
                String nameArticle = "";
                String pubDate = "";

                for (int i=0; i < nodeList.getLength(); i++){
                    String cdata = nodeListdescription.item(i+1).getTextContent();

                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher = p.matcher(cdata);
                    if (matcher.find()){
                        image = matcher.group(1);
                        Log.d("mo ta:", image);
                    } else {
                        image = null;
                    }

                    Element element = (Element) nodeList.item(i);
                    link = xmldomParser.getValue(element, "link");
                    title = xmldomParser.getValue(element, "title");

                    pubDate = xmldomParser.getValue(element, "pubDate");
                    Pattern pTime = Pattern.compile("\\d{2}:.....");
                    Matcher matcherTime = pTime.matcher(pubDate);
                    if (matcherTime.find()){
                        pubDate = matcherTime.group(0);
                    }

                    nameArticle = nodeListdescription.item(0).getTextContent().replaceAll("RSS", "");
                    itemArticleModelArrayList.add(new itemArticleModel(title, image, nameArticle, pubDate, link));
                }
                ItemArticleAdapter recyclerviewAdapter = new ItemArticleAdapter(getContext(), itemArticleModelArrayList);
                recyclerviewAdapter.setOnItemClickListener(new ItemArticleAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Intent intent = new Intent(getContext(), ReadArticleView.class);
                        intent.putExtra("link", itemArticleModelArrayList.get(position).link);
                        startActivity(intent);
                    }
                });
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(recyclerviewAdapter);
            } else {
                Toast.makeText(getContext(), "gia tri cua s bi null", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
