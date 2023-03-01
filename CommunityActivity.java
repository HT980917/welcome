package com.trainer.heart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {

    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;
    private int categoryNumber;
    private ArrayList<String> postNumber;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorypostlist);

        m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        m_ListView = findViewById(R.id.list);
        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);
        m_ListView.setOnItemClickListener(onClickListItem);

        //현재 activity를 시작한 activity에서 보낸 데이터를 읽음
        //그 데이터에 해당하는 layout을 보여줌
        //각 layout은 각 영화의 정보를 담고 있음
        categoryNumber = getIntent().getIntExtra("category", 0);
        switch (categoryNumber) {
            case 1:
                setTitle(R.string.feedback);
                categoryName = new String("feedback");
                break;
            case 2:
                setTitle(R.string.sportswear);
                categoryName = new String("sportswear");
                break;
            case 3:
                setTitle(R.string.faq);
                categoryName = new String("faq");
                break;
        }

        loadPostList();

        Button writeButton = (Button) findViewById(R.id.write);
        writeButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CommunityActivity.this, postWriteActivity.class);
                intent.putExtra("category", categoryNumber);
                startActivity(intent);
            }
        });

    }
    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CommunityActivity.this, postReadActivity.class);
            intent.putExtra("category", categoryNumber);
            intent.putExtra("postNumber", postNumber.get(position));
            startActivity(intent);
        }
    };

    public void loadPostList(){
        FirebaseDatabase.getInstance().getReference(categoryName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m_Adapter.clear();
                postNumber = new ArrayList<String>();
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    postNumber.add(snapshot.child("postNumber").getValue().toString());
                    i++;
                    Log.d("CommunityActivity", snapshot.child("title").getValue().toString());
                    m_Adapter.add(snapshot.child("title").getValue().toString());
                }
                m_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}