package com.example.inventorysupplychaainmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
    }
    public void onClickCompanyName(View view) {
        startActivity(new Intent(PurchaseActivity.this,CompanyActivity.class));
    }
    public void onClickAdd(View view) {
        startActivity(new Intent(PurchaseActivity.this,AddNewCompanyActivity.class));
    }
}