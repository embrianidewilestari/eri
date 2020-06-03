package com.example.eripolinema.view;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eripolinema.R;
import com.example.eripolinema.generator.UtilsApi;
import com.example.eripolinema.model.Pelapor;
import com.example.eripolinema.service.PelaporsService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        mPelaporsService = UtilsApi.getPelaporsService();
        // methode ini berfungsi untuk mendeklarasikan widget yang ada
        // di layout activity_daftar
        initComponents();
    }

    private void initComponents() {
        etIduser = (EditText) findViewById(R.id.etIduser);
        etNama = (EditText) findViewById(R.id.etNama);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etJurusan = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        txtLogin = (TextView) findViewById(R.id.txtLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestRegister();
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void requestRegister() {
        mPelaporsService.registerRequest(etIduser.getText().toString(), etNama.getText().toString(), etEmail.getText().toString(), etJurusan.getText().toString())
                .enqueue(new Callback<Pelapor>() {
                    @Override
                    public void onResponse(Call<Pelapor> call, Response<Pelapor> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: Berhasil");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().toString());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "Anda Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: Tidak Berhasil");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pelapor> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    EditText etIduser;
    EditText etNama;
    EditText etEmail;
    EditText etJurusan;
    Button btnRegister;
    TextView txtLogin;
    ProgressDialog loading;


    Context mContext;
    PelaporsService mPelaporsService;
}