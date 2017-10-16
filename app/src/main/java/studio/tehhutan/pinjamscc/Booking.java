package studio.tehhutan.pinjamscc;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import studio.tehhutan.pinjamscc.Interface.ItemClickListener;
import studio.tehhutan.pinjamscc.ViewHolder.MenuViewHolder;
import studio.tehhutan.pinjamscc.model.BookingList;


public class Booking extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference bookinglist;

    RecyclerView recyclerBookingList;
    RecyclerView.LayoutManager layoutManager;

    BookingList newBooking;

    EditText editNamaPeminjam, editOrganisasi, editKegiatan, editJamMulai, editJamAkhir;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Booking List");
        setSupportActionBar(toolbar);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        bookinglist = database.getReference("BookingList");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Booking.this);
                View mView = getLayoutInflater().inflate(R.layout.add_booking, null);
                editNamaPeminjam = (EditText) mView.findViewById(R.id.et_namapeminjam);
                editOrganisasi = (EditText) mView.findViewById(R.id.et_organisasi);
                editKegiatan = (EditText) mView.findViewById(R.id.et_deskripsikegiatan);
                editJamMulai = (EditText) mView.findViewById(R.id.et_jammulai);
                editJamAkhir = (EditText) mView.findViewById(R.id.et_jamakhir);
                btnSubmit = (Button) mView.findViewById(R.id.btn_submit);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();


                editJamMulai.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final Calendar c = Calendar.getInstance();
                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this,

                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        editJamMulai.setText(hourOfDay + ":" + minute);
                                    }
                                }, hour, minute, false);
                        timePickerDialog.show();

                    }
                });

                editJamAkhir.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final Calendar c = Calendar.getInstance();
                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        editJamAkhir.setText(hourOfDay + ":" + minute);
                                    }
                                }, hour, minute, false);
                        timePickerDialog.show();
                    }
                });

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        BookingList newBooking = new BookingList(editNamaPeminjam.getText().toString()
                                , editOrganisasi.getText().toString()
                                , editKegiatan.getText().toString()
                                , editJamMulai.getText().toString()
                                , editJamAkhir.getText().toString()
                        );
                        bookinglist.push().setValue(newBooking);
                        dialog.dismiss();
                    }
                });
            }


        });

        //Load Booking List
        recyclerBookingList = (RecyclerView) findViewById(R.id.recycler_booking);
        recyclerBookingList.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recyclerBookingList.setLayoutManager(layoutManager);
        loadBookingList();

    }

    public void loadBookingList() {
        FirebaseRecyclerAdapter<BookingList, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<BookingList, MenuViewHolder>(BookingList.class, R.layout.activity_booking_fragment, MenuViewHolder.class, bookinglist) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, BookingList model, int position) {
                viewHolder.txtNama.setText(model.getNama());
                viewHolder.txtOrganisasi.setText(model.getDepartemen());
                viewHolder.txtKegiatan.setText(model.getKegiatan());
                viewHolder.txtJamMulai.setText(model.getJamMulai());
                viewHolder.txtJamAkhir.setText(model.getJamAkhir());

                final BookingList clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onCLick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Booking.this, "" + clickItem.getNama(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerBookingList.setAdapter(adapter);
    }
}