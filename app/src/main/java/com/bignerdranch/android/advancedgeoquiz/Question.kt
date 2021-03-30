package com.bignerdranch.android.geoquiz

import androidx.annotation.StringRes

//@StringRes ensures that the variable that follows is actually a resource Id, and not any Integer
data class Question(@StringRes val questionResId: Int, val answer: Boolean, var isAnswered:Boolean = false, var score:Int = 0)