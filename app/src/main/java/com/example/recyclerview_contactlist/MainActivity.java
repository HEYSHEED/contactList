package com.example.recyclerview_contactlist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements contactAdapter.ItemEventListener{
private contactAdapter adapter;
private int editingItemPosition= -1;
private EditText fullNameEt;
private ImageView addNewContactBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final RecyclerView recyclerView=findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter=new contactAdapter(this);
        recyclerView.setAdapter(adapter);
        fullNameEt=findViewById(R.id.et_main_contactFullName);
        addNewContactBtn=findViewById(R.id.iv_main_addNewContact);
        addNewContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullNameEt.length()>0){
                    if(editingItemPosition>-1){
                        adapter.updateContact(fullNameEt.getText().toString(),editingItemPosition);
                        editingItemPosition= -1;
                        addNewContactBtn.setImageResource(R.drawable.baseline_white_24);
                    }
                    else{
                        adapter.addNewContact(fullNameEt.getText().toString());
                        recyclerView.scrollToPosition(0);

                    }
                    fullNameEt.setText("");
                   //todo add new contact

                }
            }
        });
    }

    @Override
    public void onItemClick(String fullName, int position) {
        editingItemPosition=position;
        fullNameEt.setText(fullName);
        addNewContactBtn.setImageResource(R.drawable.baseline_done_24);
    }
}