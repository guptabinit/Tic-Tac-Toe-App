package com.bg.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.bg.tic_tac_toe.R.id.copy_button;

public class info extends AppCompatActivity {

    ImageButton button;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        text = findViewById(R.id.email_id);
        button = findViewById(copy_button);

        //to copy a text
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Email Address", text.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(info.this,"Email address copied", Toast.LENGTH_SHORT).show();
            }
        });

    }
}