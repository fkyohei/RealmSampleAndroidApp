package com.example.g_2015.realmsample;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import com.example.g_2015.realmsample.model.Task;

import java.util.Date;

public class MainActivity extends ActionBarActivity {

    Button todoAddButton;
    ListView todoListView;
    EditText todoAddTask;
    TaskAdapter taskadapter;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoAddButton = (Button) findViewById(R.id.addbtn);
        todoListView = (ListView) findViewById(R.id.listView);

        realm = Realm.getInstance(this);

        loadTodoList();
    }

    private void loadTodoList() {
        RealmQuery<Task> query = realm.where(Task.class);

        RealmResults<Task> results = query.findAll();
Log.i("", results.toString());
        taskadapter = new TaskAdapter(this, new TaskAdapter.OnItemClickListener() {
            public void onItemClick(Task task) {
                // ...
            }
        });

        taskadapter.setResults(results);

        todoListView.setAdapter(taskadapter);

    }

    public void addTaskList(View view) {
        todoAddTask = (EditText) findViewById(R.id.addTask);
        long date = new Date().getTime();

        realm.beginTransaction();
        Task task = realm.createObject(Task.class); // Create a new object
        task.setTask(todoAddTask.getText().toString());
        task.setCreated(date);
        task.setLastUpdated(date);
        realm.commitTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; thiafs adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
