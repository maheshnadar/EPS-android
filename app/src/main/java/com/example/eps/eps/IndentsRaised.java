package com.example.eps.eps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class IndentsRaised extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indent_raised);
        this.setTitle("Indent Raised");
       // Button orderButton = (Button) findViewById(R.id.end);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuoption_indent_generation:
                //your code
                // EX : call intent if you want to swich to other activity
                Intent intent = new Intent(IndentsRaised.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuoption_indent_raised:
                //your code
//                Intent intent = new Intent(IndentsRaised.this, IndentsRaised.class);
//                startActivity(intent);
                return true;
            case R.id.menuoption_indent_revision:
                //your code
                // EX : call intent if you want to swich to other activity
                Intent intent2 = new Intent(IndentsRaised.this, IndentRevision.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
