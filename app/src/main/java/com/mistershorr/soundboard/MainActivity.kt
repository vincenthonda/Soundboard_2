package com.mistershorr.soundboard

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mistershorr.soundboard.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val TAG = "MainActivity"
    lateinit var buttonA : Button
    lateinit var buttonBb : Button
    lateinit var buttonB : Button
    lateinit var buttonCh : Button
    lateinit var buttonD : Button
    lateinit var buttonC : Button
    lateinit var buttonDh : Button
    lateinit var buttonE : Button
    lateinit var buttonF : Button
    lateinit var buttonFh : Button
    lateinit var buttonG : Button
    lateinit var buttonGh : Button
    lateinit var soundPool : SoundPool
    lateinit var notes : List<Note>
    var aNote = 0
    var bbNote = 0
    var bNote = 0
    var cNote = 0
    var chNote = 0
    var dNote = 0
    var dhNote = 0
    var eNote = 0
    var fNote = 0
    var fhNote = 0
    var gNote = 0
    var ghNote = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var inputStream = resources.openRawResource(R.raw.song)
        val jsonText = inputStream.bufferedReader().use { it.readText() }
        val gson = Gson()
        var type = object: TypeToken<List<Note>>(){}.type
        notes = gson.fromJson<List<Note>>(jsonText, type)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_main)

        wireWidgets()
        initializeSoundPool()
        setListeners()
    }

    private fun setListeners() {
        val soundBoardListener = SoundBoardListener()
        buttonA.setOnClickListener(soundBoardListener)
        buttonBb.setOnClickListener(soundBoardListener)
        buttonB.setOnClickListener(soundBoardListener)
        buttonC.setOnClickListener(soundBoardListener)
        buttonCh.setOnClickListener(soundBoardListener)
        buttonD.setOnClickListener(soundBoardListener)
        buttonDh.setOnClickListener(soundBoardListener)
        buttonE.setOnClickListener(soundBoardListener)
        buttonF.setOnClickListener(soundBoardListener)
        buttonFh.setOnClickListener(soundBoardListener)
        buttonG.setOnClickListener(soundBoardListener)
        buttonGh.setOnClickListener(soundBoardListener)
    }


    private fun wireWidgets() {
        buttonA = findViewById(R.id.button_main_a)
        buttonBb = findViewById(R.id.button_main_bb)
        buttonB = findViewById(R.id.button_main_b)
        buttonC = findViewById(R.id.button_main_c)
        buttonCh = findViewById(R.id.button_main_ch)
        buttonD = findViewById(R.id.button_main_d)
        buttonDh = findViewById(R.id.button_main_dh)
        buttonE = findViewById(R.id.button_main_e)
        buttonF = findViewById(R.id.button_main_f)
        buttonFh = findViewById(R.id.button_main_fh)
        buttonG = findViewById(R.id.button_main_g)
        buttonGh = findViewById(R.id.button_main_gh)

    }

    private fun initializeSoundPool() {

        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
        aNote = soundPool.load(this, R.raw.scalea, 1)
        bbNote = soundPool.load(this, R.raw.scalebb, 1)
        bNote = soundPool.load(this, R.raw.scaleb, 1)
        cNote =  soundPool.load(this, R.raw.scalec, 1)
        chNote =  soundPool.load(this, R.raw.scalecs, 1)
        dNote =  soundPool.load(this, R.raw.scaled, 1)
        dhNote =  soundPool.load(this, R.raw.scaleds, 1)
        eNote =  soundPool.load(this, R.raw.scalee, 1)
        fNote =  soundPool.load(this, R.raw.scalef, 1)
        fhNote =  soundPool.load(this, R.raw.scalefs, 1)
        gNote =  soundPool.load(this, R.raw.scaleg, 1)
        ghNote =  soundPool.load(this, R.raw.scalegs, 1)
    }

    private fun playNote(noteId : Int) {
        soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
    }

    private inner class SoundBoardListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button_main_a -> playNote(aNote)
                R.id.button_main_bb -> playNote(bbNote)
                R.id.button_main_b -> playNote(bNote)
                R.id.button_main_c -> playNote(cNote)
                R.id.button_main_ch -> playNote(chNote)
                R.id.button_main_d -> playNote(dNote)
                R.id.button_main_dh -> playNote(dhNote)
                R.id.button_main_e -> playNote(eNote)
                R.id.button_main_f -> playNote(fNote)
                R.id.button_main_fh -> playNote(fhNote)
                R.id.button_main_g -> playNote(gNote)
                R.id.button_main_gh -> playNote(ghNote)

            }
        }
    }
}