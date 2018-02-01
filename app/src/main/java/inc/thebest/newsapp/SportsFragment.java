package inc.thebest.newsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SportsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<News> newsList;
    private NewsAdapter mNewsAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SportsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_news, container, false);

        mRecyclerView = view.findViewById(R.id.current_news_recyclerView);
        newsList = new ArrayList<>();
        mNewsAdapter = new NewsAdapter(newsList, getActivity());

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mNewsAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //fetchFakeData();
        fetchNews();

        return view;
    }

    private  void fetchNews(){
        String url = makeURL();
        //newsList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONObject responseObject = response.getJSONObject("response");
                            JSONArray resultsArray = responseObject.getJSONArray("results");
                            int n = resultsArray.length();

                            for(int i=0; i<n; i++){
                                JSONObject itemI = resultsArray.getJSONObject(i);

                                String title = itemI.getString("webTitle");
                                final String webUrl = itemI.getString("webUrl");

                                Log.v("Message: ", title);

                                newsList.add(new News(title, webUrl));

                                mNewsAdapter.notifyDataSetChanged();
/*
                                RecycleClick.addTo(mRecyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
                                    @Override
                                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                                        //code to display web page
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                                        startActivity(intent);
                                    }
                                });
*/
                            }
                        } catch (JSONException e){
                            System.out.println("Error occured in method:    onResponse(), inside LifestyleFragment.java");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("onErrorResponse()", "Error in this method");
                    }
                });
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    //creates url which uses the current date
    private String makeURL(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = new Date();
        String todayDate = dateFormat.format(yesterday());

        StringBuilder url = new StringBuilder();
        url.append("http://content.guardianapis.com/search?from-date=");
        url.append(todayDate);
        url.append("&q=sport&api-key=b7933e6a-83fc-4375-9c0e-b6257f27b298");

        return new String(url);
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }


    private void fetchFakeData() {
        News news = new News("India wins world cup");
        newsList.add(news);

        news = new News("8 killed in a car accident");
        newsList.add(news);

        news = new News("9 killed in a car accident");
        newsList.add(news);

        news = new News("Hello World");
        newsList.add(news);

        news = new News("1 killed in a car accident");
        newsList.add(news);

        news = new News("8 killed in a car accident");
        newsList.add(news);

        news = new News("9 killed in a car accident");
        newsList.add(news);

        news = new News("Hello World");
        newsList.add(news);

        news = new News("1 killed in a car accident");
        newsList.add(news);

        mNewsAdapter.notifyDataSetChanged();
    }
}
