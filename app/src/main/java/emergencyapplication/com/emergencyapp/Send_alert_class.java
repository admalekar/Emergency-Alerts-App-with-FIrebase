package emergencyapplication.com.emergencyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amey on 14-Nov-17.
 */

public class Send_alert_class extends AppCompatActivity {


    private DatabaseReference rootAlert;
    private Button send_button_2;

    private String tempKey;
    private EditText Text_to_send;
    private Spinner station_spinner;
    private Spinner emergency_service_spinner;
    public ArrayList<String> stations;
    public ArrayList<String> emergency_spinner;

    private String selected_channel;
    private String sendText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_alert_layout);
        station_spinner = findViewById(R.id.station_spinner);
        emergency_service_spinner = findViewById(R.id.emer_spinner);

        send_button_2 = findViewById(R.id.Send_button_2);
        Text_to_send = findViewById(R.id.Send_text);

        stations = new ArrayList<>();
        emergency_spinner = new ArrayList<>();
        emergency_spinner.add("Fire Station");
        emergency_spinner.add("Ambulance");
        emergency_spinner.add("Police Station");

        stations.add("Dahisar");
        stations.add("Borivali");
        stations.add("Kandivili");
        stations.add("Malad");
        stations.add("Goregaon");
        stations.add("Jogeshwari");
        stations.add("Andheri");
        stations.add("Vile Parle");
        stations.add("Santa Cruz");
        stations.add("Khar Road");
        stations.add("Bandra");
        stations.add("Mahim");
        stations.add("Matunga");
        stations.add("Dadar");
        stations.add("Elphinstone Road");
        stations.add("Lower Parel");
        stations.add("Maha Laxmi");
        stations.add("Mumbai Central");
        stations.add("Grant Road");
        stations.add("Charni Road");
        stations.add("Marine Lines");
        stations.add("Churchgate ");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stations);
        station_spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emergency_spinner);
        emergency_service_spinner.setAdapter(adapter2);


        station_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (station_spinner.getSelectedItem().equals("Dahisar") || station_spinner.getSelectedItem().equals("Borivali") || station_spinner.getSelectedItem().equals("Kandivali") || station_spinner.getSelectedItem().equals("Malad") || station_spinner.getSelectedItem().equals("Goregaon") || station_spinner.getSelectedItem().equals("Jogeshwari") || station_spinner.getSelectedItem().equals("Andheri")) {
                    selected_channel = "D_to_A";
                    Log.d("hsdfn", selected_channel);
                    rootAlert = FirebaseDatabase.getInstance().getReference().child(selected_channel);
                } else if (station_spinner.getSelectedItem().equals("Vile Parle") || station_spinner.getSelectedItem().equals("Santa Cruz") || station_spinner.getSelectedItem().equals("Khar Road") || station_spinner.getSelectedItem().equals("Bandra")) {
                    selected_channel = "V_to_B";
                    Log.d("hsdfn", selected_channel);
                    rootAlert = FirebaseDatabase.getInstance().getReference().child(selected_channel);
                } else if (station_spinner.getSelectedItem().equals("Mahim") || station_spinner.getSelectedItem().equals("Matunga") || station_spinner.getSelectedItem().equals("Dadar")) {
                    selected_channel = "M_to_D";
                    Log.d("hsdfn", selected_channel);
                    rootAlert = FirebaseDatabase.getInstance().getReference().child(selected_channel);
                } else if (station_spinner.getSelectedItem().equals("Elphinstone Road") || station_spinner.getSelectedItem().equals("Lower Parel") || station_spinner.getSelectedItem().equals("Maha Laxmi") || station_spinner.getSelectedItem().equals("Mumbai Central")) {
                    selected_channel = "E_to_M";
                    Log.d("hsdfn", selected_channel);
                    rootAlert = FirebaseDatabase.getInstance().getReference().child(selected_channel);
                } else

                    selected_channel = "G_to_C";
                Log.d("hsdfn", selected_channel);
                rootAlert = FirebaseDatabase.getInstance().getReference().child(selected_channel);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        send_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();

                tempKey = rootAlert.push().getKey();
                rootAlert.updateChildren(map);

                DatabaseReference message_root = rootAlert.child(tempKey);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", station_spinner.getSelectedItem());
                map2.put("msg", Text_to_send.getText().toString());


                message_root.updateChildren(map2);

                sendText = "Alert is created and " + emergency_service_spinner.getSelectedItem() + " is called to " + station_spinner.getSelectedItem();

                Toast.makeText(getApplicationContext(), sendText, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
