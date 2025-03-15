package in.hridayan.xmlforge.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipboardHelper {

  public static void copyToClipboard(Context context, String text) {
    ClipboardManager clipboard =
        (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    if (clipboard != null) {
      // Ensure consistent line breaks for external applications
      text = text.replaceAll("\r\n", "\n"); // Normalize line breaks to \n
      ClipData clip = ClipData.newPlainText("Formatted XML", text);
      clipboard.setPrimaryClip(clip);
    }
  }
}