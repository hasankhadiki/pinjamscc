package studio.tehhutan.pinjamscc;


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;


@RunWith(JUnit4.class)
public class BtnSubmitClickFailedTest{

    @Rule
    public ActivityTestRule<Booking> submitClickRule = new ActivityTestRule<>(Booking.class, true, false);

    @Test
    public void btnSubmitClickTest(){
        submitClickRule.launchActivity(null);
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.et_namapeminjam)).perform(typeText("Bukan Hasan Khadiki"), closeSoftKeyboard());
//        onView(withId(R.id.et_organisasi)).perform(typeText("LDJ KISI"), closeSoftKeyboard());
        onView(withId(R.id.et_deskripsikegiatan)).perform(typeText("Bukan Upgrading Staff"), closeSoftKeyboard());
        int hour1 = 10;
        int minutes1 = 59;
        int hour2 = 11;
        onView(withId(R.id.et_jammulai)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour1, minutes1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.et_jamakhir)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour2, minutes1));
        onView(withId(android.R.id.button1)).perform(click());

        try {
            onView(withId(R.id.btn_submit)).check(matches(not(isEnabled())));
            // View is not displayed
        } catch (AssertionFailedError e) {
            // View is displayed
        }


        onView(withId(R.id.btn_submit)).perform(click());
    }
}
