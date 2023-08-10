package com.dlgustjr02124.autocrypt.data.setting

class Constant {
    companion object {
        val SERVER_HOST = "https://api.odcloud.kr"
        private const val SERVER_PATH = "/"
        val SERVER_URL = SERVER_HOST + SERVER_PATH
        const val GET_CENTER_LIST = "api/15077586/v1/centers"

        val CONNECT_TIMEOUT = 5L
        val WRITE_TIMEOUT = 5L
        val READ_TIMEOUT = 5L
        val RETURN_TYPE = "json"

        const val ACCEPT = ""
        val TOKEN =
            "bNmSjmL3NWL/mAmsQV0SyDT+8DCdZckhVg5/tSsmJHa47eBZBE+aFvCHYxeM1Dsz2FcgQ64elqYL3mr6GUyjOg=="

    }
}