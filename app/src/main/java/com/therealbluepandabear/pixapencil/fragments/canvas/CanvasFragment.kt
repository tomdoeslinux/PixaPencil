package com.therealbluepandabear.pixapencil.fragments.canvas

/**
 * Fragment Structure -> If you are making any changes to the code, follow these guidelines:
 *
 * ,------------,
 * [    Root    ]
 * '------------'
 *      │
 *      ▼
 * ,---------------,
 * [    Binding    ]
 * '---------------'
 *      │
 *      ▼
 * ,--------------,
 * [    Caller    ]
 * '--------------'
 *      │
 *      ▼
 * ,-------------,
 * [    Title    ]
 * '-------------'
 *      │
 *      ▼
 * ,-----------------,
 * [    Variables    ]
 * '-----------------'
 *      │
 *      ▼
 * ,-----------------------------------,
 * [    Private Functions/Functions    ]
 * '-----------------------------------'
 *      │
 *      ▼
 * ,------------------------,
 * [    Companion Object    ]
 * '------------------------'
 *      │
 *      ▼
 * ,----------------------------------,
 * [    Interface Caller Functions    ]
 * '----------------------------------'
 *      │
 *      ▼
 * ,---------------,
 * [    OnAttach   ]
 * '---------------'
 *      │
 *      ▼
 * ,--------------------------,
 * [    OnCreateOptionsMenu   ]
 * '--------------------------'
 *      │
 *      ▼
 * ,-------------------,
 * [    OnCreateView   ]
 * '-------------------'
 *      │
 *      ▼
 * ,---------------,
 * [    OnCreate   ]
 * '---------------'
 *      │
 *      ▼
 * ,--------------------,
 * [    OnDestroyView   ]
 * '--------------------'
 */

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.customviews.pixelgridview.PixelGridView
import com.therealbluepandabear.pixapencil.databinding.FragmentCanvasBinding
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pixapencil.listeners.CanvasFragmentListener
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

lateinit var pixelGridViewInstance: PixelGridView

class CanvasFragment : Fragment() {
    var backingBindingProperty: FragmentCanvasBinding? = null

    val binding get(): FragmentCanvasBinding {
        return backingBindingProperty!!
    }

    lateinit var caller: CanvasFragmentListener

    var bitmap: Bitmap? = null

    private var paramWidth: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    private var paramHeight: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    private var paramOuterCanvasInstance: OuterCanvasFragment? = null
    private var paramProjectTitle: String? = null
    private var paramIndex: Int = -1

    fun setParams(paramWidth: Int, paramHeight: Int, outerCanvasInstance: OuterCanvasFragment, paramProjectTitle: String?, paramIndex: Int) {
        this.paramWidth = paramWidth
        this.paramHeight = paramHeight
        this.paramOuterCanvasInstance = outerCanvasInstance
        this.paramProjectTitle = paramProjectTitle
        this.paramIndex = paramIndex
    }

    private fun setup() {
        if (paramOuterCanvasInstance != null) {
            pixelGridViewInstance = PixelGridView(requireContext(), paramWidth, paramHeight, paramOuterCanvasInstance!!, paramProjectTitle, paramIndex)
            binding.root.addView(pixelGridViewInstance)
        }
    }

    companion object {
        fun newInstance(paramWidth: Int, paramHeight: Int, outerCanvasInstance: OuterCanvasFragment, paramProjectTitle: String?, paramIndex: Int): CanvasFragment {
            val fragment = CanvasFragment()
            fragment.setParams(paramWidth, paramHeight, outerCanvasInstance, paramProjectTitle, paramIndex)

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentCanvasBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
    }
}