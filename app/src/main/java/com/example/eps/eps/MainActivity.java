package com.example.eps.eps;

import android.app.ProgressDialog;
import android.preference.PreferenceActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import com.example.eps.eps.ApiCall;

public class MainActivity extends AppCompatActivity  {

    private boolean userIsInteracting;
   // String[] bankNames={"BOI","SBI","HDFC","PNB","OBC"};
    //String[] branchName={"Mumbai","delhi","chennai"};
  //  String[] regionName={"worli","sion","dadar"};

    ArrayList<String> bankNames,branchName,regionName;
    ConstraintLayout resultLayout;
    Spinner bankSpinner,branchSpinner,regionSpinner;
    Button searchBtn,approvalBtn;
    ScrollView detailScroll;
    ProgressBar loader;
    ArrayAdapter bankAdapter,branchAdapter,regionAdapter;
    TextView temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //apiCall = new HttpUtils();
        initializeUI();
        createlist();
        initializeAdapter();
//
        bankSpinner.setOnItemSelectedListener(bank_listener);
        branchSpinner.setOnItemSelectedListener(branch_listener);
        regionSpinner.setOnItemSelectedListener(region_listener);



    }

    public void initializeUI(){
        bankSpinner = (Spinner) findViewById(R.id.bank_spinner);
        branchSpinner = (Spinner) findViewById(R.id.branch_spinner);
        regionSpinner = (Spinner) findViewById(R.id.region_spinner);
        resultLayout = (ConstraintLayout) findViewById(R.id.result);
        searchBtn = (Button) findViewById(R.id.search);
        approvalBtn = (Button) findViewById(R.id.approval);
        detailScroll=(ScrollView) findViewById(R.id.detailscroll);
        loader=findViewById(R.id.loader);

        temp=findViewById(R.id.temp);
    }
    public void createlist(){
        bankNames = new ArrayList<>();
        branchName =  new ArrayList<>();
        regionName=  new ArrayList<>();
        bankNames.add("select Bank");
        bankNames.add("SBI");
        bankNames.add("HDFC");
        bankNames.add("KOTAK");
//        branchName.add("select branch");
//        regionName.add("select region");
    }
    public void initializeAdapter(){
    bankAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames);
    bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    bankSpinner.setAdapter(bankAdapter);
//    bankSpinner.setSelection(0,true);
//    bankSpinner.setSelected(false);
//    bankSpinner.setOnItemSelectedListener(this);
//        branchSpinner.setAdapter(null);
//        regionSpinner.setAdapter(null);
    branchAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,branchName);
    branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    branchSpinner.setAdapter(branchAdapter);

    regionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,regionName);
    regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    regionSpinner.setAdapter(regionAdapter);
}

    private AdapterView.OnItemSelectedListener bank_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.i("main","inside bank listener");
            hideSearchResult();
            if (position > 0) {
                String selectedbank= (String) bankSpinner.getItemAtPosition(position);
                ArrayList<String> tempBranch = new ArrayList<>();
                tempBranch.add("select branch");
                tempBranch.add("DELHI");
                tempBranch.add("MUMBAI");
                tempBranch.add("CHENNAI");
                branchAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,tempBranch);
                branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                branchSpinner.setAdapter(branchAdapter);

                ArrayList<String> tempRegion = new ArrayList<>();
                regionAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,tempRegion);
                regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                regionSpinner.setAdapter(regionAdapter);
            }
            else{
//                ArrayList<String> tempBranch = new ArrayList<>();
//               // tempBranch.add("select branch");
//                branchAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,tempBranch);
//                branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                branchSpinner.setAdapter(branchAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private AdapterView.OnItemSelectedListener branch_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.i("main","inside branch listener");
            hideSearchResult();
            if (position > 0) {

                String selectedbranch= (String) bankSpinner.getItemAtPosition(position);
                ArrayList<String> tempRegion = new ArrayList<>();
                tempRegion.add("select branch");
                tempRegion.add("sion");
                tempRegion.add("matunga");
                tempRegion.add("dadar");
                regionAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,tempRegion);
                regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                regionSpinner.setAdapter(regionAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private AdapterView.OnItemSelectedListener region_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.i("main","inside region listener");
            if (position > 0) {

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };




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
//    @Override
//    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//
//        if (!userIsInteracting) {
//            Log.i("main","user interacting");
//            return ;
//        }
//        Log.i("TAG", "Information level message"+arg0.getId()+"=="+R.id.bank_spinner+"__________"+ arg1+"------"+id);
//
//        if(arg0.getId()== R.id.bank_spinner){
//Log.i("inside if","bank spinner"+R.id.bank_spinner+"---"+arg0.getId());
//
//           // regionSpinner.setAdapter(null);
//        }
//        else if(arg0.getId()== R.id.branch_spinner){
//            Log.i("inside if","branch spinner"+R.id.branch_spinner+"---"+arg0.getId());
//
//        }
//        else if(arg0.getId()== R.id.region_spinner){
//            Log.i("inside if","region spinner"+R.id.region_spinner+"---"+arg0.getId());
//searchBtn.setClickable(true);
//
//        }
//        Toast.makeText(getApplicationContext(), bankNames[position], Toast.LENGTH_LONG).show();
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> arg0) {
//// TODO Auto-generated method stub
//
//    }


    public void searchfn(View view)
    {

     if (bankSpinner.getSelectedItemPosition()==0) {

         Toast.makeText(getApplicationContext(),"Please Select Bank", Toast.LENGTH_LONG).show();
         return;
    }
        if (branchSpinner.getSelectedItemPosition()==0) {

            Toast.makeText(getApplicationContext(),"Please Select Branch", Toast.LENGTH_LONG).show();
            return;
        }
        if (regionSpinner.getSelectedItemPosition()==0) {

            Toast.makeText(getApplicationContext(),"Please Select region", Toast.LENGTH_LONG).show();
            return;
        }
        resultLayout.setVisibility(View.VISIBLE);
        approvalBtn.setVisibility(View.VISIBLE);
        // Do something in response to button click



    }

    public void detailsTogglefn(View view){
        if(detailScroll.getVisibility()== View.INVISIBLE)
        {
            detailScroll.setVisibility(View.VISIBLE);

        }
        else{
            detailScroll.setVisibility(View.INVISIBLE);
        }

    }

    public void hideSearchResult(){
        resultLayout.setVisibility(View.INVISIBLE);
        approvalBtn.setVisibility(View.INVISIBLE);
    }
    public void showLoader(){
        loader.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        loader.setVisibility(View.INVISIBLE);
    }



    // event listener


// add http request

    public void apicall(){
//        RequestQueue queue = Volley.newRequestQueue(this);
       String url ="http://www.google.com";

        RequestQueue queue1 = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST,"https://reqres.in/api/users", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                temp.setText("Response is: "+ response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                            temp.setText("That didn't work!"+error);
                NetworkResponse networkResponse = error.networkResponse;
                Toast.makeText(getApplicationContext(),networkResponse.toString(), Toast.LENGTH_LONG).show();
//                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
//                    // HTTP Status Code: 401 Unauthorized
//                }
            }
        }){
//            @Override
//            protected Map<String,String> getParams(){
//
//                //going in string format
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("user","value1");
//                params.put("pass","value2");
//                params.put("comment","value3");
//                params.put("comment_post_ID","value4");
//                params.put("blogId","value5");
//
//                return params;
//            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError  {
            //adding herder field
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }

            @Override
            public String getBodyContentType() {
                //adding only contenttype field
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                //sending body on post request

                JSONObject jsonBody = new JSONObject();

                try {
                    jsonBody.put("name", "morpheus");
                    jsonBody.put("job", "leader");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                final String requestBody = jsonBody.toString();
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };
        queue1.add(sr);
    }
}
