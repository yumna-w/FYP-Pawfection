package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawfection.DynamicRecyclerViewInterface.LoadMore;
import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.example.pawfection.databinding.ActivityPetLoverLostPetAlertBinding;
import com.example.pawfection.databinding.ActivityPetLoverNavigationDrawerBaseBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PetLoverLostPetAlert extends PetLoverNavigationDrawerBase {

    ActivityPetLoverLostPetAlertBinding activityPetLoverLostPetAlertBinding;
    List<DynamicRecyclerViewModel> items = new ArrayList();
    DynamicRecyclerViewAdapter dynamicRecyclerViewAdapter;
    private Button petLoverLostPetAlertNewAlertButton;
    private TextView petLoverLostPetAlertTextViewHello;
    private TextView petLoverLostPetAlertTextViewHaveYou;
    private ImageView petLoverLostPetAlertImageViewPaw;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    NodeJS myAPI;
    JSONArray jsonArray;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverLostPetAlertBinding = ActivityPetLoverLostPetAlertBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverLostPetAlertBinding.getRoot());

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        compositeDisposable.add(myAPI.getAlerts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        jsonArray = new JSONArray(s);

                        for (int i = 0; i < 10; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String petName = jsonObject.getString("petName");
                            String lastSeen = jsonObject.getString("lastSeen");
                            String contactNumber = jsonObject.getString("contactNumber");
                            System.out.println(petName + lastSeen + contactNumber);

                            DynamicRecyclerViewModel item = new DynamicRecyclerViewModel(petName, lastSeen, contactNumber);
                            items.add(item);
                        }
                    }
                }));

        RecyclerView dynamicRecyclerView = findViewById(R.id.petLoverLostPetAlertRecyclerView);
        dynamicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dynamicRecyclerViewAdapter = new DynamicRecyclerViewAdapter(dynamicRecyclerView, this, items);
        dynamicRecyclerView.setAdapter(dynamicRecyclerViewAdapter);

        dynamicRecyclerViewAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 10) {
                    items.add(null);
                    dynamicRecyclerViewAdapter.notifyItemInserted(items.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size() - 1);
                            dynamicRecyclerViewAdapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index + 10;
                            for (int i = index;i < end;i++) {
                                String petName = UUID.randomUUID().toString();
                                String a = "a";
                                String b = "b";
                                DynamicRecyclerViewModel item = new DynamicRecyclerViewModel(petName,a,b);
                                items.add(item);
                            }
                            dynamicRecyclerViewAdapter.notifyDataSetChanged();
                            dynamicRecyclerViewAdapter.setLoaded();
                        }
                    }, 4000);
                }
                else
                    Toast.makeText(PetLoverLostPetAlert.this, "End of alerts.", Toast.LENGTH_SHORT).show();
            }
        });

        petLoverLostPetAlertNewAlertButton = findViewById(R.id.petLoverLostPetAlertNewAlertButton);
        petLoverLostPetAlertTextViewHello = findViewById(R.id.petLoverLostPetAlertTextViewHello);
        petLoverLostPetAlertTextViewHaveYou = findViewById(R.id.petLoverLostPetAlertTextViewHaveYou);
        petLoverLostPetAlertImageViewPaw = findViewById(R.id.petLoverLostPetAlertImageViewPaw);

        petLoverLostPetAlertNewAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetLoverLostPetAlert.this,NewLostPetAlert.class);

                Pair[] pairs = new Pair[4];

                pairs[0] = new Pair<View,String>(petLoverLostPetAlertImageViewPaw, "mainLogo");
                pairs[1] = new Pair<View,String>(petLoverLostPetAlertTextViewHello, "loginText");
                pairs[2] = new Pair<View,String>(petLoverLostPetAlertTextViewHaveYou, "loginText2");
                pairs[3] = new Pair<View,String>(petLoverLostPetAlertNewAlertButton, "loginButton");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PetLoverLostPetAlert.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }
}