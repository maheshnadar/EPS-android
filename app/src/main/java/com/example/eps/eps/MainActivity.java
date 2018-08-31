package com.example.eps.eps;

import android.app.ProgressDialog;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;
//import com.example.eps.eps.ApiCall;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private boolean userIsInteracting;
    String[] bankNames={"BOI","SBI","HDFC","PNB","OBC"};
    String[] branchName={"Mumbai","delhi","chennai"};
    String[] regionName={"worli","sion","dadar"};
    ConstraintLayout detailsLayout;
    Spinner bankSpinner,branchSpinner,regionSpinner;
    Button searchBtn,approvalBtn;
    ScrollView detailScroll;
    ProgressBar loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();

//
        ArrayAdapter bankAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankSpinner.setAdapter(bankAdapter);
        bankSpinner.setSelection(0,true);
        bankSpinner.setSelected(false);
        bankSpinner.setOnItemSelectedListener(this);
//        branchSpinner.setAdapter(null);
//        regionSpinner.setAdapter(null);


    }

    public void initializeUI(){
        bankSpinner = (Spinner) findViewById(R.id.bank_spinner);
        branchSpinner = (Spinner) findViewById(R.id.branch_spinner);
        regionSpinner = (Spinner) findViewById(R.id.region_spinner);
        detailsLayout = (ConstraintLayout) findViewById(R.id.details);
        searchBtn = (Button) findViewById(R.id.search);
        approvalBtn = (Button) findViewById(R.id.approval);
        detailScroll=(ScrollView) findViewById(R.id.detailscroll);
        loader=findViewById(R.id.loader);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }


//    Spinner dropdown = findViewById(R.id.spinner);
//    //create a list of items for the spinner.
//    String[] items = new String[]{"1", "2", "three"};
//    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
////There are multiple variations of this, but this is the basic variant.
//    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
////set the spinners adapter to the previously created one.
//    dropdown.setAdapter(adapter);



    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        if (!userIsInteracting) {
            Log.i("main","user interacting");
            return ;
        }
        Log.i("TAG", "Information level message"+arg0.getId()+"=="+R.id.bank_spinner+"__________"+ arg1+"------"+id);

        if(arg0.getId()== R.id.bank_spinner){
Log.i("inside if","bank spinner"+R.id.bank_spinner+"---"+arg0.getId());
            ArrayAdapter branchAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,branchName);
            branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            branchSpinner.setAdapter(branchAdapter);
            branchSpinner.setSelection(0,true);
            branchSpinner.setSelected(false);
            branchSpinner.setOnItemSelectedListener(this);
           // regionSpinner.setAdapter(null);
        }
        else if(arg0.getId()== R.id.branch_spinner){
            Log.i("inside if","branch spinner"+R.id.branch_spinner+"---"+arg0.getId());
            ArrayAdapter regionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,regionName);
            regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            regionSpinner.setAdapter(regionAdapter);
            regionSpinner.setSelection(0,true);
            regionSpinner.setSelected(false);
            regionSpinner.setOnItemSelectedListener(this);
        }
        else if(arg0.getId()== R.id.region_spinner){
            Log.i("inside if","region spinner"+R.id.region_spinner+"---"+arg0.getId());
searchBtn.setClickable(true);

        }
        Toast.makeText(getApplicationContext(), bankNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }


    public void search(View view)
    {
        detailScroll.setVisibility(View.VISIBLE);
        approvalBtn.setVisibility(View.VISIBLE);
        // Do something in response to button click
    }

    public void reset(){
        detailScroll.setVisibility(View.INVISIBLE);
        approvalBtn.setVisibility(View.INVISIBLE);
    }
    public void showLoader(){
        loader.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        loader.setVisibility(View.INVISIBLE);
    }

    // event listener



}
