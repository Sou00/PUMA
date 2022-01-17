package pl.edu.uj.shop.RealmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmUser : RealmObject() {
    @PrimaryKey
    var id = 0
    var name: String = ""
    var age: Int = 0
}