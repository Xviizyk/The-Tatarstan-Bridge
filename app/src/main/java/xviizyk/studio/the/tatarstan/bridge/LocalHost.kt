package xviizyk.studio.the.tatarstan.bridge

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import java.io.InputStream

class LocalHost(private val context: Context, port: Int) : NanoHTTPD(port) {

    override fun serve(session: IHTTPSession): Response {
        var uri = session.uri
        if (uri == "/") {
            uri = "/index.html"
        }
        val assetPath = uri.substring(1)

        val inputStream: InputStream = context.assets.open(assetPath)
        val mimeType = getMimeType(uri)
        return newChunkedResponse(Response.Status.OK, mimeType, inputStream)
    }

    private fun getMimeType(uri: String): String {
        return when {
            uri.endsWith(".html", ignoreCase = true) -> "text/html"
            uri.endsWith(".css", ignoreCase = true) -> "text/css"
            uri.endsWith(".js", ignoreCase = true) -> "application/javascript"
            uri.endsWith(".json", ignoreCase = true) -> "application/json"
            else -> "application/octet-stream"
        }
    }
}