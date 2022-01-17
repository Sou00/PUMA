package pl.edu.uj.shop.RealmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmQuestion : RealmObject() {
    @PrimaryKey
    var id = 0
    var title: String = ""
    var response: String = ""
}