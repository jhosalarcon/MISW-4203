package com.misw.vinilos_g24;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

import static androidx.test.espresso.matcher.ViewMatchers.withText;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestAlbum {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() throws InterruptedException {
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Álbumes")).perform(click());
        onView(withText("pruebita")).check(matches(isDisplayed()));
    }
    @Test
    public void AlbumNoExiste() throws InterruptedException {
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Álbumes")).perform(click());
        onView(withText("Album falso")).check(doesNotExist());
    }
}
