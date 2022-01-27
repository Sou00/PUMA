package pl.edu.uj.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import io.realm.Realm
import pl.edu.uj.shop.Adapters.ContactInfoAdapter
import pl.edu.uj.shop.RealmModels.RealmContactInfo

class ContactList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        val listView = findViewById<ListView>(R.id.contactListView)
        val db = Realm.getDefaultInstance()
        val contactList = db.where(RealmContactInfo::class.java).findAll().toList()
        val contactAdapter = ContactInfoAdapter(this, contactList)
        listView.adapter = contactAdapter
    }
}