package com.therealbluepandabear.pixapencil.fragments.tools

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentToolsBinding
import com.therealbluepandabear.pixapencil.listeners.ToolsFragmentListener
import com.therealbluepandabear.pixapencil.utility.StringConstants

class ToolsFragment : Fragment() {
    private fun setup() {
        setOnClickListeners()

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            onOptionTapped(binding.fragmentToolsPencilButton)
        } else {
            onOptionTapped(binding.fragmentToolsPencilButtonH)
        }

        caller.onToolTapped(StringConstants.PencilToolIdentifier)
    }

    fun tapOnToolByName(toolName: String) {
        when (toolName) {
            StringConstants.PencilToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsPencilButton)
                } else {
                    onOptionTapped(binding.fragmentToolsPencilButtonH)
                }
            }

            StringConstants.FillToolIdentifier  -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsFillButton)
                } else {
                    onOptionTapped(binding.fragmentToolsFillButtonH)
                }
            }

            StringConstants.LineToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsLineButton)
                } else {
                    onOptionTapped(binding.fragmentToolsLineButtonH)
                }
            }

            StringConstants.RectangleToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsRectangleButton)
                } else {
                    onOptionTapped(binding.fragmentToolsRectangleButtonH)
                }
            }

            StringConstants.OutlinedRectangleToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsOutlinedRectangleButton)
                } else {
                    onOptionTapped(binding.fragmentToolsOutlinedRectangleButtonH)
                }
            }

            StringConstants.SquareToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsSquareButton)
                } else {
                    onOptionTapped(binding.fragmentToolsSquareButtonH)
                }
            }

            StringConstants.OutlinedSquareToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsOutlinedSquareButton)
                } else {
                    onOptionTapped(binding.fragmentToolsOutlinedSquareButtonH)
                }
            }

            StringConstants.CircleToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsCircleButton)
                } else {
                    onOptionTapped(binding.fragmentToolsCircleButtonH)
                }
            }

            StringConstants.OutlinedCircleToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsOutlinedCircleButton)
                } else {
                    onOptionTapped(binding.fragmentToolsOutlinedCircleButtonH)
                }
            }

            StringConstants.SprayToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsSprayButton)
                } else {
                    onOptionTapped(binding.fragmentToolsSprayButtonH)
                }
            }

            StringConstants.PolygonToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsPolygonButton)
                } else {
                    onOptionTapped(binding.fragmentToolsPolygonButtonH)
                }
            }

            StringConstants.DitherToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsDitherButton)
                } else {
                    onOptionTapped(binding.fragmentToolsDitherButtonH)
                }
            }

            StringConstants.DarkenToolIdentifier  -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsDarkenButton)
                } else {
                    onOptionTapped(binding.fragmentToolsDarkenButtonH)
                }
            }

            StringConstants.LightenToolIdentifier  -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsLightenButton)
                } else {
                    onOptionTapped(binding.fragmentToolsLightenButtonH)
                }
            }

            StringConstants.ColorPickerToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsColorPickerButton)
                } else {
                    onOptionTapped(binding.fragmentToolsColorPickerButtonH)
                }
            }

            StringConstants.EraseToolIdentifier -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onOptionTapped(binding.fragmentToolsEraseButton)
                } else {
                    onOptionTapped(binding.fragmentToolsEraseButtonH)
                }
            }
        }
    }

    companion object {
        fun newInstance(): ToolsFragment {
            return ToolsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToolsFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentToolsBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

}