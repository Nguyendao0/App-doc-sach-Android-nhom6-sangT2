package com.example.helloworldjava.view.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.presenter.AccountSettingPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class AccountSettingActivity extends AppCompatActivity implements AccountSettingView {
    private TextInputEditText txtUsername;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AccountSettingPresenter accountSettingPresenter = new AccountSettingPresenter(this);
        accountSettingPresenter.fillUserDataToEditView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public void fillUserDataToEditView(User user) {
        txtUsername = findViewById(R.id.usernameTextInputEditText);
        txtPassword = findViewById(R.id.passwordTextInputEditText);
        txtEmail = findViewById(R.id.emailTextInputEditText);

        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtEmail.setText(user.getEmail());
    }
}