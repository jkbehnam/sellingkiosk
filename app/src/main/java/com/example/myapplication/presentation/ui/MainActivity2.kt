package com.example.myapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.domain.FooodRepositpry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class MainActivity2 : BaseActivity() {
    var fooodRepositpry: FooodRepositpry? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        val appComponent: AppComponent
//        appComponent = DaggerAppComponent.builder()
//            .build()
//        appComponent.inject(this)

        val x: Flow<List<FoodModel>>? = fooodRepositpry?.getAllFoods()
        if (x != null) {
            runBlocking(Dispatchers.IO) {
                Log.i("beeehnnnamm", x.first().toString())

            }
        }
      /*  window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LOW_PROFILE)
*/
    }
}