package abby.finalproject_abbylcassien1.walkincloset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.Firebase;

import abby.finalproject_abbylcassien1.R;

public class WalkInCloset extends AppCompatActivity {
//
//
//    private List<Clothes> clothes;
//    private ClothesAdapter clothesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_in_closet);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.btn_star);
    }

    public void top(View view) {
        Intent intent = new Intent(this, TopsMain2.class);
        startActivity(intent);
    }

    public void bottom(View view) {
    }

    public void shoes(View view) {
    }

    public void jacket(View view) {
    }

    public void access(View view) {
    }

    public void other(View view) {
    }

    //Log out menu button
    private Firebase rootRef = new Firebase("https://abbyandcassie.firebaseio.com/");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                rootRef.unauth();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
