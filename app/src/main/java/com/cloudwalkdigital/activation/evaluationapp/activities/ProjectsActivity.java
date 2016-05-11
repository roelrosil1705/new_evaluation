package com.cloudwalkdigital.activation.evaluationapp.activities;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.adapter.ProjectAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class ProjectsActivity extends AppCompatActivity implements ProjectAdapter.OnItemClickListener{
    Toolbar mActionBarToolbar;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ProjectsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        ProjectAdapter myRecyclerViewAdapter = new ProjectAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());

        myRecyclerViewAdapter.add(0, "Dove School Event", "JO001", "April 25, 2016");
        myRecyclerViewAdapter.add(1, "Cream silk School Event", "JO002", "April 25, 2016");
        myRecyclerViewAdapter.add(2, "Royal School Event", "JO003", "April 25, 2016");
        myRecyclerViewAdapter.add(3, "Coca-Cola School Event", "JO004", "April 25, 2016");
        myRecyclerViewAdapter.add(4, "Sprite School Event", "JO005", "April 25, 2016");
        myRecyclerViewAdapter.add(5, "Wilkins School Event", "JO006", "April 25, 2016");
        myRecyclerViewAdapter.add(6, "Sunsilk School Event", "JO007", "April 25, 2016");
        myRecyclerViewAdapter.add(7, "Dove1 School Event", "JO008", "April 25, 2016");
        myRecyclerViewAdapter.add(8, "Dove2 School Event", "JO009", "April 25, 2016");
        myRecyclerViewAdapter.add(9, "Dove3 School Event", "JO010", "April 25, 2016");
        myRecyclerViewAdapter.add(10, "Dove4 School Event", "JO011", "April 25, 2016");
        myRecyclerViewAdapter.add(11, "Dove5 School Event", "JO0012", "April 25, 2016");

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Choose an Event");
    }

    @Override
    public void onItemClick(ProjectAdapter.ItemHolder item, int position) {
//        startActivity(new Intent(getApplicationContext(), EventActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//        Toast.makeText(getApplicationContext()," -//// ",Toast.LENGTH_LONG).show();
        Toast.makeText(this,
                "Remove " + position + " : " + item.getItemName(),
                Toast.LENGTH_SHORT).show();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_sample, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_simple:
//                SimpleActivity.startActivity(this);
//                return true;
//            case R.id.action_paint:
//                PaintActivity.startActivity(this);
//                return true;
//            case R.id.action_drawable:
//                DrawableActivity.startActivity(this);
//                return true;
//            case R.id.action_complex:
//                ComplexActivity.startActivity(this);
//                return true;
//            case R.id.action_simple_grid:
//                SimpleGridActivity.startActivity(this);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
