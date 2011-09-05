package com.vshershnev.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.vshershnev.client.UserService;
import com.vshershnev.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	
	public String getFile(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
				if (!FieldVerifier.isValidName(input)) {
					// If the input is not valid, throw an IllegalArgumentException back to
					// the client.
					throw new IllegalArgumentException("Input must be at least 1 characters long");
				}
				// Escape data from the client to avoid cross-site script vulnerabilities.
				input = escapeHtml(input);
				try{
					//Creating Scanner for file
					Scanner s = new Scanner(new File("file/Book1.csv"));
					//Creating String to return;
					String answer = "";
					//Check if there are any at all lines
					if(s.hasNext()){
						//buffer string to store current line value
						String str_buf = "";
						//Cycling through file
						while (s.hasNext()){
							str_buf = s.next();
							//Search for string sequence in line. If found add to answer string.
							if(str_buf.contains(input))
							answer += str_buf + "<br>";
						}
						//Return what we found or raise an error of no found lines.
						if(!answer.isEmpty()) return answer;
						else throw new IllegalArgumentException("No lines found");
					}
					else throw new IllegalArgumentException("File has no lines");
				}catch(FileNotFoundException fnf){
					throw new IllegalArgumentException("File not found");
				}
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
