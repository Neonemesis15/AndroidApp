package com.org.seratic.lucky.manager;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

public class CustomDigitWatcher implements InputFilter {

	private EditText mEditText;

	public CustomDigitWatcher(EditText e) {
		mEditText = e;
	}

	String CurrentWord;

	@Override
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
		String data = source.toString().substring(start, end);
		String retorno = null;
		if (dest != null) {
			int tam_previo = dest.length();
			switch (tam_previo) {
			case 0:
				if (data.matches("[0-9]")) {
					retorno = data;
				} else {
					retorno = "";
				}
				break;
			case 1:
				if (dstart == 1) {
					if (data.matches("[0-9]")) {
						if (dest.charAt(0) == '0') {
							retorno = "";
							mEditText.setText(source);
						} else if (dest.charAt(0) == '1') {
							retorno = data;
						} else if (dest.charAt(0) == '2' && data.equalsIgnoreCase("0")) {
							retorno = data;
						} else {
							retorno = "";
						}
					}
				} else {
					if (data.equalsIgnoreCase("1") || (dest.charAt(0) == '0' && data.equalsIgnoreCase("2"))) {
						retorno = data;
					} else {
						retorno = "";
					}
				}
				break;
			default:
				retorno = "";
				break;
			}
		} else {
			if (data.matches("[0-9]")) {
				retorno = data;
			} else {
				retorno = "";
			}
		}
		return retorno;

	}

}
