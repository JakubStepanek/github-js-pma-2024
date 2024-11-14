package com.example.myapp011ajednoduchamatematika

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp011ajednoduchamatematika.databinding.ActivityMainBinding
import com.example.myapp011ajednoduchamatematika.databinding.ActivityPlayBinding
import com.example.myapp011ajednoduchamatematika.databinding.WinLayoutBinding
import kotlin.compareTo
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var bindingWin: WinLayoutBinding

    var TimeTextView: TextView? = null
    var QuestionTextText: TextView? = null
    var ScoreTextView: TextView? = null
    var AlertTextView: TextView? = null
    var FinalScoreTextView: TextView? = null
    var btn0: Button? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var countDownTimer: CountDownTimer? = null
    var random: Random = Random
    var a = 0
    var b = 0
    var indexOfCorrectAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingMain.root)

        val calInt = intent.getStringExtra("cals")
        cals = calInt!!
        TimeTextView = bindingMain.TimeTextView
        QuestionTextText = bindingMain.QuestionTextText
        ScoreTextView = bindingMain.ScoreTextView
        AlertTextView = bindingMain.AlertTextView


        //FinalScoreTextView = findViewById(R.id.FinalScoreTextView)
        btn0 = bindingMain.button0
        btn1 = bindingMain.button1
        btn2 = bindingMain.button2
        btn3 = bindingMain.button3

        startGame()

    }

    fun NextQuestion(cal: String) {
        a = random.nextInt(10)
        b = random.nextInt(10)
        QuestionTextText!!.text = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {

                when (cal) {
                    "+" -> {
                        answers.add(a + b)
                    }

                    "-" -> {
                        answers.add(a - b)
                    }

                    "*" -> {
                        answers.add(a * b)
                    }

                    "/" -> {
                        try {
                            answers.add(a / b)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } else {
                var wrongAnswer = random.nextInt(20)
                try {
                    while (
                        wrongAnswer == a + b
                        || wrongAnswer == a - b
                        || wrongAnswer == a * b
                        || wrongAnswer == a / b
                    ) {
                        wrongAnswer = random.nextInt(20)
                    }
                    answers.add(wrongAnswer)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        try {
            btn0!!.text = "${answers[0]}"
            btn1!!.text = "${answers[1]}"
            btn2!!.text = "${answers[2]}"
            btn3!!.text = "${answers[3]}"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun optionSelect(view: View?) {
        totalQuestions++
        if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
            points++
            AlertTextView!!.text = "Correct"
        } else {
            AlertTextView!!.text = "Wrong"
        }
        ScoreTextView!!.text = "$points/$totalQuestions"
        NextQuestion(cals)

    }

    fun playAgain(view: View?) {
        onBackPressed()
        points = 0
        totalQuestions = 0
        ScoreTextView!!.text = "$points/$totalQuestions"
        countDownTimer!!.start()

    }

    private fun startGame() {
        NextQuestion(cals)
        countDownTimer = object : CountDownTimer(10000, 500) {
            override fun onTick(p0: Long) {
                TimeTextView!!.text = (p0 / 1000).toString() + "s"
            }

            override fun onFinish() {
                TimeTextView!!.text = "Konec času"
                openDialog()
            }
        }.start()
    }

    private fun openDialog() {
        bindingWin = WinLayoutBinding.inflate(layoutInflater)
        setContentView(bindingWin.root)

        val inflate = LayoutInflater.from(this)
        var winDialog = inflate.inflate(R.layout.win_layout, null)


        FinalScoreTextView = winDialog.findViewById(R.id.FinalScoreTextView)
        val btnPlayAgain = winDialog.findViewById<Button>(R.id.buttonPlayAgain)
        val btnBack = winDialog.findViewById<Button>(R.id.buttonBack)
        var dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(winDialog)
        FinalScoreTextView!!.text = "$points/$totalQuestions"
        btnPlayAgain.setOnClickListener {
            playAgain(it)
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
        val showDialog = dialog.create()
        showDialog.show()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}