package com.example.pawfection;

import static com.example.pawfection.Login.userID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.example.pawfection.databinding.ActivityNewLostPetAlertBinding;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NewLostPetAlert extends PetLoverNavigationDrawerBase {

    ActivityNewLostPetAlertBinding activityNewLostPetAlertBinding;
    private Button newLostPetAlertButtonSelectFromGallery;
    private ImageView newLostPetAlertImageViewPet;
    private final int GALLERY_REQ_CODE = 1000;

    private TextInputLayout newLostPetAlertTextInputLayoutPetName;
    private TextInputLayout newLostPetAlertTextInputLayoutLastSeen;
    private TextInputLayout newLostPetAlertTextInputLayoutContactNumber;
    private Button newLostPetAlertButtonSubmit;

    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewLostPetAlertBinding = ActivityNewLostPetAlertBinding.inflate(getLayoutInflater());
        setContentView(activityNewLostPetAlertBinding.getRoot());

        newLostPetAlertButtonSelectFromGallery = findViewById(R.id.newLostPetAlertButtonSelectFromGallery);
        newLostPetAlertImageViewPet = findViewById(R.id.newLostPetAlertImageViewPet);

        newLostPetAlertTextInputLayoutPetName = findViewById(R.id.newLostPetAlertTextInputLayoutPetName);
        newLostPetAlertTextInputLayoutLastSeen = findViewById(R.id.newLostPetAlertTextInputLayoutLastSeen);
        newLostPetAlertTextInputLayoutContactNumber = findViewById(R.id.newLostPetAlertTextInputLayoutContactNumber);
        newLostPetAlertButtonSubmit = findViewById(R.id.newLostPetAlertButtonSubmit);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        newLostPetAlertButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePetName() && validateLastSeen() && validateContactNumber()) {
                    String petName = newLostPetAlertTextInputLayoutPetName.getEditText().getText().toString();
                    String lastSeen = newLostPetAlertTextInputLayoutLastSeen.getEditText().getText().toString();
                    String contactNumber = newLostPetAlertTextInputLayoutContactNumber.getEditText().getText().toString();
                    int users_id = userID;

                    compositeDisposable.add(myAPI.createNewAlert(petName, lastSeen, contactNumber, users_id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Throwable {
                                    Toast.makeText(NewLostPetAlert.this,s,Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(NewLostPetAlert.this,PetLoverLostPetAlert.class);
                                    startActivity(intent);
                                }
                            }));
                }
            }
        });


        newLostPetAlertButtonSelectFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });
    }

    public Boolean validatePetName() {
        String petName = newLostPetAlertTextInputLayoutPetName.getEditText().getText().toString();
        if (petName.isEmpty()) {
            newLostPetAlertTextInputLayoutPetName.setError("Field cannot be empty.");
            return false;
        }
        else {
            newLostPetAlertTextInputLayoutPetName.setError(null);
            newLostPetAlertTextInputLayoutPetName.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateLastSeen() {
        String lastSeen = newLostPetAlertTextInputLayoutLastSeen.getEditText().getText().toString();
        if (lastSeen.isEmpty()) {
            newLostPetAlertTextInputLayoutLastSeen.setError("Field cannot be empty.");
            return false;
        }
        else {
            newLostPetAlertTextInputLayoutLastSeen.setError(null);
            newLostPetAlertTextInputLayoutLastSeen.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateContactNumber() {
        String contactNumber = newLostPetAlertTextInputLayoutContactNumber.getEditText().getText().toString();
        if (contactNumber.isEmpty()) {
            newLostPetAlertTextInputLayoutContactNumber.setError("Field cannot be empty");
            return false;
        }
        else {
            newLostPetAlertTextInputLayoutContactNumber.setError(null);
            newLostPetAlertTextInputLayoutContactNumber.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                newLostPetAlertImageViewPet.setImageURI(data.getData());
            }
        }
    }
}