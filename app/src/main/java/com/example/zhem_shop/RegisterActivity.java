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
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhem_shop.Adapters.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;
    EditText edtEmail,edtName,edtPhone,edtPass;
    CheckBox checkbox_agree,checkbox_show_password;
    ImageView img_document;
    CardView btn_tirkelu;
    TextView txt_kiru;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        userRef = db.getReference("User");
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtPass = findViewById(R.id.edtPass);
        btn_tirkelu = findViewById(R.id.btn_tirkelu);
        txt_kiru = findViewById(R.id.txt_kiru);
        checkbox_show_password = findViewById(R.id.checkbox_show_password);
        checkbox_agree = findViewById(R.id.checkbox_agree);
        loadingBar  = new ProgressDialog(this);

        txt_kiru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class) );
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
        btn_tirkelu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }

        });
    }
    private void registerUser() {
        String email2 = edtEmail.getText().toString();
        String email = email2+"@gmail.com";
        String pass = edtPass.getText().toString();
        RegisterUser(email,pass);
    }
    private void RegisterUser(String email, String pass) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,R.string.email_jazynyz,Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,R.string.zhana_qupia_soz_jazynyz,Toast.LENGTH_SHORT).show();
        }
        if(pass.length()<6){
            Toast.makeText(getApplicationContext(),R.string.zhana_qupia_soz_6,Toast.LENGTH_SHORT).show();
        }
        if (!checkbox_agree.isChecked()){
            Toast.makeText(getApplicationContext(),R.string.barlyq_erejelermen,Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle(R.string.tirkelu);
            loadingBar.setMessage("Please,wait...");
            loadingBar.show();
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User();
                            user.setEmail(edtEmail.getText().toString());
                            user.setName(edtName.getText().toString());
                            user.setPhone(edtPhone.getText().toString());
                            user.setPassword(edtPass.getText().toString());
                            userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this,R.string.okinishke_orai_tirkelu,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,email+R.string.esimi_bos_emes,Toast.LENGTH_SHORT).show();
                        }
                        loadingBar.dismiss();
                    }
                });
            loadingBar.dismiss();
        }
    }
}