package com.dimakaplin143.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button okBtn;
    private Button regBtn;

    private EditText editLogin;
    private EditText editPassword;

    private StorageControl storage;

    private final String LOGIN = "login";
    private final String PASSWORD = "password";

    private String message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


    }

    public void initViews() {
        okBtn = findViewById(R.id.ok_btn);
        regBtn = findViewById(R.id.reg_btn);

        editLogin = findViewById(R.id.edit_login);
        editPassword = findViewById(R.id.edit_password);

        storage = new StorageControl(this);

        okBtn.setOnClickListener(v-> {
            if(storage.isExist(LOGIN) && storage.isExist(PASSWORD)) {
                String inputLogin = editLogin.getText().toString();
                String inputPassword = editPassword.getText().toString();
                String login = storage.readFile(LOGIN);
                String password = storage.readFile(PASSWORD);
                if(inputLogin.equals(login) && inputPassword.equals(password)) {
                    message = getResources().getText(R.string.valid_msg).toString();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    message = getResources().getText(R.string.invalid_msg).toString();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            } else {
                message = getResources().getText(R.string.need_register).toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        regBtn.setOnClickListener(v-> {
            String inputLogin = editLogin.getText().toString();
            String inputPassword = editPassword.getText().toString();
            if(!"".equals(inputLogin) && !"".equals(inputPassword)) {
                storage.saveFile(LOGIN, inputLogin);
                storage.saveFile(PASSWORD, inputPassword);
                message = getResources().getText(R.string.reg_ok).toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            } else {
                message = getResources().getText(R.string.fill_fields).toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });



    }


}
