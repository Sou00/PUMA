package pl.edu.uj.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import io.realm.Realm
import pl.edu.uj.shop.Adapters.QuestionAdapter
import pl.edu.uj.shop.RealmModels.RealmQuestion

class FAQList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqlist)

        val listView = findViewById<ListView>(R.id.faqListView)
        val db = Realm.getDefaultInstance()
        val questionList = db.where(RealmQuestion::class.java).findAll().toList()
        val questionAdapter = QuestionAdapter(this, questionList)
        listView.adapter = questionAdapter

    }
}