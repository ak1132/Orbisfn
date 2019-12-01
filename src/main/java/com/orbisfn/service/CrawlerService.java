package com.orbisfn.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {
	private HashSet<String> links;
	public HashMap<String, ArrayList<ArrayList<String>>> table;

	public CrawlerService() {
		links = new HashSet<String>();
		table = new HashMap<>();
	}

	public void getURL(String URL, String ticker) {
		if (!links.contains(URL)) {
			try {
				Document document = Jsoup.connect(URL).get();
				org.jsoup.select.Elements linksOnPage = document.select("a[href]");
				for (Element page : linksOnPage) {
					String url = page.attr("abs:href").trim();
					if (containsExactWord(url, ticker) && url.contains("etf")) {
						links.add(url);
						getURL(page.attr("abs:href"), ticker);
					}
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
	}

	public void getFundInfo(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements rows = doc.select("section.fund-info table tbody tr");
			ArrayList<ArrayList<String>> fundInfo = new ArrayList<>();
			for (org.jsoup.nodes.Element tr : rows) {
				ArrayList<String> row = new ArrayList<>();
				row.add(tr.select("td.label").text());
				row.add(tr.select("td.tips").text());
				row.add(tr.select("td.data").text());
				fundInfo.add(row);
			}
			table.put("fundInfo", fundInfo);
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	public void getFundCharacteristics(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements rows = doc.select("section.hidden-xs table tbody tr");
			ArrayList<ArrayList<String>> fundChars = new ArrayList<>();
			for (org.jsoup.nodes.Element tr : rows) {
				ArrayList<String> row = new ArrayList<>();
				row.add(tr.select("td.label").text());
				row.add(tr.select("td.tips").text());
				row.add(tr.select("td.data").text());
				fundChars.add(row);
			}
			table.put("fundChars", fundChars);
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	public void getFundPerformance(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements rows = doc.select("section.fund-perf table tbody tr");
			ArrayList<ArrayList<String>> fundPerf = new ArrayList<>();
			for (org.jsoup.nodes.Element tr : rows) {
				ArrayList<String> row = new ArrayList<>();
				row.add(tr.select("td.label").text());
				row.add(tr.select("td.tips").text());
				row.add(tr.select("td.data").text());
				fundPerf.add(row);
			}
			table.put("fundPerf", fundPerf);
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	public void getTopHoldings(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements h2 = doc.select("div#holdings section");
			ArrayList<ArrayList<String>> topFundHoldings = new ArrayList<>();
			ArrayList<ArrayList<String>> topIndexHoldings = new ArrayList<>();

			for (org.jsoup.nodes.Element tr : h2) {
				if (tr.text().contains("Fund Top Holdings")) {
					org.jsoup.select.Elements rows = tr.select("table tbody tr");
					for (org.jsoup.nodes.Element row : rows) {
						ArrayList<String> rowData = new ArrayList<>();
						rowData.add(row.select("td.label").text());
						rowData.add(row.select("td.weight").text());
						rowData.add(row.select("td.data").text());
						topFundHoldings.add(rowData);
					}
				} else if (tr.text().contains("Index Top Holdings")) {
					org.jsoup.select.Elements rows = tr.select("table tbody tr");
					for (org.jsoup.nodes.Element row : rows) {
						ArrayList<String> rowData = new ArrayList<>();
						rowData.add(row.select("td.label").text());
						rowData.add(row.select("td.weight").text());
						topIndexHoldings.add(rowData);
					}
				}
			}
			table.put("fundTopHoldings", topFundHoldings);
			table.put("fundIndexHoldings", topIndexHoldings);
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	public void getCountryWeights(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements h2 = doc.select("div.row section");
			ArrayList<ArrayList<String>> countryWeights = new ArrayList<>();

			for (org.jsoup.nodes.Element tr : h2) {
				if (tr.text().contains("Fund Country Weights")) {
					org.jsoup.select.Elements rows = tr.select("table tbody tr");
					for (org.jsoup.nodes.Element row : rows) {
						ArrayList<String> rowData = new ArrayList<>();
						rowData.add(row.select("td.label").text());
						rowData.add(row.select("td.data").text());
						countryWeights.add(rowData);
					}
				}
			}
			table.put("countryWeights", countryWeights);
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	public void getSectorAllocation(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements h2 = doc.select("div.row");
			ArrayList<ArrayList<String>> fundSector = new ArrayList<>();
			ArrayList<ArrayList<String>> indexSector = new ArrayList<>();

			for (org.jsoup.nodes.Element tr : h2) {
				String tag = tr.text();
				if (tag.contains("Fund Sector Allocation") || tag.contains("Index Sector Allocation")) {
					org.jsoup.select.Elements rows = tr.select("div");
					boolean flag = false;
					for (org.jsoup.nodes.Element row : rows) {
						String text = row.text();
						if (text.contains("xml") && !flag) {
							ArrayList<String> data = new ArrayList<>();
							data.add(text.substring(text.indexOf("<?xml"), text.indexOf("Index")));
							fundSector.add(data);
							flag = true;
						} else if (flag && text.contains("xml") && text.contains("Index Sector")) {
							ArrayList<String> data = new ArrayList<>();
							data.add(text.substring(text.indexOf("<?xml")));
							indexSector.add(data);
						}
					}
				}
			}
			//XMLUtils.prettyPrintXML(XMLUtils.convertStringToXMLDocument(indexSector.get(0).get(0)));
			table.put("fundSectorAllocation", fundSector);
			table.put("indexSectorAllocation", indexSector);
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	private boolean containsExactWord(String fullString, String partWord) {
		String pattern = "\\b" + partWord + "\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(fullString);
		return m.find();
	}

	public HashSet<String> getLinks() {
		return links;
	}

	public static void main(String[] args) throws IOException {
		CrawlerService service = new CrawlerService();
		String url = "https://us.spdrs.com/etf/spdr-global-dow-etf-DGT";
//		service.getCountryWeights(url);
//		service.getFundCharacteristics(url);
//		service.getFundInfo(url);
//		service.getFundPerformance(url);
		service.getSectorAllocation(url);
//		service.getTopHoldings(url);
//		for (Map.Entry<String, ArrayList<ArrayList<String>>> e : service.table.entrySet()) {
//			System.out.println(e.getKey() + " " + e.getValue().toString());
//		}
	}
}
