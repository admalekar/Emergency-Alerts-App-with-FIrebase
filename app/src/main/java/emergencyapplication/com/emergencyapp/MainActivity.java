package emergencyapplication.com.emergencyapp;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference root, root1, root2, root3, root4;

    private RadioGroup rgroup;
    private RadioButton DtoA;
    private RadioButton EtoM;
    private RadioButton GtoC;
    private RadioButton MtoD;
    private RadioButton VtoB;

    private String latest_msg;
    private String station;
    private TextView Text_message;

    private Button send_alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = FirebaseDatabase.getInstance().getReference().getRoot().child("D_to_A");
        root1 = FirebaseDatabase.getInstance().getReference().getRoot().child("V_to_B");
        root3 = FirebaseDatabase.getInstance().getReference().getRoot().child("E_to_M");
        root2 = FirebaseDatabase.getInstance().getReference().getRoot().child("M_to_D");
        root4 = FirebaseDatabase.getInstance().getReference().getRoot().child("G_to_C");


        rgroup = findViewById(R.id.radioGroup);
        DtoA = findViewById(R.id.Channel_1);
        VtoB = findViewById(R.id.Channel_2);
        MtoD = findViewById(R.id.Channel_3);
        EtoM = findViewById(R.id.Channel_4);
        GtoC = findViewById(R.id.Channel_5);


        Text_message = findViewById(R.id.Scroll_text);
        send_alert = findViewById(R.id.Send_alert_button);


        send_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Send_alert_class.class);
                startActivity(intent);
            }
        });
    }



    public void onCheckBox1(View view) {

        Log.d("DtoA", "true");
        root.limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                show_child(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void onCheckBox2(View view) {

        root1.limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("checkbox2");
                show_child(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Log.d("cb2", "true2");
    }

    public void onCheckBox3(View view) {

        Log.d("cb3", "true3");
        root2.limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                show_child(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onCheckBox4(View view) {
        Log.d("cb4", "true4");
        root3.limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                show_child(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onCheckBox5(View view) {
        Log.d("cb5", "true5");
        root4.limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("--");
                show_child(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void show_child(DataSnapshot dataSnapshot) {
       // Text_message.setText("");
        int count;
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {
            latest_msg = (String) ((DataSnapshot) i.next()).getValue();
            station = (String) ((DataSnapshot) i.next()).getValue();
            Text_message.append(station + " :" + latest_msg + "\n\n");

        }
    }
}
