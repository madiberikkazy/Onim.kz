package com.example.zhem_shop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
CardView card_show_filter;
TextView txt_filter;
ImageView img_filter,img_user;
Dialog dialog_suzgi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        card_show_filter = findViewById(R.id.card_show_filter);
        txt_filter = findViewById(R.id.txt_filter);
        img_filter = findViewById(R.id.img_filter);
        img_user = findViewById(R.id.img_user);
        dialog_suzgi = new Dialog(this);
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ZhekeKabinetActivity.class) );

            }
        });
        img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dialog_suzgi.setContentView(R.layout.dialog_suzgi);
                    dialog_suzgi.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog_suzgi.show();
                RelativeLayout keneitilgen_s_korsetu  =dialog_suzgi.findViewById(R.id.keneitilgen_s_korsetu);
                ImageView img_back  =dialog_suzgi.findViewById(R.id.img_back);
                img_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_suzgi.dismiss();
                    }
                });
                keneitilgen_s_korsetu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,SuzgiActivity.class) );
                    }
                });
            }
        });
        card_show_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu =new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.filter,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_barlygy:
                                txt_filter.setText(R.string.barlygy);
                                break;
                            case R.id.menu_shop:
                                txt_filter.setText(R.string.shop);
                                break;
                            case R.id.menu_zhem:
                                txt_filter.setText(R.string.zhem);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
        alertdialog.setTitle(R.string.qosymsha);
        alertdialog.setMessage(R.string.qosymshadan_shygudy);
        alertdialog.setPositiveButton(R.string.ia, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
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
}