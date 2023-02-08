package com.example.zhem_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.zhem_shop.Adapters.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edtEmail,edtPass;
    CheckBox checkbox_show_password;
    CardView btn_kiru;
    TextView txt_tirkelu;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btn_kiru = findViewById(R.id.btn_kiru);
        txt_tirkelu = findViewById(R.id.txt_tirkelu);
        checkbox_show_password = findViewById(R.id.checkbox_show_password);
        loadingBar  = new ProgressDialog(this);
        txt_tirkelu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class) );
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
        btn_kiru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticationUser();
            }
        });
    }

    private void authenticationUser() {

        String email = edtEmail.getText().toString()+"@gmail.com";
        String password = edtPass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,R.string.email_jazynyz,Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,R.string.qupia_soz_jazynyz,Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle(R.string.kiru);
            loadingBar.setMessage(getApplicationContext().getResources().getString(R.string.kute_turynyz));
            loadingBar.show();
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loadingBar.dismiss();
                            FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    GlobalVar.currentUser = snapshot.getValue(User.class);
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this,R.string.okinishke_orai_kiru,Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}