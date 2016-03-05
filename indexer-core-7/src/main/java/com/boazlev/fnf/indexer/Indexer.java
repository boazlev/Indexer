package com.boazlev.fnf.indexer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boazlev.fnf.indexer.exceptions.IndexerException;
import com.boazlev.fnf.indexer.exceptions.InvalidPdfIndexerException;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Indexer {

	public Map<String, Integer> index(String pdfFileName, String idsFileName) {
		FileInputStream pdfInputStream = null;
		try {
			pdfInputStream = new FileInputStream(pdfFileName);
		} catch (FileNotFoundException e) {
			throw new IndexerException("Failed getting text from file ", e);
		}
		return this.index(pdfInputStream, idsFileName);
	}

	public Map<String, Integer> index(InputStream pdfInputStream, String idsFileName) {
		Map<String, Integer> map = new HashMap<>();
		StringBuilder content = new StringBuilder();
		PdfReader reader;
		try {
			reader = new PdfReader(pdfInputStream);
			int n = reader.getNumberOfPages();
			for (int i = 1; i <= n; i++) {
				String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
				content.append(textFromPage);
			}
		} catch (InvalidPdfException ipe) {
			throw new InvalidPdfIndexerException("Invalid PDF format", ipe);
		} catch (IOException e) {
			throw new IndexerException("Failed getting text from file ", e);
		}
		try(InputStream in = this.getClass().getClassLoader().getResourceAsStream(idsFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));){
			String line;
			List<String> lines = new ArrayList<>();
			try {
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
			} catch (IOException e) {
				 throw new IndexerException("Failed getting text from file ", e);
			}
			map = this.index(content.toString(), lines);
		} catch (IOException e) {
			throw new IndexerException("Failed reading text from file ", e);
		}
		return map;
	}

	private Map<String, Integer> index(final String content, Collection<String> lines) {
		Map<String, Integer> map = new HashMap<>();
		for (String line : lines) {
			int count = this.count(content, line);
			if (count > 0) {
				map.put(line, count);
			}
		}
		return map;
	}

	public int count(String content, String id) {
		Pattern p = Pattern.compile(id);
		Matcher m = p.matcher(content);
		int count = 0;
		while (m.find()) {
			count++;
		}
		return count;
	}
}
