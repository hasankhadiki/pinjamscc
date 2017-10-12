package studio.tehhutan.pinjamscc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import studio.tehhutan.pinjamscc.Interface.ItemClickListener;
import studio.tehhutan.pinjamscc.ViewHolder.MenuViewHolder;
import studio.tehhutan.pinjamscc.model.BookingList;


public class Booking extends AppCompatActivity{

    FirebaseDatabase database;
    DatabaseReference bookinglist;

    RecyclerView recyclerBookingList;
    RecyclerView.LayoutManager layoutManager;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Load Booking List
        recyclerBookingList = (RecyclerView)findViewById(R.id.recycler_booking);
        recyclerBookingList.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recyclerBookingList.setLayoutManager(layoutManager);

        loadBookingList();
    }

    public void loadBookingList(){
        FirebaseRecyclerAdapter<BookingList, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<BookingList, MenuViewHolder>(BookingList.class, R.layout.activity_booking_fragment, MenuViewHolder.class, bookinglist) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, BookingList model, int position) {
                viewHolder.txtNama.setText(model.getNama());
                viewHolder.txtOrganisasi.setText(model.getDepartemen());
                viewHolder.txtKegiatan.setText(model.getKegiatan());

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.booking, menu);
        return true;
    }

}
