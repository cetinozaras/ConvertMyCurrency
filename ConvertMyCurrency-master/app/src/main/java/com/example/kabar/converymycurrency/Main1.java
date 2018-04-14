package com.example.kabar.converymycurrency;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.example.mutespittah.convertmycurrencyfinal.R;

public class Main1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        setContentView(R.layout.activity_main1);
        TextView Logo = (TextView) findViewById(R.id.textView);
        Button Enter= (Button) findViewById(R.id.button);
        findViewById(R.id.button).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main1.this,Main2.class));
            }
        });
    }
}