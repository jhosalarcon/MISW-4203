package com.misw.vinilos_g24

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.misw.vinilos_g24.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        binding.navView.viewTreeObserver.addOnGlobalLayoutListener {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home,
                    R.id.nav_artistas,
                    R.id.nav_albumes,
                    R.id.nav_albumesDetailFragment,
                    R.id.nav_coleccionistas
                ), drawerLayout
            )
            setupActionBarWithNavController(navController!!, appBarConfiguration)
            navView.setupWithNavController(navController!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.main, menu)
        supportActionBar!!.title = "Vinilos"
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp(appBarConfiguration) ?: super.onSupportNavigateUp()
    }
}