package com.samst.tourguide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {

    var map_sites = arrayOf("Comice", "Hollybelly 5", "Shabour", "Septime" ,
        "Du Pain et des Idées", "HEBE", "Louvre Museum", "Seine River Cruises",
        "Luxembourg Gardens", "Cathédrale Notre-Dame de Paris", "Disneyland Paris",
        "Eiffel Tower")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapBar = supportActionBar
        mapBar!!.title = "Maps of Locations Listed"

        val adapter = ArrayAdapter(this,
            R.layout.listview_item, map_sites)
        val listView: ListView = findViewById(R.id.listview_3)
        listView.setAdapter(adapter)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemId = parent.getItemIdAtPosition(position).toInt()

            when(itemId) {
                0 -> newMap("48.8494","2.2759")
                1 -> newMap("48.8710", "2.3598")
                2 -> newMap("48.8659", "2.3491")
                3 -> newMap("48.8536", "2.3807")
                4 -> newMap("48.864716", "2.349014")
                5 -> newMap("48.8508","2.3492")
                6 -> newMap("48.8606", "2.3376")
                7 -> newMap("49.4342", "0.1172")
                8 -> newMap("48.8462", "2.3372")
                9 -> newMap("48.8530", "2.3499")
                10 -> newMap("48.8672", "2.7838")
                11 -> newMapStreet("48.8584","2.2945")

                else -> Toast.makeText(this, "Cannot display selection", Toast.LENGTH_LONG).show()
            }

        }


    } // main on create

    //launches google maps and gets coordinates.  Zoomed 65%
    fun newMap (lat: String, lng: String) {
        val navigationIntentUri = Uri.parse("geo: " + lat + "," + lng + "?z=65")
        val intent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)

    }
    fun newMapStreet (lat: String, lng: String) {
        val navIntentUri = Uri.parse("google.streetview:cbll=48.8672,2.7838")
        val intent = Intent(Intent.ACTION_VIEW, navIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)

    }


} // class
