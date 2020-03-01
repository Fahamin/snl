package saturday.live.snl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseSetup extends AppCompatActivity {
    EditText titleTxt, linkTxt, lengthTxt;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("video");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_setup);

        initView();
    }

    private void saveData() {
        TubeDataModel fmodelClass = new TubeDataModel();

        String title, link, length;
        title = titleTxt.getText().toString().trim();
        link = linkTxt.getText().toString().trim();
        length = lengthTxt.getText().toString().trim();

        if (title.isEmpty()) {
            titleTxt.setError("pls enter titile");
            titleTxt.requestFocus();
            return;
        }
        if (link.isEmpty()) {
            linkTxt.setError("empty");
            linkTxt.requestFocus();
            return;
        }
        if (length.isEmpty()) {
            lengthTxt.setError("empty");
            lengthTxt.requestFocus();
            return;
        }

        String key_id = reference.push().getKey();
        int i = 0;

        TubeDataModel modelData = new TubeDataModel(i, title, link, length);
        reference.child(key_id).setValue(modelData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FirebaseSetup.this, "save Data", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(FirebaseSetup.this, "Error save Data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        titleTxt = findViewById(R.id.titleEtdID);
        linkTxt = findViewById(R.id.lengthEtdID);
        lengthTxt = findViewById(R.id.lengthEtdID);
    }


    public void viewData(View view) {
        // startActivity(new Intent(this, MainActivity.class));
    }

    public void save(View view) {
        saveData();
    }
}
