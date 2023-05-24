package com.example.attendancemontering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricFragment;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MarkAttendance extends AppCompatActivity {

    BiometricPrompt biometricPrompt;
    /*The BiometricPrompt class is part of the AndroidX Biometric library introduced in Android 9 (API level 28) as a standardized way to authenticate users using biometric data,
such as fingerprints or face recognition. It provides a system-provided biometric dialog for authentication purposes.*/
    BiometricPrompt.PromptInfo promptInfo;
    /*The PromptInfo class is a nested class within BiometricPrompt that represents the configuration options for the biometric prompt.
It allows you to customize the appearance and behavior of the biometric dialog, such as the title, subtitle, and other related settings*/
    ConstraintLayout mMainlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        mMainlayout = findViewById(R.id.mainlayout);
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "Device doesn't have required hardware", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Hardware not responding", Toast.LENGTH_SHORT).show();
                break;


            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "Biometric not enrolled", Toast.LENGTH_SHORT).show();
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MarkAttendance.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MarkAttendance.this, "Login success", Toast.LENGTH_SHORT).show();
               /* mMainlayout.setVisibility(View.VISIBLE);*/
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Vinit")
                .setDescription("Mark here").setDeviceCredentialAllowed(true).build();

        biometricPrompt.authenticate(promptInfo);
    }
}