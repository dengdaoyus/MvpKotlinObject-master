/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ddy.android.architecture.blueprints.todoapp.tasks

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.NoActivityResumedException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions.open
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.contrib.DrawerMatchers.isOpen
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import com.ddy.android.architecture.blueprints.todoapp.TestUtils.getToolbarNavigationContentDescription
import com.ddy.android.architecture.blueprints.todoapp.custom.action.NavigationViewActions.navigateTo
import com.example.R
import com.example.tasks.TasksActivity
import junit.framework.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for the [DrawerLayout] layout component in [TasksActivity] which manages
 * navigation within the app.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class AppNavigationTest {

    /**
     * [ActivityTestRule] is a JUnit [@Rule][Rule] to launch your activity under test.

     *
     *
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule @JvmField var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test fun clickOnStatisticsNavigationItem_ShowsStatisticsScreen() {
        openStatisticsScreen()

        // Check that statistics Activity was opened.
        onView(withId(R.id.statistics)).check(matches(isDisplayed()))
    }

    @Test fun clickOnListNavigationItem_ShowsListScreen() {
        openStatisticsScreen()

        openTasksScreen()

        // Check that Tasks Activity was opened.
        onView(withId(R.id.tasksContainer)).check(matches(isDisplayed()))
    }

    @Test fun clickOnAndroidHomeIcon_OpensNavigation() {
        // Check that left drawer is closed at startup
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.START))) // Left Drawer should be closed.

        // Open Drawer
        onView(withContentDescription(getToolbarNavigationContentDescription(
                activityTestRule.activity, R.id.toolbar))).perform(click())

        // Check if drawer is open
        onView(withId(R.id.drawer_layout))
                .check(matches(isOpen(Gravity.START))) // Left drawer is open open.
    }

    @Test fun Statistics_backNavigatesToTasks() {
        openStatisticsScreen()

        // Press back to go back to the tasks list
        pressBack()

        // Check that Tasks Activity was restored.
        onView(withId(R.id.tasksContainer)).check(matches(isDisplayed()))
    }

    @Test fun backFromTasksScreen_ExitsApp() {
        // From the tasks screen, press back should exit the app.
        assertPressingBackExitsApp()
    }

    @Test fun backFromTasksScreenAfterStats_ExitsApp() {
        // This test checks that TasksActivity is a parent of StatisticsActivity

        // Open the stats screen
        openStatisticsScreen()

        // Open the tasks screen to restore the task
        openTasksScreen()

        // Pressing back should exit app
        assertPressingBackExitsApp()
    }

    private fun assertPressingBackExitsApp() {
        try {
            pressBack()
            fail("Should kill the app and throw an exception")
        } catch (e: NoActivityResumedException) {
            // Test OK
        }

    }

    private fun openTasksScreen() {
        // Open Drawer to click on navigation item.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.START))) // Left Drawer should be closed.
                .perform(open()) // Open Drawer

        // Start tasks list screen.
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.list_navigation_menu_item))
    }

    private fun openStatisticsScreen() {
        // Open Drawer to click on navigation item.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.START))) // Left Drawer should be closed.
                .perform(open()) // Open Drawer

        // Start statistics screen.
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.statistics_navigation_menu_item))
    }
}
