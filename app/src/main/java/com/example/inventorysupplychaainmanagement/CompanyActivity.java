package com.example.inventorysupplychaainmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
    }
    public void onClickAddItem(View view) {
        startActivity(new Intent(CompanyActivity.this,AddItemActivity.class));
    }
}