package com.vesperrun.tipsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vesperrun.tipsync.data.GigRepository
import com.vesperrun.tipsync.data.TipSyncDatabase
import com.vesperrun.tipsync.ui.TipSyncApp
import com.vesperrun.tipsync.ui.theme.TipSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = GigRepository(TipSyncDatabase.get(this).gigDao())
        setContent {
            TipSyncTheme {
                TipSyncApp(repository)
            }
        }
    }
}
