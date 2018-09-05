package com.example.eps.eps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class IndentRevision  extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indent_revision);

        this.setTitle("Indent Revision - "+ "Addanki");
        // Button orderButton = (Button) findViewById(R.id.end);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuoption_indent_generation:
                //your code
                // EX : call intent if you want to swich to other activity
                Intent intent = new Intent(IndentRevision.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuoption_indent_raised:
                //your code
                Intent intent2 = new Intent(IndentRevision.this, IndentsRaised.class);
                startActivity(intent2);
                return true;
            case R.id.menuoption_indent_revision:
                //your code
//                Intent intent3 = new Intent(IndentRevision.this, IndentRevision.class);
//                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
