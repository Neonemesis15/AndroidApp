package com.org.seratic.lucky.manager;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

public class CustomTextWatcher implements InputFilter {

	private EditText mEditText;

	public CustomTextWatcher(EditText e) {
		mEditText = e;
	}

	String CurrentWord;

	@Override
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
		String data = source.toString().substring(start, end);

		boolean isValid = data.matches("[A-Za-z 0-9.,/-]+");
		if (!isValid) {
			return "";
		}
		return null;
	}

}
