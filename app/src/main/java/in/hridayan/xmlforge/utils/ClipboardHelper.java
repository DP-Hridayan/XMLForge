package in.hridayan.xmlforge.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipboardHelper {

  public static void copyToClipboard(Context context, String text) {
    ClipboardManager clipboard =
        (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    if (clipboard != null) {
      String fixedText = text.replace("\n", "\r\n");
      ClipData clip = ClipData.newPlainText("Formatted XML", fixedText);
      clipboard.setPrimaryClip(clip);
    }
  }
}