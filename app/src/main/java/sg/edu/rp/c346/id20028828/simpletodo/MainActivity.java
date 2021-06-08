package sg.edu.rp.c346.id20028828.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddRemove;
    EditText etTask;
    Button btnAdd, btnRemove, btnClear;
    ListView lvTask;

//    ArrayList<String> alTask;
//    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinner);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnRemove = findViewById(R.id.buttonRemoveTask);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);

        ArrayList<String> alTask = new ArrayList<String>();
        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(aaTask);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        etTask.setHint(R.string.type_in_a_new_task_here);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        etTask.setHint(R.string.remove);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etTask.getText().toString());
                if(alTask.size()==0){
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                }
                else if(alTask.size()<=pos){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
                else{
                    alTask.remove(pos);
                    aaTask.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Task removed successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTask.size()==0){
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                }
                else{
                    alTask.clear();
                    aaTask.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Task cleared successfully", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}