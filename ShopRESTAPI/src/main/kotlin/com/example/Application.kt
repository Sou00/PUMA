package com.example

import com.example.Models.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


fun main() {
    Database.connect("jdbc:sqlite:database.db", "org.sqlite.JDBC")

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Products)
        SchemaUtils.create(Users)
        SchemaUtils.create(Orders)
        SchemaUtils.create(Questions)
        SchemaUtils.create(ContactInfos)
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}

object Products : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("Name",255)
    val desc: Column<String> = text("Description")
    val price: Column<Double> = double("Price")

    override val primaryKey = PrimaryKey(id,name = "PK_PRODUCTS_ID")

    fun toProduct(row: ResultRow): Product =
        Product(
            id = row[id],
            name = row[name],
            desc = row[desc],
            price = row[price]
        )
}


object Orders : Table(){
    val id: Column<Int> = integer("id")
    val userId: Column<Int> = integer("userId").references(Users.id)
    val productId: Column<Int> = integer("productId").references(Products.id)
    val quantity: Column<Int> = integer("Quantity")

    override val primaryKey = PrimaryKey(id,userId,productId,name = "PK_orderS_ID")

    fun toOrder(row: ResultRow): Order =
        Order(
            id = row[id],
            userId = row[userId],
            productId = row[productId],
            quantity = row[quantity]
        )
}




object Users : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("Name",255)
    val age: Column<Int> = integer("Age")

    override val primaryKey = PrimaryKey(id,name = "PK_USERS_ID")

    fun toUser(row: ResultRow): User =
        User(
            id = row[id],
            name = row[name],
            age = row[age]
        )
}



object ContactInfos : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("Name",255)
    val number: Column<String> = varchar("Number",255)
    val address: Column<String> = varchar("Address",255)

    override val primaryKey = PrimaryKey(id,name = "PK_CONTACTINFOS_ID")

    fun toContactInfo(row: ResultRow): ContactInfo =
        ContactInfo(
            id = row[id],
            name = row[name],
            number = row[number],
            address = row[address]
        )

}



object Questions : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val title: Column<String> = varchar("Title",255)
    val response: Column<String> = text("Response")

    override val primaryKey = PrimaryKey(id,name = "PK_QUESTIONS_ID")

    fun toQuestion(row: ResultRow): Question =
        Question(
            id = row[id],
            title = row[title],
            response = row[response]
        )

}


