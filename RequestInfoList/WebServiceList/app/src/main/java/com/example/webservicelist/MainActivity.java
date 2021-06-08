package com.example.webservicelist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webservicelist.Adapters.UserAdapter;
import com.example.webservicelist.Models.Result;
import com.example.webservicelist.Models.UserList;
import com.example.webservicelist.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<UserList> list;
    UserAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        define();
        request();

    }

    private void define() {
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlert(list.get(position).getId());
            }
        });
    }

    public void request() {
        list = new ArrayList<>();
        Call<List<UserList>> x = ManagerAll.getInstance().getLists();
        x.enqueue(new Callback<List<UserList>>() {
            @Override
            public void onResponse(Call<List<UserList>> call, Response<List<UserList>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    adp = new UserAdapter(getApplicationContext(),MainActivity.this,list);
                    listView.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<UserList>> call, Throwable t) {

            }
        });

    }

    public void showAlert(final String id){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.layoutmsg,null);

        Button canBtn, delBtn;
        canBtn=view.findViewById(R.id.canBtn);
        delBtn=view.findViewById(R.id.dltBtn);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog alertDialog = alert.create();

        canBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Result> delete = ManagerAll.getInstance().deleteItem(id);
                delete.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),response.body().getResult(),Toast.LENGTH_LONG).show();
                            alertDialog.cancel();
                            request();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }
        });
        alertDialog.show();
    }

}

