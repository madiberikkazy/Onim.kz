package com.example.zhem_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class QosymshaTuralyActivity extends AppCompatActivity {
RelativeLayout bagalau,dostarmen_bolisu,zhobaga_qooldau_korsetu;
ImageView img_back,img_instagramm,img_telegramm;
Dialog dialog_qoldau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qosymsha_turaly);
        dialog_qoldau = new Dialog(this);
        bagalau = findViewById(R.id.bagalau);
        dostarmen_bolisu = findViewById(R.id.dostarmen_bolisu);
        zhobaga_qooldau_korsetu = findViewById(R.id.zhobaga_qooldau_korsetu);
        img_back =findViewById(R.id.img_back);
        img_instagramm =findViewById(R.id.img_instagramm);
        img_telegramm =findViewById(R.id.img_telegramm);
        dostarmen_bolisu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getPackageName());
                startActivity(Intent.createChooser(intent, ""));
            }
        });
        zhobaga_qooldau_korsetu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_qoldau.setContentView(R.layout.dialog_qoldau);
                dialog_qoldau.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog_qoldau.show();
                TextView txt_qoldau = dialog_qoldau.findViewById(R.id.txt_qoldau);
                ImageView img_copy = dialog_qoldau.findViewById(R.id.img_copy);
                img_copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("Text View", txt_qoldau.getText().toString());
                        clipboardManager.setPrimaryClip(clipData);
                        Toast.makeText(QosymshaTuralyActivity.this, R.string.koshirildi, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QosymshaTuralyActivity.this,ZhekeKabinetActivity.class) );
            }
        });
        img_instagramm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/onim_app/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        img_telegramm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://t.me/onim_app"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        bagalau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}