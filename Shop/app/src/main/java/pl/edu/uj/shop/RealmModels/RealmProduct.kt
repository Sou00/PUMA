package pl.edu.uj.shop.RealmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmProduct : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var desc: String = ""
    var price: Double = 0.0
}