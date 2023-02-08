package com.example.zhem_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SuzgiActivity extends AppCompatActivity {
RelativeLayout layout_turi,layout_aimaq;
TextView txt_turi,txt_aimaq,txt_sany,txt_tonnadan_kg_bastap,txt_habarlandyru_sany;
EditText edt_baga_kein,edt_baga_dein,edt_uaqyt,edt_sany;
CheckBox checkbox_photo,checkbox_tasmaldau;
CardView btn_habarlandyru_korsetu,suzgini_tazalau;
ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suzgi);
        layout_turi = findViewById(R.id.layout_turi);
        layout_aimaq = findViewById(R.id.layout_aimaq);
        txt_turi = findViewById(R.id.txt_turi);
        txt_aimaq = findViewById(R.id.txt_aimaq);
        txt_sany = findViewById(R.id.txt_sany);
        txt_tonnadan_kg_bastap = findViewById(R.id.txt_tonnadan_kg_bastap);
        txt_habarlandyru_sany = findViewById(R.id.txt_habarlandyru_sany);
        edt_baga_kein = findViewById(R.id.edt_baga_kein);
        edt_baga_dein = findViewById(R.id.edt_baga_dein);
        edt_uaqyt = findViewById(R.id.edt_uaqyt);
        edt_sany = findViewById(R.id.edt_sany);
        checkbox_photo = findViewById(R.id.checkbox_photo);
        checkbox_tasmaldau = findViewById(R.id.checkbox_tasmaldau);
        btn_habarlandyru_korsetu = findViewById(R.id.btn_habarlandyru_korsetu);
        suzgini_tazalau = findViewById(R.id.suzgini_tazalau);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SuzgiActivity.this,MainActivity.class) );
            }
        });
    }
}