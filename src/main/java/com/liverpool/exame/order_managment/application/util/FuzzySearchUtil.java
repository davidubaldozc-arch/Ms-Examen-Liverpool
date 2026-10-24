package com.liverpool.exame.order_managment.application.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class FuzzySearchUtil {
	
	/*
	 * Limpia el texto utilizando la libreria de Apache 
	 * para quitar acentos
	 */
	public static String normalize(String input) {
		if(input == null) {
			return "";
		}
		
		//stripAccents quita todos los acentos 
		String normalized = StringUtils.stripAccents(input);
		return normalized.replaceAll("[,.]", "").toLowerCase().trim();
	}
	
	/*
	 * Compara los textos
	 */
	public static boolean isFlexibleMatch(String textoAlmacenado, String textoBusqueda,
			int toleranciaErrores) {
		String source = normalize(textoAlmacenado);
		String target = normalize(textoBusqueda);
		
		if(source.contains(target)) {
			return true;
		}
		
		//Le dejamos el calculo a la libreria
		int distance  = LevenshteinDistance.getDefaultInstance().apply(source, target);
		return distance <= toleranciaErrores;
	}

}
