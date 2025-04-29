package in.hridayan.xmlforge.fragments;

import in.hridayan.xmlforge.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import in.hridayan.xmlforge.databinding.FragmentHomeBinding;
import in.hridayan.xmlforge.utils.ClipboardHelper;
import in.hridayan.xmlforge.utils.XmlFormatter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.formatButton.setOnClickListener(v -> formatXml());
        binding.copyButton.setOnClickListener(v -> copyFormattedXml());
        binding.inputLayout.setEndIconOnClickListener(v -> clearFields());
    }

    private void formatXml() {
        String input = binding.inputXml.getText().toString().trim();
        if (input.isEmpty()) {
            showToast(getString(R.string.toast_enter_xml));
            return;
        }

        String formattedXml = XmlFormatter.formatXml(input);
        if (formattedXml != null) {
            binding.outputXml.setText(formattedXml);
        } else {
            showToast(getString(R.string.toast_format_failed));
        }
    }

    private void copyFormattedXml() {
        String formattedText = binding.outputXml.getText().toString();
        if (formattedText.isEmpty()) {
            showToast(getString(R.string.toast_no_xml_to_copy));
            return;
        }

        ClipboardHelper.copyToClipboard(requireContext(), formattedText);
        showToast(getString(R.string.toast_copied));
    }

    private void clearFields() {
        binding.inputXml.setText("");
        binding.outputXml.setText("");
        showToast(getString(R.string.toast_cleared));
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
