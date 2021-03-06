package xyz.tusion.baltseahack_androidapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import io.reactivex.disposables.CompositeDisposable
import xyz.tusion.baltseahack_androidapp.R

/**
 * Базовый класс включающий в себя общую для всех Fragment логику в проекте.
 */
abstract class BaseFragment(
    @LayoutRes layoutRes: Int? = null
) : Fragment(layoutRes ?: R.layout.empty_layout) {

    protected var firstStart: Boolean = true

    protected val rxBinds = CompositeDisposable()

    protected lateinit var navController: NavController
    /**
     * Метод вызывается один раз при старте фрагмента
     * Здесь можно обработать переданные в аргументах данные
     */
    protected open fun init() {}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navController = findNavController()
        return super.onCreateView(inflater, container, savedInstanceState)

    }
    override fun onResume() {
        super.onResume()
        if (firstStart) {
            init()
            firstStart = false
        }
        createBinds()
    }

    override fun onPause() {
        super.onPause()
        rxBinds.clear()
    }

    protected open fun createBinds() {
    }

}