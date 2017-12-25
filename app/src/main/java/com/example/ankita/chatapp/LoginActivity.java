

/**
 * Created by Ankita on 17-12-2017.
 */

package com.example.ankita.chatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


//import com.loopj.android.http.*;




public class LoginActivity extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Button btn = (Button) findViewById(R.id.loginbutton);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {


                                       String URL = "http://infinite-oasis-40195.herokuapp.com/login";

                                       final EditText name = (EditText) findViewById(R.id.username);
                                       final EditText pass = (EditText) findViewById(R.id.password);


                                       StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                                               new Response.Listener<String>() {
                                                   @Override
                                                   public void onResponse(String response) {
                                                       // response
                                                       Log.d("Response", response);
                                                       Intent myIntent = new Intent(LoginActivity.this, ChatActivity.class);
                                                       startActivity(myIntent);

                                                   }
                                               },
                                               new Response.ErrorListener() {
                                                   @Override
                                                   public void onErrorResponse(VolleyError error) {
                                                       // error
                                                       Log.d("Error.Response", error.toString());
                                                       final Context mContext = getApplicationContext();
                                                       Toast.makeText(mContext, "Username or Email ID Incorrect", Toast.LENGTH_LONG).show();


                                                   }
                                               }
                                       ) {
                                           @Override
                                           protected Map<String, String> getParams() {
                                               Map<String, String> params = new HashMap<String, String>();
                                               params.put("username", name.getText().toString());
                                               params.put("password", pass.getText().toString());

                                               return params;
                                           }
                                       };
                                       requestQueue.add(postRequest);


                                   }

        });

        Button btn2 = (Button) findViewById(R.id.signupbutton);

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent2 = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(myIntent2);
            }
        });


                                   }
}




/*


POST REQUEST PREVIOUS CODE

                    JSONObject jsonBody = new JSONObject();
                    final EditText name = (EditText) findViewById(R.id.username);
                    final EditText pass = (EditText) findViewById(R.id.password);

                    jsonBody.put("username", name);
                    jsonBody.put("password", pass);
                    final String requestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY----->", response);

                            if(response==t) {
                                Intent myIntent = new Intent(LoginActivity.this, ChatActivity.class);
                                startActivity(myIntent);
                            }
                            else
                                {
                                    final Context mContext = getApplicationContext();

                                    Toast.makeText(mContext, "Username or Email ID Incorrect", Toast.LENGTH_LONG).show();

                                }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            String responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                                // can get more details such as response.headers
                            }
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    };

                    requestQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        });


    }



































GET REQUEST EXAMPLE

        final TextView mTextView = (TextView) findViewById(R.id.text);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.coinmarketcap.com/v1/ticker/?limit=1";

 Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
 Add the request to the RequestQueue.queue.add(stringRequest);
*/