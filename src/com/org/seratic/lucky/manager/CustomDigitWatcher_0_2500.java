package com.org.seratic.lucky.manager;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

public class CustomDigitWatcher_0_2500 implements InputFilter {

	private EditText mEditText;

	public CustomDigitWatcher_0_2500(EditText e) {
		mEditText = e;
	}

	String CurrentWord;

	@Override
	public CharSequence filter(CharSequence source, int start, int end,
			Spanned dest, int dstart, int dend) {
		Log.i("CustomDigitWatcher",
				"source: " + source + " - dest: " + dest.toString()
						+ " - start: " + start + " - end: " + end
						+ " - dstart: " + dstart + " - dend: " + dend);
		String data = source.toString().substring(start, end);
		String retorno = null;
		if (dest != null) {
			int tam_previo = dest.length();
			int pos = -1;
			pos = find(dest, '.');
			switch (tam_previo) {
			case 0:
				if (data.matches("[0-9]")) {
					retorno = data;
				} else {
					retorno = "";
				}
				break;
			case 1:
				if (data.matches("[0-9]")) {
					if (dest.charAt(0) == '0') {
						retorno = "";
						mEditText.setText(source);
					} else {
						if (pos > -1) {
							if (pos > 0 && (tam_previo - 3) < pos) {
								retorno = data;
							} else {
								retorno = "";
							}
						} else {
							retorno = data;
						}
					}
				} else if (data.equalsIgnoreCase(".")) {
					if (pos > -1) {
						retorno = "";
					} else {
						if (dstart > 0) {
							retorno = data;
						} else {
							retorno = "";
						}
					}
				} else {
					retorno = "";
				}

				break;
			case 2:
				if (data.matches("[0-9]")) {
					if (dest.charAt(0) == '0' && pos == -1) {
						if (data.matches("[0-9]")) {
							retorno = "";
							mEditText.setText(source);
						} else {
							retorno = data;
						}
					} else {
						if (pos > -1) {
							if (pos > 0 && (tam_previo - 3) < pos) {
								retorno = data;
							} else {
								retorno = "";
							}
						} else {
							retorno = data;
						}
					}
				} else if (data.equalsIgnoreCase(".")) {
					if (pos > -1) {
						retorno = "";
					} else {
						if (dstart > 0) {
							retorno = data;
						} else {
							retorno = "";
						}
					}
				} else {
					retorno = "";
				}
				break;
			case 3:
				if (data.matches("[0-9]")) {
					if (dest.charAt(0) == '0' && pos == -1) {
						retorno = "";
						mEditText.setText(source);
					} else {
						if (pos > -1) {
							if (pos > 0 && (tam_previo - 3) < pos) {
								retorno = data;
							} else {
								retorno = "";
							}
						} else {
							if (dest.charAt(0) == '2' && dest.charAt(1) == '5'
									&& dest.charAt(2) == '0'
									&& data.equalsIgnoreCase("0")) {
								retorno = data;
							} else {
								retorno = "";
							}
						}
					}
				} else if (data.equalsIgnoreCase(".")) {
					if (pos > -1) {
						retorno = "";
					} else {
						if (dstart > 0) {
							retorno = data;
						} else {
							retorno = "";
						}
					}
				} else {
					retorno = "";
				}
				break;
			case 4:
				if (data.matches("[0-9]")) {
					if (dest.charAt(0) == '0' && pos == -1) {
						retorno = "";
						mEditText.setText(source);
					} else {
						if (pos > -1) {
							if (pos > 0 && (tam_previo - 3) < pos) {
								retorno = data;
							} else {
								retorno = "";
							}
						} else {
							retorno = "";
						}
					}
				} else {
					retorno = "";
				}
				break;
			case 5:
				if (data.matches("[0-9]")) {
					if (dest.charAt(0) == '0' && pos == -1) {
						retorno = "";
						mEditText.setText(source);
					} else {
						if (pos > -1) {
							if (pos > 0 && (tam_previo - 3) < pos) {
								retorno = data;
							} else {
								retorno = "";
							}
						} else {
							retorno = "";
						}
					}
				} else {
					retorno = "";
				}
				break;
			default:
					retorno = "";
				break;
			}
		}
		return retorno;

	}

	private int find(Spanned dest, char crit) {
		int pos = -1;
		int tam = dest.length();
		for (int i = 0; i < tam; i++) {
			if (dest.charAt(i) == crit) {
				pos = i;
				break;
			}
		}
		return pos;
	}

}
