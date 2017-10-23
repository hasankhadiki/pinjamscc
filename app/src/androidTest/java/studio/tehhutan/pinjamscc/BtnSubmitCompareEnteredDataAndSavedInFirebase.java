package studio.tehhutan.pinjamscc;

import studio.tehhutan.pinjamscc.model.BookingList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by tehhutan on 23/10/17.
 */

public class BtnSubmitCompareEnteredDataAndSavedInFirebase {

    FirebaseDatabase database;
    DatabaseReference bookinglist;
    public String nama, departemen, kegiatan, jamMulai, jamAkhir;



    @Rule
    public ActivityTestRule<Booking> submitClickRule = new ActivityTestRule<>(Booking.class, true, false);

    @Test
    public void btnSubmit() {
        submitClickRule.launchActivity(null);
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.et_namapeminjam)).perform(typeText("Miranda"), closeSoftKeyboard());
        onView(withId(R.id.et_organisasi)).perform(typeText("LDJ Mozaik"), closeSoftKeyboard());
        onView(withId(R.id.et_deskripsikegiatan)).perform(typeText("UX Design Workshop"), closeSoftKeyboard());
        int hour1 = 7;
        int minutes1 = 15;
        int hour2 = 10;
        onView(withId(R.id.et_jammulai)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour1, minutes1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.et_jamakhir)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour2, minutes1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_submit)).perform(click());


        //Init Firebase
        database = FirebaseDatabase.getInstance();
        bookinglist = database.getReference("BookingList");

        bookinglist.limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                BookingList newPost = dataSnapshot.getValue(BookingList.class);

                nama = newPost.getNama();
                departemen = newPost.getDepartemen();
                kegiatan = newPost.getKegiatan();
                jamMulai= newPost.getJamMulai();
                jamAkhir = newPost.getJamAkhir();

                System.out.println("Fetched From Firebase");
                System.out.println(nama);
                System.out.println(departemen);
                System.out.println(kegiatan);
                System.out.println(jamMulai);
                System.out.println(jamAkhir);

            }



            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
    }

    String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(EditText.class);
            }

            @Override
            public String getDescription() {
                return "getting text from an EditText";
            }

            @Override
            public void perform(UiController uiController, View view) {
                EditText et = (EditText) view; //Save, because of check in getConstraints()
                stringHolder[0] = et.getText().toString();
            }
        });
        return stringHolder[0];
    }
}
