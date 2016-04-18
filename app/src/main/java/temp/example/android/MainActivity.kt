package temp.example.android

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createLayout())
    }

    private fun createLayout(): View {
        return verticalLayout {
            val ID_TOP_ROW = 10
            val ID_LEVEL_1 = 11
            val ID_LEVEL_2 = 12
            val ID_LEVEL_3 = 13
            padding = dip(16)
            val name = editText {
                hint = "I'm a hint"
                textSize = 24f
            }
            button("Measure me") {
                textSize = 24f
                onClick {
                    doMeasure()
                }
            }.lparams(width = matchParent) {
                horizontalMargin = dip(5)
                topMargin = dip(10)
            }

            button("Measure me long") {
                textSize = 24f
                onClick {
                    doLongMeasure()
                }
            }.lparams(width = matchParent) {
                horizontalMargin = dip(5)
                topMargin = dip(10)
            }

            relativeLayout() {
                backgroundColor = Color.DKGRAY

                linearLayout() {
                    id = ID_TOP_ROW
                    padding = dip(16)
                    backgroundColor = Color.RED
                    orientation = LinearLayout.HORIZONTAL
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)

                }.lparams(width = matchParent, height = dip(60)) {
                    alignParentTop()
                }

                linearLayout() {
                    padding = dip(16)
                    backgroundColor = Color.YELLOW
                    orientation = LinearLayout.HORIZONTAL
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                }.lparams(width = matchParent, height = dip(60)) {
                    centerInParent()
                }

                linearLayout() {
                    padding = dip(16)
                    backgroundColor = Color.GREEN
                    orientation = LinearLayout.HORIZONTAL
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                    imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                }.lparams(width = matchParent, height = dip(60)) {
                    alignParentBottom()
                }

                relativeLayout() {
                    backgroundColor = Color.MAGENTA

                    linearLayout() {
                        id = ID_LEVEL_1
                        padding = dip(16)
                        orientation = LinearLayout.HORIZONTAL
                        imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                        imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                        imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                    }.lparams(width = matchParent)

                    relativeLayout() {
                        backgroundColor = Color.BLUE

                        linearLayout() {
                            id = ID_LEVEL_2
                            padding = dip(16)
                            orientation = LinearLayout.HORIZONTAL
                            imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                            imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                            imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                        }.lparams(width = matchParent, height = wrapContent)

                        relativeLayout() {
                            backgroundColor = Color.BLACK

                            linearLayout() {
                                id = ID_LEVEL_3
                                padding = dip(16)
                                orientation = LinearLayout.HORIZONTAL
                                imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                                imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                                imageView(R.mipmap.ic_launcher).lparams(width = 0, height = matchParent, weight = 1f)
                            }.lparams(width = matchParent, height = wrapContent)

                        }.lparams(width = matchParent) {
                            below(ID_LEVEL_2)
                        }

                    }.lparams(width = matchParent) {
                        below(ID_LEVEL_1)
                    }

                }.lparams(width = matchParent) {
                    below(ID_TOP_ROW)
                }

            }.lparams(width = matchParent, height = matchParent)
        }
    }

    private fun doLongMeasure() {
        var start = System.currentTimeMillis()
        for (i in 1..10)
            createLayout()
        var stop = System.currentTimeMillis()
        Log.d("%%%%%", "DSL Inflate time: ${stop - start}")
        start = System.currentTimeMillis()
        for (i in 1..10)
            LayoutInflater.from(this).inflate(R.layout.activity_main, null, false)
        stop = System.currentTimeMillis()
        Log.d("%%%%%", "XML Inflate time: ${stop - start}")
    }

    private fun doMeasure() {
        var start = System.currentTimeMillis()
        val dslView = createLayout()
        var stop = System.currentTimeMillis()
        Log.d("%%%%%", "DSL Inflate time: ${stop - start}")
        start = System.currentTimeMillis()
        val xmlView = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false)
        stop = System.currentTimeMillis()
        Log.d("%%%%%", "XML Inflate time: ${stop - start}")
    }
}
