package com.example.pavan.oreo81;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pavan.oreo81.databaseutils.AppDatabase;
import com.example.pavan.oreo81.databaseutils.User;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText editText,editLastName;
    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editText=findViewById(R.id.editText);
        editLastName=findViewById(R.id.editText2);
        txtResult=findViewById(R.id.text_result);

        findViewById(R.id.button_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ad validations

               new MydataLoader().execute(0);
            }
        });

        findViewById(R.id.btn_retrieve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ad validations

                new MydataLoader().execute(1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MydataLoader extends AsyncTask<Integer , Void, List<User> > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<User> doInBackground(Integer ... params) {
            switch (params[0])
            {
                case 0:
                    User user=new User();
                    user.setUid(UUID.randomUUID().toString());
                    user.setFirstName(editText.getText().toString());
                    user.setLastName(editLastName.getText().toString());
                    user.setRole("S");
                    AppDatabase.getAppDatabase(MainActivity.this).userDao().insertAll(user);
                    break;
                case 1:
                    return  AppDatabase.getAppDatabase(MainActivity.this).userDao().getAll();

                default:
                    break;
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<User> s) {
            super.onPostExecute(s);
            editText.setText("");
            editLastName.setText("");
            if(s!=null && s.size()>0)
            {
                StringBuilder stringBuilder=new StringBuilder();
                for (User user:s)
                {
                    stringBuilder.append(user.getFirstName()).append(" ").append(user.getLastName()).append(" ").append(user.getRole()).append("\n");
                }

                txtResult.setText(stringBuilder);
            }
        }
    }
}
