package com.example.car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ViewCarActivity extends AppCompatActivity {

    private EditText et_model,et_color,et_Dpl,et_descroition;
    private Toolbar toolbar;

    private ImageView et_image;
    public  int carID=-1;

    private DatabaseAccess db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car_activty);

        et_image=findViewById(R.id.et_image);
        et_model =findViewById(R.id.et_model);
        et_color =findViewById(R.id.et_color);
        et_Dpl =findViewById(R.id.et_distanceperletter);
        et_descroition =findViewById(R.id.et_description);

        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = DatabaseAccess.getInstance(this);

        Intent intent = getIntent();
        carID =intent.getIntExtra(MainActivity.CAR_KAY,-1);

        if (carID == -1){
            enablefields();
            clearFields();
        }else {
            disablefields();
            db.open();
            CarModel c =db.getcar(carID);
            db.close();
            if (c != null){
                fillcartoFiles(c);
            }
        }

    }



    private void fillcartoFiles(CarModel c){
        if(c.getImage() != null &&c.getImage().equals(""))
         et_image.setImageURI(Uri.parse(c.getImage()));
        et_model.setText(c.getModel());
        et_color.setText(c.getColor());
        et_descroition.setText(c.getDescription());
        et_Dpl.setText(String.valueOf(c.getDpl()));
    }

    private void disablefields(){
        et_image.setEnabled(false);
        et_color.setEnabled(false);
        et_model.setEnabled(false);
        et_descroition.setEnabled(false);
        et_Dpl.setEnabled(false);
    }

    private void enablefields(){
        et_image.setEnabled(false);
        et_color.setEnabled(true);
        et_model.setEnabled(true);
        et_descroition.setEnabled(true);
        et_Dpl.setEnabled(true);
    }

    private void clearFields(){
        et_image.setImageURI(null);
        et_color.setText("");
        et_model.setText("");;
        et_descroition.setText("");;
        et_Dpl.setText("");;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detales_menu,menu);
        MenuItem delete = findViewById(R.id.delete);
        MenuItem save = findViewById(R.id.save);
        MenuItem edit = findViewById(R.id.edit);
        if(carID==-1){
            save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);
        }else {
            save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                return true;
            case R.id.delete:
                return true;
            case R.id.edit:
                return true;
        }
        return false;
    }
}
