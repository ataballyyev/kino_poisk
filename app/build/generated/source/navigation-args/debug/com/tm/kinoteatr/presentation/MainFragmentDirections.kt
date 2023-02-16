package com.tm.kinoteatr.presentation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.tm.kinoteatr.R
import com.tm.kinoteatr.`data`.model.Film
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class MainFragmentDirections private constructor() {
  private data class ActionMainFragmentToDetailsFragment(
    public val film: Film
  ) : NavDirections {
    public override val actionId: Int = R.id.action_mainFragment_to_detailsFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Film::class.java)) {
          result.putParcelable("film", this.film as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(Film::class.java)) {
          result.putSerializable("film", this.film as Serializable)
        } else {
          throw UnsupportedOperationException(Film::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionMainFragmentToDetailsFragment(film: Film): NavDirections =
        ActionMainFragmentToDetailsFragment(film)
  }
}
