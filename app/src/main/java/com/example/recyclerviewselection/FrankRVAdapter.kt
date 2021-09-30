package com.example.recyclerviewselectiontoast


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Our RV adapter
//
// The first line needs a bit of explanation from both Android and Kotlin perspectives... -fgreco
//

class FrankRVAdapter(private val frankData: ArrayList<String>) :    // Pass your data (and datatype) to your Adapter's constructor
    RecyclerView.Adapter<FrankRVAdapter.FrankHolder>() {            // Your adapter subclasses RecyclerView.Adapter
    // You have to tell this superclass what view holder datatype you are using (ie, FrankRVAdapter.FrankHolder).
    // And you have to specify the primary constructor used to initialize the superclass (note the '()')

    // Btw, frankData is the variable in this class that holds my data.
    // I decided to use an ArrayList (but I could have used any other data structure, eg, HashMap...
    //      but if I did, I would have to change all the ArrayList methods to HashMap methods in this class)
    //      Since the RecyclerView uses onCreateViewHolder(), onBindViewHolder(), getItemCount(), etc, it doesn't care
    //      what data structure we use internally.  This is the benefit of the adapter technique.

    var numVis: Int = 0                         // shows how many times onCreateViewHolder() is called
    private lateinit var mycontext: Context     // needed for the toast later

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrankRVAdapter.FrankHolder {
        mycontext = parent.context
        val view = LayoutInflater.from(mycontext).inflate(R.layout.recyclerview_listitem, parent, false)
        Log.i("Frank", "onCreateViewHolder() called... " + numVis++)
        return FrankHolder(view)
    }

    /*
     onBindViewHolder() - what to do when any offscreen information needs to put into the visible Views
     */
    override fun onBindViewHolder(holder: FrankHolder, position: Int) {
        val myitem = frankData.get(position)
        Log.i("Frank", "onBindViewHolder() called ------ myitem: [$myitem] : $position : $numVis" )

        holder.textView.setText(myitem)             // Replace the info in the holder

        // Reset the onClickListener() to a onscreen RV list item
        holder.textView.setOnClickListener {        // Show a Toast when item is selected
            Log.i("Frank", "onClick() - [$myitem]")
            Toast.makeText(mycontext, "You selected [$myitem]", Toast.LENGTH_LONG).show()   // this works fine
        }
    }

    override fun getItemCount(): Int {
        return frankData.size
    }

    /*
     FrankHolder() - my view holder.  It subclasses RecyclerView.ViewHolder and indicates the primary constructor to initialize that superclass
     *
     * Since this is a simple hello world, I'm just using a String in each list item of the RV
     */
    class FrankHolder(myView: View) : RecyclerView.ViewHolder(myView) {
        val textView : TextView = myView.findViewById(R.id.textView)
    }
}
