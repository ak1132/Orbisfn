package com.orbisfn.service;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {
	private HashSet<String> links;

	public CrawlerService() {
		links = new HashSet<String>();
	}

	public void getPageLinks(String URL) {
		if (!links.contains(URL)) {
			try {
				if (links.add(URL)) {
					System.out.println(URL);
				}
				Document document = Jsoup.connect(URL).get();
				org.jsoup.select.Elements linksOnPage = document.select("a[href]");
				for (Element page : linksOnPage) {
					getPageLinks(page.attr("abs:href"));
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
	}
}
