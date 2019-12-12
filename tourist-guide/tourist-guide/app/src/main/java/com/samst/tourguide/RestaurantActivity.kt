package com.samst.tourguide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RestaurantActivity : AppCompatActivity() {

    var restaurant_names = arrayOf("Comice",
        "Hollybelly 5","Shabour",
        "Septum" ,"Du Pain et des Idées",
        "HEBE", "More Options?")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val wikiBar = supportActionBar
        wikiBar!!.title = "Popular Restaurants Paris"

        val adapter = ArrayAdapter(this,
            R.layout.listview_item, restaurant_names)
        val listView:ListView = findViewById(R.id.listview_1)
        listView.setAdapter(adapter)
        
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemId = parent.getItemIdAtPosition(position).toInt()

            when(itemId) {
                0 -> openWebPage("https://comice.paris/")
                1 -> openWebPage("https://holybellycafe.com/holybelly-5/")
                2 -> openWebPage("https://www.restaurantshabour.com/en/")
                3 -> openWebPage("https://www.tripadvisor.com/Restaurant_Review-g187147-d2185868-Reviews-Septime-Paris_Ile_de_France.html")
                4 -> openWebPage("http://dupainetdesidees.com/")
                5 -> openWebPage("https://www.heberestaurant.com/en/#menu")
                6 -> moreRes("48.864716","2.349014")
                else -> Toast.makeText(this, "Out Of Luck", Toast.LENGTH_LONG).show()
            }//Views being populated

        }//onItemClickListener

    } //onCreate


    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }//openWeb
    fun moreRes (lat: String, lng: String) {
        val navigationIntentUri = Uri.parse("geo: 48.864716,2.349014?q=Restaurants")
        //Querying for restaurants at a city level in stead of nearby
        val intent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
        //All google map intents called as Action_view
        intent.setPackage("com.google.android.apps.maps")
        //setting the packageto make sure google maps is what handles the intent
        startActivity(intent)

    }//moreOptions!

} // class
