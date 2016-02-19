/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.boazlev.fnf.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.boazlev.fnf.indexer.Indexer;

@SuppressWarnings("serial")
public class IndexerServlet extends HttpServlet {

	private Indexer indexer = new Indexer();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<?xml-stylesheet type=\"text/xsl\" href=\"out.xslt\"?>");
		out.println("<catalog>");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		try {
			FileItemIterator iterator = fileUpload.getItemIterator(request);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				InputStream mathMLContent = item.openStream();
				if (!item.isFormField()) {
					Map<String, Integer> index = indexer.index(mathMLContent, "./lines.txt");
					for (Map.Entry<String, Integer> mapEntry : index.entrySet()) {
						out.println("<cd>");
						out.print("<index>");
						out.print(mapEntry.getKey());
						out.println("</index>");
						out.print("<count>");
						out.print(mapEntry.getValue());
						out.println("</count>");
						out.println("</cd>");
					}
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}
		out.print("</catalog>");
	}
}
