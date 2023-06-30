package com.example.dbsqlite_v01

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                AGE_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?,  oldVersion: Int, newVersion: Int) {
        // this method is to check if table already exists
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(name : String, age : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, name)
        values.put(AGE_COL, age)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {
        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase
        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "GEEKS_FOR_GEEKS"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "gfg_table"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COl = "nome"

        // below is the variable for age column
        val AGE_COL = "fone"
    }


    //-------------------------------------------------------------------
    fun utilizadorListSelectAll(): ArrayList<Utilizador>{
        val db= this.readableDatabase
        val cursor= db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
        val listaUtilizador: ArrayList<Utilizador> = ArrayList()
        if(cursor.count > 0){
            cursor.moveToFirst()
            do{
                val idIndex= cursor.getColumnIndex("id")
                val nomeIndex= cursor.getColumnIndex("nome")
                val foneIndex= cursor.getColumnIndex("fone")

                val id=cursor.getInt(idIndex)
                val nome=cursor.getString(nomeIndex)
                val fone=cursor.getString(foneIndex)

                listaUtilizador.add(Utilizador(id,nome,fone))
            }while (cursor.moveToNext())
        }
        db.close()
        return listaUtilizador
    }

}




























/*
val DATABASE_NAME="help.db"
val TABLE_NAME="user"
val COL_NAME="nome"
val COL_FONE="fone"
val COL_ID="id"

//class DBHelper(context: Context) : SQLiteOpenHelper(context, "database.db", null, 1) {
    class DBHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

   /*
    val sql = arrayOf(
        "CREATE TABLE USUARIOS(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOME TEXT, FONE TEXT)",
        "INSERT INTO USUARIOS(nome,fone) VALUES ('sergio','9999')"
    )
    */

    override fun onCreate(db: SQLiteDatabase?) {
     val createTable = ("CREATE TABLE" + TABLE_NAME + " ("
             + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COL_NAME + " TEXT," + COL_FONE + "TEXT" +")")
      db?.execSQL(createTable)
    /*
     sql.forEach {
            db.execSQL(it)
        }

     */

        /*
        db.execSQL("CREATE TABLE USUARIOS(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOME TEXT, FONE TEXT)")
        db.execSQL("INSERT INTO USUARIOS(nome,fone) VALUES ('sergio','9999')")
        db.execSQL("INSERT INTO USUARIOS(nome,fone) VALUES ('jk','8888')")
        */
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS USUARIOS")
        onCreate(db)
    }

    fun insertData(user:User){
        val db= this.writableDatabase
        val cv= ContentValues()

        cv.put(COL_NAME,user.nome)
        cv.put(COL_FONE,user.fone)
        var result=db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong()){
            Toast.makeText(context,"ERRO CADASTRO", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"CADASTRO OK", Toast.LENGTH_SHORT).show()
        }


    }

    fun utilizadorSelectAll() : Cursor {
        val db= this.readableDatabase
        val cursor= db.rawQuery("SELECT * FROM USUARIOS",null)
        db.close()
        return cursor

    }


    fun utilizadorListSelectAll(): ArrayList<Utilizador>{
        val db= this.readableDatabase
        val cursor= db.rawQuery("SELECT * FROM USUARIOS",null)
        val listaUtilizador: ArrayList<Utilizador> = ArrayList()
        if(cursor.count > 0){
            cursor.moveToFirst()
            do{
                val idIndex= cursor.getColumnIndex("id")
                val nomeIndex= cursor.getColumnIndex("nome")
                val foneIndex= cursor.getColumnIndex("fone")

                val id=cursor.getInt(idIndex)
                val nome=cursor.getString(nomeIndex)
                val fone=cursor.getString(foneIndex)

                listaUtilizador.add(Utilizador(id,nome,fone))
            }while (cursor.moveToNext())
        }
        db.close()
        return listaUtilizador
    }

}

 */