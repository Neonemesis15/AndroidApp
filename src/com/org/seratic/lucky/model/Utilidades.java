package com.org.seratic.lucky.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class Utilidades {

	private static SimpleDateFormat sdfDateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public static String convertDateToString(Date fecha) {
		return sdfDateTime.format(fecha);
	}

	public static int parseEntero(String str) {
		int retorno = 0;
		try {
			if (str != null && !str.trim().equals("")) {
				retorno = Integer.parseInt(str);
			}
		} catch (Exception e) {
			Log.e("*", "La cadena " + str + " no tiene el formato entero");
		}
		return retorno;
	}

	public static double parseDouble(String str) {
		double retorno = 0;
		if (str != null && !str.trim().equals("")) {
			try {
				retorno = Double.parseDouble(str);
			} catch (Exception e) {
				Log.e("*", "La cadena " + str + " no tiene el formato double");
			}
		}
		return retorno;
	}

	public static int parseCoordenada(String coordenada) {
		int inCoordenada = 0;
		try {
			float flCoordenada = Float.parseFloat(coordenada);
			// inCoordenada = (int)(flCoordenada * 1000000);
			inCoordenada = (int) (flCoordenada * 10);
		} catch (Exception e) {
			Log.e("Utilidades", "La coordenada '" + coordenada + "' no cumple el formato de coordenada");
		}
		return inCoordenada;
	}

	public static String cleanNombreFoto(String urlNameFoto) {
		String nameFoto = "";
		if (urlNameFoto != null) {
			String extension = ".jpg";
			int posI = urlNameFoto.lastIndexOf("/");
			int posF = urlNameFoto.indexOf(extension);
			if (posF == -1) {
				posF = urlNameFoto.length();
			}
			nameFoto = urlNameFoto.substring(posI + 1, posF);
		}
		return nameFoto;
	}

	// public static void valideDecimalNumber(Editable s) {
	// String df = s.toString();
	// try {
	// Double.parseDouble(df);
	// } catch (Exception e) {
	// Pattern pattern = Pattern.compile("\\D");
	// Matcher matcher = pattern.matcher(df);
	// Vector<String> letras = new Vector<String>();
	// while (matcher.find()) {
	// int st = matcher.start();
	// int end = matcher.end();
	// String sub = df.substring(st, end);
	// if (!sub.equals("."))
	// letras.add(sub);
	// }
	// if (!letras.isEmpty()) {
	// for (int i = 0; i < letras.size(); i++) {
	// df = df.replace(letras.elementAt(i), "");
	// }
	// s.clear();
	// s.append(df);
	// }
	// String[] spl = df.split("\\.");
	// if (spl != null && spl.length > 1) {
	// String newStr = spl[0] + ".";
	// for (int i = 1; i < spl.length; i++) {
	// newStr += spl[i];
	// }
	// df = newStr;
	// s.clear();
	// s.append(df);
	// }
	// }
	// }

}
