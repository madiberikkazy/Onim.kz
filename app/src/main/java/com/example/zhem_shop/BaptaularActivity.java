package com.example.zhem_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhem_shop.Adapters.GlobalVar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BaptaularActivity extends AppCompatActivity {
private FirebaseAuth mAuth;
DatabaseReference databaseReference;
ProgressDialog progressDialog;
EditText edtEmail,edtPhone,edtName,edtPass,edtNewPass;
CheckBox checkbox_show_password,checkbox_show_new_password;
CardView btn_saqtau;
TextView txt_old_pass;
ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baptaular);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        getUserinfo();
        progressDialog = new ProgressDialog(this);
        txt_old_pass = findViewById(R.id.txt_old_pass);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtPass = findViewById(R.id.edtPass);
        edtNewPass = findViewById(R.id.edtNewPass);
        img_back = findViewById(R.id.img_back);
        btn_saqtau = findViewById(R.id.btn_saqtau);
        checkbox_show_password = findViewById(R.id.checkbox_show_password);
        checkbox_show_new_password = findViewById(R.id.checkbox_show_new_password);
        btn_saqtau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ValidAndSave();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaptaularActivity.this,ZhekeKabinetActivity.class) );
            }
        });
        checkbox_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        checkbox_show_new_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edtNewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    edtNewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void ValidAndSave() {
        if(TextUtils.isEmpty(edtPass.getText().toString())&&TextUtils.isEmpty(edtNewPass.getText().toString())){
            if(TextUtils.isEmpty(edtName.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.atynyzdy_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtPhone.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.tel_nom_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtEmail.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.email_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else{
                progressDialog.setTitle(R.string.malimetterdi_ozgertu);
                progressDialog.setMessage(getApplicationContext().getResources().getString(R.string.kute_turynyz));
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);

                HashMap<String,Object> userMap = new HashMap<>();
                userMap.put("name",edtName.getText().toString());
                userMap.put("phone",edtPhone.getText().toString());
                userMap.put("email",edtEmail.getText().toString());
                databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
            }
        }
        else {
            String txt_edtPass = edtPass.getText().toString();
            if(TextUtils.isEmpty(edtName.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.atynyzdy_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtPhone.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.tel_nom_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtEmail.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.email_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtPass.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.qupia_soz_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtNewPass.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.zhana_qupia_soz_jazynyz,Toast.LENGTH_SHORT).show();
            }
            else if(!txt_edtPass.equals(txt_old_pass.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.qupia_soz_saikes_kelmeidi,Toast.LENGTH_SHORT).show();
            }
            else if(edtNewPass.getText().toString().length()<6){
                Toast.makeText(getApplicationContext(),R.string.zhana_qupia_soz_6,Toast.LENGTH_SHORT).show();
            }
            else{
                progressDialog.setTitle(R.string.malimetterdi_ozgertu);
                progressDialog.setMessage(getApplicationContext().getResources().getString(R.string.kute_turynyz));
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
                HashMap<String,Object> userMap = new HashMap<>();
                userMap.put("name",edtName.getText().toString());
                userMap.put("phone",edtPhone.getText().toString());
                userMap.put("email",edtEmail.getText().toString());
                userMap.put("password",edtNewPass.getText().toString());
                databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
            }
        }
    }

    private void getUserinfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()&&snapshot.getChildrenCount()>0){
                    String name = snapshot.child("name").getValue().toString();
                    String phone = snapshot.child("phone").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();
                    String email2 = email.substring(0,email.length()-10);
                    String password = snapshot.child("password").getValue().toString();
                    edtName.setText(name);
                    edtPhone.setText(phone);
                    edtEmail.setText(email2);
                    txt_old_pass.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}