package com.example.plugins

import com.example.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {

    routing {
        get {
            call.respondText("Welcome to the main page")
        }
        route("/product") {
            get{

                    val products = transaction {
                        Products.selectAll().map { Products.toProduct(it) }
                    }
                    call.respond(products)
            }
            get("{id}") {
                val id = call.parameters["id"]!!.toInt()

                val product = transaction {
                    Products.select{Products.id eq id }.first()
                }
                call.respond(product)
            }
            post {
                val product = call.receive<Product>()
                transaction {
                    Products.insert {
                        it[name] = product.name
                        it[desc] = product.desc
                    }
                }
                call.respondText("Product stored correctly", status = HttpStatusCode.Created)
            }
            delete("{id}") {
                val id = call.parameters["id"]!!.toInt()

                transaction {
                    Products.deleteWhere { Products.id eq id }
                }
            }
        }
        route("/order"){
            get{
                val orders = transaction {
                    Orders.selectAll().map { Orders.toOrder(it) }
                }
                call.respond(orders)
            }
            get("{id}") {
                val id = call.parameters["id"]!!.toInt()

                val order = transaction {
                    Orders.select{Orders.id eq id }.map { Orders.toOrder(it) }
                }
                call.respond(order)
            }
            post {
                val order = call.receive<Order>()
                transaction {
                    Orders.insert {
                        it[id] =  order.id
                        it[userId] = order.userId
                        it[productId] = order.productId
                        it[quantity] = order.quantity
                    }
                }
                call.respondText("Order stored correctly", status = HttpStatusCode.Created)
            }
            delete("{id}") {
                val id = call.parameters["id"]!!.toInt()

                transaction {
                    Orders.deleteWhere { Orders.id eq id }
                }
            }
        }
        route("/user"){
            get{
                val users = transaction {
                    Users.selectAll().map { Users.toUser(it) }
                }
                call.respond(users)
            }
            get("{id}") {
                val id = call.parameters["id"]!!.toInt()

                val user = transaction {
                    Users.select{Users.id eq id }.first()
                }
                call.respond(user)
            }
            post {
                val user = call.receive<User>()
                transaction {
                    Users.insert {
                        it[name] = user.name
                        it[age] = user.age
                    }
                }
                call.respondText("User stored correctly", status = HttpStatusCode.Created)
            }
            delete("{id}") {
                val id = call.parameters["id"]!!.toInt()

                transaction {
                    Users.deleteWhere { Users.id eq id }
                }
            }
        }
        route("/contact"){
            get{
                val contacts = transaction {
                    ContactInfos.selectAll().map { ContactInfos.toContactInfo(it) }
                }
                call.respond(contacts)
            }
            get("{id}") {
                val id = call.parameters["id"]!!.toInt()

                val contact = transaction {
                    ContactInfos.select{ContactInfos.id eq id }.first()
                }
                call.respond(contact)
            }
            post {
                val contact = call.receive<ContactInfo>()
                transaction {
                    ContactInfos.insert {
                        it[name] = contact.name
                        it[number] = contact.number
                        it[address] = contact.address
                    }
                }
                call.respondText("Contact stored correctly", status = HttpStatusCode.Created)
            }
            delete("{id}") {
                val id = call.parameters["id"]!!.toInt()

                transaction {
                    ContactInfos.deleteWhere { ContactInfos.id eq id }
                }
            }
        }
        route("/faq"){
            get{
                val questions = transaction {
                    Questions.selectAll().map { Questions.toQuestion(it) }
                }
                call.respond(questions)
            }
            get("{id}") {
                val id = call.parameters["id"]!!.toInt()

                val question = transaction {
                    Questions.select{Questions.id eq id }.first()
                }
                call.respond(question)
            }
            post {
                val question = call.receive<Question>()
                transaction {
                    Questions.insert {
                        it[title] = question.title
                        it[response] = question.response
                    }
                }
                call.respondText("Question stored correctly", status = HttpStatusCode.Created)
            }
            delete("{id}") {
                val id = call.parameters["id"]!!.toInt()

                transaction {
                    Questions.deleteWhere { Questions.id eq id }
                }
            }
        }
    }
}
