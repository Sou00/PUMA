package pl.edu.uj.shop.RealmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmContactInfo : RealmObject() {
    @PrimaryKey
    var id = 0
    var name: String = ""
    var number: String = ""
    var address: String = ""
}