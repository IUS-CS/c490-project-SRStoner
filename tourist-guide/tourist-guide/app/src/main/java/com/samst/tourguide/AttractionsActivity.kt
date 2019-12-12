package com.samst.tourguide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AttractionsActivity : AppCompatActivity() {

    var attraction_names = arrayOf("Louvre Museuem - " + " Worlds largest art museum",
        "Seine River Cruises - Great view right around Eiffel Tower",
        "Luxembourg Gardens - Beautiful and free of charge ",
        "Cathédrale Notre-Dame de Paris - Currently not open to public",
        "Disneyland Paris - For the kiddos", "Eiffel Tower - Iconic monument", "More Options?")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attractions)

        val attractionBar = supportActionBar
        attractionBar!!.title = "Popular Attractions Paris"

        val adapter = ArrayAdapter(this,
            R.layout.listview_item, attraction_names)
        val listView: ListView = findViewById(R.id.listview_2)
        listView.setAdapter(adapter)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemId = parent.getItemIdAtPosition(position).toInt()

                when(itemId) {
                    0 -> openWebPage("https://www.louvre.fr/en")
                    1 -> openWebPage("https://www.youtube.com/watch?v=ZS43KkzLIYk")
                    2 -> openWebPage("https://www.shutterstock.com/search/luxembourg-gardens")
                    3 -> openWebPage("https://www.notredamedeparis.fr/en/")
                    4 -> openWebPage("https://www.disneylandparis.com/en-us/")
                    5 -> openWebPage("https://www.toureiffel.paris/en")
                    6 -> moreAttractions("48.864716","2.349014")

                    else -> Toast.makeText(this, "Cannot display selection", Toast.LENGTH_LONG).show()
            }
        }

    } // main on create

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
    fun moreAttractions (lat: String, lng: String) {
        val navigationIntentUri = Uri.parse("geo: 48.864716,2.349014?q=Attractions")
        //Based off the coordinated given, I search restaurants within a small radius
        val intent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)

    }

} // class
