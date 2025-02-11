package com.tricakrawala.batikpedia.presentation.ui.screen.main

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.tricakrawala.batikpedia.presentation.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun navHost_clickItemProvinsioToDetail() {
        composeTestRule.onNodeWithTag("dataNusantara")
            .assertExists("dataNusantara tidak ditemukan di UI")
        composeTestRule.onNodeWithTag("dataNusantara")
            .performScrollToIndex(3)
            .performClick()
    }
}