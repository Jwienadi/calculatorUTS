package com.android.calculatoruts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.round


class MainActivity : AppCompatActivity() {
    lateinit var calc: String
    lateinit var res: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0.setOnClickListener { displayCalcNum("0") }
        btn1.setOnClickListener { displayCalcNum("1") }
        btn2.setOnClickListener { displayCalcNum("2") }
        btn3.setOnClickListener { displayCalcNum("3") }
        btn4.setOnClickListener { displayCalcNum("4") }
        btn5.setOnClickListener { displayCalcNum("5") }
        btn6.setOnClickListener { displayCalcNum("6") }
        btn7.setOnClickListener { displayCalcNum("7") }
        btn8.setOnClickListener { displayCalcNum("8") }
        btn9.setOnClickListener { displayCalcNum("9") }
        btnkoma.setOnClickListener { displayCalcNum(".") }

        btntambah.setOnClickListener { displayCalcOperator("+") }
        btnmin.setOnClickListener { displayCalcOperator("-") }
        btnkali.setOnClickListener { displayCalcOperator("x") }
        btnbagi.setOnClickListener { displayCalcOperator("/") }
        btnkurung.setOnClickListener { displayKurung() }

        btnakar.setOnClickListener {
            if(lastIsOperator() || tvcalculate.text == "" ){
                displayCalcNum("√(")
            }else {
                displayCalcNum("x√(")
            }

        }
        btnkuadrat.setOnClickListener { displayCalcOperator("^(2)") }

        btnc.setOnClickListener { clear() }
        btnac.setOnClickListener { allclear() }

        btnsamadengan.setOnClickListener { hasil() }

        btnfloor.setOnClickListener { floorhasil()  }
        btnceiling.setOnClickListener { ceilhasil() }
        btnround.setOnClickListener { roundhasil() }

        }
    private fun floorhasil(){
        hasil()
        var angka:Double =tvresult.text.toString().toDouble()
        tvresult.text= floor(angka).toInt().toString()
    }
    private  fun ceilhasil(){
        hasil()
        var angka:Double =tvresult.text.toString().toDouble()
        tvresult.text= ceil(angka).toInt().toString()
    }
    private fun roundhasil(){
        hasil()
        var angka:Double =tvresult.text.toString().toDouble()
        tvresult.text= (round(angka * 10.0)/10.0).toString()

    }


    private fun displayCalcNum(input: String) {
//            calc+=input
//            tvcalculate.text=calc.replace("*","x")

        tvcalculate.append(input)
    }

    private fun lastIsOperator(): Boolean {

        return if (tvcalculate.text != ""){
            var lastindex=tvcalculate.text.reversed()[0]
            var alloperator="/x+-"
            lastindex in alloperator
        } else{
            false
        }
    }
    private fun displayCalcOperator(input: String) {
//            calc+=input
//            tvcalculate.text=calc.replace("*","x")
//        var lastindex=tvcalculate.text.reversed()[0]
//        var alloperator="/x+-"
        try {
            if(lastIsOperator()){
                tvcalculate.text=tvcalculate.text.dropLast(1)
                tvcalculate.append(input)
            } else{
                tvcalculate.append(input)
            }
        } catch (e:Exception){
            Toast.makeText(this,"Invalid Format Used",Toast.LENGTH_LONG).show()
        }

    }

    private fun displayKurung(){
        var teks=tvcalculate.text.toString()

        if (teks.filter{it== '('}.count()==teks.filter{it== ')'}.count()){
            displayCalcNum("(")
        } else {
            displayCalcNum(")")
        }
    }

    private fun clear(){
//        tvcalculate.text=tvcalculate.text.dropLast(1)
//        tvresult.text=tvresult.text.drop(tvresult.text.length)
        if ("/+x-" in tvcalculate.text) {
            while (!lastIsOperator()) {
                tvcalculate.text = tvcalculate.text.dropLast(1)
                if (tvcalculate.text == "") {
                    println("wis kosong")
                }

            }

        } else{
            allclear()
        }


//        tvcalculate.setTextSize(60F)
//        this.calc.dropLast(1)
//        tvcalculate.text=this.calc.replace("*","x")
    }
    private fun allclear(){
        tvcalculate.text=tvcalculate.text.drop(tvcalculate.text.length)
        tvresult.text=tvresult.text.drop(tvresult.text.length)
//        tvcalculate.setTextSize(60F)
//        this.calc.drop(this.calc.length)
//        tvcalculate.text=this.calc.replace("*","x")
    }

    private fun hasil(){
        var teks=tvcalculate.text.toString().replace("x","*").replace("√","sqrt")


        try {
        var calculate= ExpressionBuilder(teks).build()
        var hasil: Double
        hasil= calculate.evaluate()
        if ((hasil % 1).equals(0.0) || (hasil % 1).equals(-0.0)) {
            tvresult.text=hasil.toLong().toString()
        } else {
            tvresult.text=hasil.toString()
        }
//        tvcalculate.setTextSize(60F)

//        Toast.makeText(this,hasil.toString(),Toast.LENGTH_LONG).show()

        } catch (e:Exception){
            Toast.makeText(this,"Invalid Format Used",Toast.LENGTH_LONG).show()
        }

    }




}