package com.syarifhidayatullah.myapplication.model.dummy

class ListModel(jenis: String, name: String, src: String, jumlah: Int) {
    var jenis = ""
    var name = ""
    var src = ""
    var jumlah:Int=0

    init {
        this.jenis = jenis
        this.name = name
        this.src = src
        this.jumlah = jumlah
    }

}