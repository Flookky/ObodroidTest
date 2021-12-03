package com.example.obodroid.Control

import android.view.View

class Control {
    companion object{
        //Screen
        var screen_width = 0
        var screen_height = 0

        var year_sel = 0
        var month_sel = 0

        const val flags = (
               View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}