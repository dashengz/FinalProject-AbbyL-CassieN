package abby.finalproject_abbylcassien1.walkincloset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import abby.finalproject_abbylcassien1.MainActivity;
import abby.finalproject_abbylcassien1.R;

public class ClothesViewActivity extends AppCompatActivity {

    private Firebase rootRef = new Firebase("https://abbyandcassie.firebaseio.com/");
    private Firebase userRef;
    private Firebase.AuthStateListener authStateListener;
    private CardAdapter cardAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops_main2);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.btn_star);


        Intent intent = getIntent();
        String message = intent.getStringExtra(WalkInCloset.TYPE);
        final String type = message;

        authStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    userRef = rootRef.child("users/" + authData.getUid());
                    cardAdapter = new CardAdapter(userRef.child("clothing"), ClothesViewActivity.this, type);
                    recyclerView.setAdapter(cardAdapter);
                } else {
                    Intent intent = new Intent(ClothesViewActivity.this, WalkInCloset.class);
                    startActivity(intent);
                }
            }
        };

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        rootRef.addAuthStateListener(authStateListener);
    }


    @Override
    protected void onPause() {
        super.onPause();
        rootRef.removeAuthStateListener(authStateListener);
    }


    //the list of all the information
    // ...which is given to the Adapter
    //...which is given to the Recycler View - knows how to populate info



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //this takes you back to home, but it makes you log back in!!!!!
            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            case R.id.log_out:
                rootRef.unauth();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
