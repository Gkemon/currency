package com.gk.emon.currency

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.gk.emon.currency.databinding.ActivityMainBinding
import com.gk.emon.currency.local_db.AppDatabase
import com.gk.emon.currency.model.CurrenciesApiResponse
import com.gk.emon.currency.network.toMap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDatabase.getInstance(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            GlobalScope.launch {
                val response = service.getCurrencies(
                    "61d3eb0799864c1cb519933d81f1de12"
                )
                val result = CurrenciesApiResponse(
                    1,
                    JSONObject(
                        response.body().toString()
                    ).toMap() as Map<String, String>
                )
                AppDatabase.getInstance(this@MainActivity).getCurrencyDao().insertAllCurrency(
                    result
                )

                service.getLatestExchangeRates("61d3eb0799864c1cb519933d81f1de12").body()
                    ?.let { it ->
                        AppDatabase.getInstance(this@MainActivity).getLatestRateDao()
                            .insertAllLatestRates(
                                it
                            )
                    }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}