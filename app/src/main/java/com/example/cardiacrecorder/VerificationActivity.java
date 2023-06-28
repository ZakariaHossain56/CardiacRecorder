package com.example.cardiacrecorder;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import java.util.Random;

public class VerificationActivity extends AppCompatActivity {
    TextView textView1 = findViewById(R.id.toasteq);
    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Email Verification");
        setContentView(R.layout.activity_verification);
        String email = getIntent().getStringExtra("EMAIL_VERIFICATION");
        TextView textView = findViewById(R.id.emailverify);

        if (email.isEmpty()) {
            textView.setText("Email not found");
        } else {
            textView.setText("your email : " + email);
            sendVerificationCode(email);
        }


    }

   /* private void sendVerificationCode(String email) {


        // Firebase Authentication's built-in email sending feature
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(VerificationActivity.this, "A link has been sent to your email", Toast.LENGTH_SHORT).show();

                            FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                                @Override
                                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null && user.isEmailVerified()) {
                                        Toast.makeText(VerificationActivity.this, "Email verified", Toast.LENGTH_SHORT).show();
                                        textView1.setText("Email Verified");

                                    }
                                }
                            });
                        } else {
                            Toast.makeText(VerificationActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                            textView1.setText("Invalid email ");
                        }
                    }
                });
    }*/



    private void sendVerificationCode(String email) {
        // Generate a 6-digit verification code
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        verificationCode = String.valueOf(code);

        // TODO: Replace the following with your SMTP server and email credentials
        final String username = "";
        final String password = "";
        final String smtpHost = "";
        final int smtpPort = 587;

        // Set up JavaMail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Create a Session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Verification Code");
            message.setText("Your verification code is: " + verificationCode);

            // Send the email
            Transport.send(message);

            Toast.makeText(VerificationActivity.this, "Verification code sent to your email", Toast.LENGTH_SHORT).show();
        } catch (MessagingException e) {
            Log.e(TAG, "Failed to send verification email", e);
            Toast.makeText(VerificationActivity.this, "Failed to send verification code", Toast.LENGTH_SHORT).show();
        }


    }

}