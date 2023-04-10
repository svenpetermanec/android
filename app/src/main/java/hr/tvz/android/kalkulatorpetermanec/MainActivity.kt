package hr.tvz.android.kalkulatorpetermanec

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import hr.tvz.android.kalkulatorpetermanec.databinding.ActivityMainBinding
import kotlin.math.E
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun setResources() {
            binding.title.setText(R.string.title)
            binding.description.setText(R.string.description)
            binding.weight.setHint(R.string.weight)
            binding.reps.setHint(R.string.reps)
            binding.button.setText(R.string.button)
        }

        setResources()

        binding.button.setOnClickListener {
            val weight = binding.weight.text.toString().toInt()
            val reps = binding.reps.text.toString().toInt()
            var oneRM = 0.0

            if(binding.reps.text.toString().toInt() > 10) {
                binding.reps.error = getString(R.string.error)
            }

            when(binding.spinner.selectedItem.toString()) {
                "Brzycki" -> oneRM = weight * (36 / (37 - reps).toDouble())
                "Epley" -> oneRM = weight * (1 + 0.0333 * reps)
                "Lander" -> oneRM = (100 * weight) / (101.3 - 2.67123 * reps)
                "Lombardi"  -> oneRM = weight * reps.toDouble().pow(0.1)
                "Mayhew" -> oneRM = (100 * weight) / (52.2 + (41.9 * E.pow(-0.055 * reps)))
                "O'Conner" -> oneRM = weight * (1 + 0.025 * reps)
                "Wathen" -> oneRM = (100 * weight) / (48.8 + (53.8 * E.pow(-0.075 * reps)))
            }

            binding.result.text = StringBuilder().append("1RM ").append(String.format("%.2f", oneRM)).append(" kg")
        }

        binding.themeSwitch.setOnClickListener {
            when(baseContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                    MODE_NIGHT_NO
                )
                Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(
                    MODE_NIGHT_YES
                )
            }
        }
    }
}