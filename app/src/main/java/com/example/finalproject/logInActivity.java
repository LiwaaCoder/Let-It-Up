package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class logInActivity extends AppCompatActivity
{
    private ActivityLoginBinding binding;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    private FirebaseAuth firebaseAuth;
    private String mVerficiationId;
    private static final String TAG = "MAIN_TAG";
    private ProgressDialog pd;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.phone.setVisibility(View.VISIBLE);
        binding.loginTxtOTP.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        pd.setTitle("PLease Wait ... ");
        pd.setCanceledOnTouchOutside(false);
        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneCredentials(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e)
            {
                pd.dismiss();
                Toast.makeText(logInActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCodeSent(@NonNull String verificationid, @NonNull PhoneAuthProvider.ForceResendingToken token)
            {
                super.onCodeSent(verificationid , forceResendingToken);
                Log.d(TAG,"OnCodeSent"+verificationid);
                mVerficiationId = verificationid;
                forceResendingToken=token;
                pd.dismiss();

                binding.phone.setVisibility(View.GONE);
                Toast.makeText(logInActivity.this,"Verification Code Sent",Toast.LENGTH_SHORT).show();

                binding.codeSent.setText("PLease Type The code we have sent  \n to"+binding.phoneEt.getText().toString().trim());

            }
        };
        binding.loginBTNLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    String phone = binding.phoneEt.getText().toString().trim();
                    if(TextUtils.isEmpty(phone))
                        Toast.makeText(logInActivity.this,"Please Enter Phone Number : ",Toast.LENGTH_SHORT).show();
                    else
                        startPhoneNumberVerification(phone);
            }
        });

        binding.codeSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone = binding.phoneEt.getText().toString().trim();
                if(TextUtils.isEmpty(phone))
                    Toast.makeText(logInActivity.this,"Please Enter Phone Number : ",Toast.LENGTH_SHORT).show();
                else
                    resendVerificationCode(phone,forceResendingToken);


            }
        });

        binding.codeResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String code = binding.codeEt.getText().toString().trim();
                if(TextUtils.isEmpty(code))
                    Toast.makeText(logInActivity.this,"PLease Enter Verification Code : ",Toast.LENGTH_SHORT).show();
                else
                    verifyNumberWithCode(code);

            }
        });

    }




    private void startPhoneNumberVerification(String phone)
    {
        pd.setMessage("Verifying Phone Number");
        pd.show();
        PhoneAuthOptions opt =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(opt);
    }


    private void resendVerificationCode(String phone , PhoneAuthProvider.ForceResendingToken token)
    {
        pd.setTitle("Resending Code");
        pd.show();

        PhoneAuthOptions opt =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBacks)
                        .setForceResendingToken(token)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(opt);
    }

    private void verifyNumberWithCode(String code)
    {
        pd.setMessage("Verifying Code");
        pd.show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerficiationId,code);
        signInWithPhoneCredentials(credential);

}

    private void signInWithPhoneCredentials(PhoneAuthCredential credential)
    {
        pd.setMessage("Logging In");
        pd.show();
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>()
        {
            @Override
            public void onSuccess(AuthResult authResult)
            {
                pd.dismiss();
                String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                Toast.makeText(logInActivity.this,"Logged In as"+phone,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(logInActivity.this, Concert_Schedule_activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        pd.dismiss();
                        Toast.makeText(logInActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

    }
}