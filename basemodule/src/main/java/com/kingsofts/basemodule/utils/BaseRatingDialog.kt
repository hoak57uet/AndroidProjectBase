//package com.kingsofts.basemodule.utils
//
//import android.R
//import android.content.Context
//import com.codemybrainsout.ratingdialog.RatingDialog
//
//
//class BaseRatingDialog {
//    companion object {
//        var ratingDialog: RatingDialog? = null
//        private fun instance(context: Context): RatingDialog {
//            if (ratingDialog == null) {
//                ratingDialog = RatingDialog.Builder(context)
//                    .session(2)
//                    .title("How was your experience with us?")
//                    .titleTextColor(R.color.black)
//                    .positiveButtonText("Not Now")
//                    .negativeButtonText("Never")
////                    .positiveButtonTextColor(R.color.white)
////                    .negativeButtonTextColor(R.color.grey_500)
//                    .formTitle("Submit Feedback")
//                    .formHint("Tell us where we can improve")
//                    .formSubmitText("Submit")
//                    .formCancelText("Cancel")
////                    .ratingBarColor(R.color.yellow)
//                    .build()
//
//            }
//            return ratingDialog!!
//        }
//
//        public fun show(context: Context) {
//            instance(context).show()
//        }
//    }
//}