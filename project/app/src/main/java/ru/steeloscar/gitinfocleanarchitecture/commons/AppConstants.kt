package ru.steeloscar.gitinfocleanarchitecture.commons

import android.net.Uri

object AppConstants {

    const val APP_PREFERENCES = "sharedPreferences"
    // Fields from default config.
    const val CLIENT_ID = "e7e2aa6b703dbaad5a67"
    const val CLIENT_SECRET = "00b694b186542152fdcc974b50292a5322a8fa80"
    const val REDIRECT_URI = "steeloscar://callback"
    const val SCOPE = "repo%20user"

    val authorizeUri: Uri
    get() = Uri.parse(
            "https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}&scope=${SCOPE}" +
                    "&redirect_uri=${REDIRECT_URI}&login=${GitInfoPreferences.getLogin()}"
    )

    val registerUri: Uri = Uri.parse("https://github.com/join")
}