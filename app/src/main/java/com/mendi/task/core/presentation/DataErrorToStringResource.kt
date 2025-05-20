package com.mendi.task.core.presentation

import com.mendi.task.R
import com.mendi.task.core.domain.DataError

fun DataError.toUiText(): Int {
  val stringRes = when (this) {
    DataError.Remote.REQUEST_TIMEOUT -> R.string.error_request_timeout
    DataError.Remote.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
    DataError.Remote.NO_INTERNET -> R.string.error_no_internet
    DataError.Remote.SERVER -> R.string.error_unknown
    DataError.Remote.SERIALIZATION -> R.string.error_serialization
    DataError.Remote.UNKNOWN -> R.string.error_unknown
  }

  return stringRes
}
