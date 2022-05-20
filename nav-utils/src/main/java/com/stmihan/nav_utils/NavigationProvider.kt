package com.stmihan.nav_utils

interface NavigationProvider {
    fun launch(navCommand: NavCommand)
}