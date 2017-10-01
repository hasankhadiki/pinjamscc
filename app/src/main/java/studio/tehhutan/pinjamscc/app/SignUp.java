package studio.tehhutan.pinjamscc.app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import studio.tehhutan.pinjamscc.R;
import studio.tehhutan.pinjamscc.model.User;

public class SignUp extends AppCompatActivity {


    MaterialEditText editNrp, editEmail, editNama, editPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editNrp = (MaterialEditText)findViewById(R.id.et_nrp);
        editEmail = (MaterialEditText)findViewById(R.id.et_email);
        editNama = (MaterialEditText)findViewById(R.id.et_nama);
        editPassword = (MaterialEditText)findViewById(R.id.et_password);
        btnSignUp = (Button)findViewById(R.id.btn_signup);

        //Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Tunggu sebentar..");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                    //Cek apakah NRP sudah ada didalam database
                        if(dataSnapshot.child(editNrp.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "NRP tersebut sudah ada di dalam database", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mDialog.dismiss();
                            User user = new User(editEmail.getText().toString(), editNama.getText().toString(), editPassword.getText().toString());
                            table_user.child(editNrp.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Pendaftaran akun baru berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
