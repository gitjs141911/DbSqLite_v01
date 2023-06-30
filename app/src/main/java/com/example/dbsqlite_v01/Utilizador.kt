package com.example.dbsqlite_v01

class Utilizador (val id: Int =0,val nome: String ="", val fone: String="") {

    override fun toString(): String {
        return "id=$id, nome=$nome, fone=$fone"
    }
}