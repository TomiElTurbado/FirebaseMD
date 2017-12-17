package com.example.juanma.firebasemasterdetail.dummy

import android.util.Log
import android.widget.Toast
import com.example.juanma.firebasemasterdetail.ItemDetailActivity
import com.example.juanma.firebasemasterdetail.ItemListActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import java.util.ArrayList
import java.util.HashMap
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    val database = FirebaseDatabase.getInstance()
    val reference = database.reference
    var listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot)
        {
            var i : Int = 0
            for(obj in snapshot.children)
            {
                var item = DummyItem("" + i++.toString(), "" + obj.children.elementAt(0).value.toString(), "" + obj.children.elementAt(1).value.toString());
                addItem(item);
            }
        }

        override fun onCancelled(p0: DatabaseError) {
            //Log.i("E", "Error en la base de datos: " + p0.message)
            Toast.makeText(null, p0.message, Toast.LENGTH_LONG).show()
        }
    }

    constructor() {
        reference.child("lenguajes").addValueEventListener(listener)
    }

    /*
    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }
    */

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    /*
    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }
    */

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val content: String, val details: String?) {
        override fun toString(): String = content
    }
}
