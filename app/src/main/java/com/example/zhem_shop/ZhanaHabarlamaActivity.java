package com.example.zhem_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ZhanaHabarlamaActivity extends AppCompatActivity {
private FirebaseAuth mAuth;
DatabaseReference databaseReference;
ProgressDialog progressDialog;
ImageView img_satushy_product,img_back;
CardView img_pencil_satushy_product,btn_habarlandyru_korsetu;
RelativeLayout layout_turi,layout_aimaq;
TextView txt_turi,txt_aimaq,txt_tonnadan_kg_bastap,txt_sany;
EditText edt_baga,edt_sany,edt_satushy_name,edt_satushy_nomeri,edt_satushy_koment;
CheckBox checkbox_tasmaldau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhana_habarlama);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        getUserinfo();
        progressDialog = new ProgressDialog(this);
        img_satushy_product = findViewById(R.id.img_satushy_product);
        img_back = findViewById(R.id.img_back);
        img_pencil_satushy_product = findViewById(R.id.img_pencil_satushy_product);
        btn_habarlandyru_korsetu = findViewById(R.id.btn_habarlandyru_korsetu);
        layout_turi = findViewById(R.id.layout_turi);
        layout_aimaq = findViewById(R.id.layout_aimaq);
        txt_turi = findViewById(R.id.txt_turi);
        txt_tonnadan_kg_bastap = findViewById(R.id.txt_tonnadan_kg_bastap);
        txt_aimaq = findViewById(R.id.txt_aimaq);
        txt_sany = findViewById(R.id.txt_sany);
        edt_baga = findViewById(R.id.edt_baga);
        edt_sany = findViewById(R.id.edt_sany);
        edt_satushy_name = findViewById(R.id.edt_satushy_name);
        edt_satushy_nomeri = findViewById(R.id.edt_satushy_nomeri);
        edt_satushy_koment = findViewById(R.id.edt_satushy_koment);
        checkbox_tasmaldau = findViewById(R.id.checkbox_tasmaldau);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhanaHabarlamaActivity.this,ZhekeKabinetActivity.class) );
            }
        });
    }

    private void getUserinfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()&&snapshot.getChildrenCount()>0){
                    String name = snapshot.child("name").getValue().toString();
                    String phone = snapshot.child("phone").getValue().toString();
                    edt_satushy_name.setText(name);
                    edt_satushy_nomeri.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}