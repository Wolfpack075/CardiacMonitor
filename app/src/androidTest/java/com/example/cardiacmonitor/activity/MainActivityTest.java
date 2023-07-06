package com.example.cardiacmonitor.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.example.cardiacmonitor.MainActivity;
import com.example.cardiacmonitor.R;
import com.example.cardiacmonitor.activity.EFVIewAction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddButton() {
        onView(ViewMatchers.withId(R.id.add_button)).perform(click());
        onView(withId(R.id.dateValue)).perform(ViewActions.typeText("12/10/2021"));
        onView(withId(R.id.timeValue)).perform(ViewActions.typeText("10:19"));
        onView(withId(R.id.systolicValue)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.diastolicValue)).perform(ViewActions.typeText("90"));
        pressBack();
        onView(withId(R.id.heartRateValue)).perform(ViewActions.typeText("80"));
        pressBack();
        onView(withId(R.id.commentValue)).perform(ViewActions.typeText("UI test data insert"));
        pressBack();
        onView(withId(R.id.addButton)).perform(click());
        onView(ViewMatchers.withId(R.id.recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        onView(ViewMatchers.withId(R.id.recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.editButton)).perform(click());

        onView(withId(R.id.dateValue2)).perform(ViewActions.clearText());
        onView(withId(R.id.dateValue2)).perform(ViewActions.typeText("11/10/2021"));
        onView(withId(R.id.timeValue2)).perform(ViewActions.clearText());
        onView(withId(R.id.timeValue2)).perform(ViewActions.typeText("5.30"));
        pressBack();
        onView(withId(R.id.updateButton)).perform(click());
    }

    @Test
    public void deleteTestButton() {
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.dateValue)).perform(ViewActions.typeText("12/10/2021"));
        onView(withId(R.id.timeValue)).perform(ViewActions.typeText("10:19"));
        onView(withId(R.id.systolicValue)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.diastolicValue)).perform(ViewActions.typeText("90"));
        pressBack();
        onView(withId(R.id.heartRateValue)).perform(ViewActions.typeText("80"));
        pressBack();
        onView(withId(R.id.commentValue)).perform(ViewActions.typeText("UI test data insert"));
        pressBack();
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.recycleview)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, EFVIewAction.clickChildViewWithId(R.id.deleteButton)));


    }


}
