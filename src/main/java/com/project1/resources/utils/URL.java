package com.project1.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String formatarStringBusca(String meuParam) {
		try {
			return URLDecoder.decode(meuParam, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> convertStringToListInteger(String paramString){
		String[] meuVetor = paramString.split(",");
		List<Integer> minhaLista = new ArrayList<Integer>();
		for(int i=0; i < meuVetor.length; i++) {
			minhaLista.add(Integer.parseInt(meuVetor[i]));
		}
		return minhaLista;
	}
	
}
