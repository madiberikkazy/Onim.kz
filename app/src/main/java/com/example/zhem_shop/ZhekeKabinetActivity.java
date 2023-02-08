package com.example.zhem_shop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ZhekeKabinetActivity extends AppCompatActivity {
RelativeLayout tandauly_tauarlar,menin_habarlamalarym,zhana_habarlandyru_engizu,qosymsha_turaly,baptaular,shygu,til;
ImageView img_back;
Dialog dialog_til;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zheke_kabinet);
        dialog_til = new Dialog(this);
        tandauly_tauarlar = findViewById(R.id.tandauly_tauarlar);
        menin_habarlamalarym = findViewById(R.id.menin_habarlamalarym);
        zhana_habarlandyru_engizu = findViewById(R.id.zhana_habarlandyru_engizu);
        qosymsha_turaly = findViewById(R.id.qosymsha_turaly);
        shygu = findViewById(R.id.shygu);
        baptaular = findViewById(R.id.baptaular);
        til = findViewById(R.id.til);
        img_back =findViewById(R.id.img_back);
        shygu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        AlertDialog.Builder alertdialog = new AlertDialog.Builder(ZhekeKabinetActivity.this);
                        alertdialog.setTitle(R.string.paraqsha);
                        alertdialog.setMessage(R.string.paraqshadan_shygudy);
                        alertdialog.setPositiveButton(R.string.ia, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(ZhekeKabinetActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        alertdialog.setNegativeButton(R.string.zhoq, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertdialog.show();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhekeKabinetActivity.this,MainActivity.class) );
            }
        });
        baptaular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhekeKabinetActivity.this,BaptaularActivity.class) );
            }
        });
        zhana_habarlandyru_engizu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhekeKabinetActivity.this,ZhanaHabarlamaActivity.class) );
            }
        });
        qosymsha_turaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhekeKabinetActivity.this,QosymshaTuralyActivity.class));
            }
        });
        til.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_til.setContentView(R.layout.dialog_til);
                dialog_til.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog_til.show();
            }
        });
    }
}