package com.example.internapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internapp.DataClass.topHeadLines
import com.example.internapp.ui.main.SectionsPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.miguelcatalan.materialsearchview.MaterialSearchView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MainActivity : AppCompatActivity() {

    var drawer : NavigationView? = null




    override fun onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val item = menu!!.findItem(R.id.action_search)

        RetrofitClient.searchView = findViewById<com.miguelcatalan.materialsearchview.MaterialSearchView>(R.id.searchView)
        RetrofitClient.searchView!!.setMenuItem(item)

        RetrofitClient.searchView!!.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!=null && newText.isNotEmpty()){
                    RetrofitClient.retrofitService.getSearch(newText).enqueue(object : retrofit2.Callback<topHeadLines>{
                        override fun onFailure(call: Call<topHeadLines>, t: Throwable) {
                            Toast.makeText(this@MainActivity,"Error in getting news : ${t.message}",Toast.LENGTH_LONG).show()



                        }

                        override fun onResponse(
                            call: Call<topHeadLines>,
                            response: Response<topHeadLines>
                        ) {
                            RetrofitClient.articles!!.clear()
                            RetrofitClient.articles!!.addAll(response.body()!!.articles)
                            RetrofitClient.adapter?.notifyDataSetChanged()
                        }
                    })

                }
                if(newText!=null && newText.isEmpty()){
                    RetrofitClient.retrofitService.getTopNews().enqueue(object :
                        retrofit2.Callback<topHeadLines> {
                        override fun onFailure(call: Call<topHeadLines>, t: Throwable) {
                            Toast.makeText(this@MainActivity,"Error in getting news : ${t.message}",Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<topHeadLines>, response: Response<topHeadLines>) {
                            if(response.isSuccessful){
                                RetrofitClient.articles!!.clear()
                                RetrofitClient.articles!!.addAll(response.body()!!.articles)
                                RetrofitClient.adapter?.notifyDataSetChanged()
                            }
                        }
                    })
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
        })



        return true
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById<NavigationView>(R.id.nav_view)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name)
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)



//        val materialSeachBar = findViewById<com.mancj.materialsearchbar.MaterialSearchBar>(R.id.searchBar)
//        materialSeachBar.addTextChangeListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if(s!=null){
//                    if(s.isNotEmpty()){
//                    }
//                }
//            }
//        })


    }
}