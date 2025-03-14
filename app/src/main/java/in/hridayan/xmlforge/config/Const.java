package in.hridayan.xmlforge.config;
import java.util.Arrays;
import java.util.List;

public interface Const {
    
    // XML formatting priority order for reordering attributes
     List<String> ATTR_PRIORITY_ORDER =
      Arrays.asList(
          "xmlns:android",
          "android:id",
          "android:layout_width",
          "android:layout_height",
          "android:layout_margin",
          "android:layout_marginStart",
          "android:layout_marginEnd",
          "android:layout_marginTop",
          "android:layout_marginBottom",
          "android:layout_marginHorizontal",
          "android:layout_marginVertical");
}
