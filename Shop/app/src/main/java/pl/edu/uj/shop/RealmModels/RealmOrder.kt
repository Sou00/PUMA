package pl.edu.uj.shop.RealmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmOrder : RealmObject() {
    var id = 0
    var userId: Int = 0
    @PrimaryKey
    var productId: Int = 0
    var quantity: Int = 0
}