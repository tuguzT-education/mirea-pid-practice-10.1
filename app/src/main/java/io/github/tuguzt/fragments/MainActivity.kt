package io.github.tuguzt.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import io.github.tuguzt.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    private var first: Fragment = RedFragment()
    private var second: Fragment = BlueFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.caption.setOnClickListener {
            removeFragment()
            viewModel.invertStartup()
        }

        viewModel.startup.observe(this) {
            if ((viewModel.getStartup() && first is BlueFragment) ||
                (!viewModel.getStartup() && first is RedFragment))
                    second = first.also { first = second }

            loadFragment(binding.fragment1, first)
            loadFragment(binding.fragment2, second)
        }
    }

    private fun removeFragment() {
        supportFragmentManager.apply {
            beginTransaction().remove(first).commit()
            executePendingTransactions()

            beginTransaction().remove(second).commit()
            executePendingTransactions()
        }
    }

    private fun loadFragment(frameLayout: FrameLayout, fragment: Fragment) {
        supportFragmentManager.apply {
            beginTransaction().add(frameLayout.id, fragment).commit()
            executePendingTransactions()
        }
    }
}
