package com.techmania.testfirebasejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAge, editTextEmail;
    Button buttonSend, buttonRead;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("users");

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSend = findViewById(R.id.buttonSend);
        buttonRead = findViewById(R.id.buttonRead);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = editTextName.getText().toString();
                String userAge = editTextAge.getText().toString();
                String userEmail = editTextEmail.getText().toString();

                writeData(userName,Integer.parseInt(userAge),userEmail);

            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readData();

            }
        });

    }

    private void writeData(String userName, int userAge, String userEmail) {

        key = reference.child("Users").push().getKey();
        Log.d("userkey", key);

        Users user = new Users(key,userName, userAge, userEmail);

        reference.child(key).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Write to database is successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"There is a problem", Toast.LENGTH_LONG).show();
                    Log.d("Error",task.getException().toString());
                }
            }
        });

    }

    private void readData(){

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Users users = dataSnapshot.getValue(Users.class);

                    if (users != null){
                        System.out.println("****************");
                        System.out.println("userId: " + users.getUserId());
                        System.out.println("userName: " + users.getUserName());
                        System.out.println("userAge: " + users.getUserAge());
                        System.out.println("userEmail: " + users.getUserEmail());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}