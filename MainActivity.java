//Sparsh-Arya - Main Activity Screen of the App
package com.mywbut.studentapp; // Package name

import androidx.appcompat.app.AppCompatActivity;
//Packages for buttons
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//Networking Packages
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
// To create objects for JSON
import org.json.JSONException;
import org.json.JSONObject;
// To import hashmaps & maps
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText cgpa,iq,profile_score;
    Button predict;
    TextView result;
    String url = "https://campusx-student-app.herokuapp.com/predict"; //HTTP Request url

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intializing
        cgpa = findViewById(R.id.cgpa);
        iq = findViewById(R.id.iq);
        profile_score = findViewById(R.id.profile_score);
        predict = findViewById(R.id.predict);
        result = findViewById(R.id.result);
        // Action on pressing the button
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hit the API -> Volley
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("placement");
                                    if(data.equals("1")){
                                        result.setText("Student Will Crack the JOB!!");
                                    }else{
                                        result.setText("Student Needs to Work More Hard!!");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();// Network error
                            }
                        }){

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("cgpa",cgpa.getText().toString());
                        params.put("iq",iq.getText().toString());
                        params.put("profile_score",profile_score.getText().toString());
                        // Input Parameters
                        return params;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);
            }
        });
    }
}
