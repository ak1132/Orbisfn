package com.orbisfn.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.orbis.utils.XMLUtils;
import com.orbisfn.entity.FundCountryWeights;
import com.orbisfn.entity.FundIndexHoldings;
import com.orbisfn.entity.FundMarketPrice;
import com.orbisfn.entity.FundPerformance;
import com.orbisfn.entity.FundTopHoldings;
import com.orbisfn.entity.Funds;
import com.orbisfn.entity.SectorAllocation;

@Service
public class CrawlerService {

	private HashSet<String> links;
	public String URL_prefix = "https://www.spdrs.com/product/fund.seam?ticker=";
	private SimpleDateFormat format;

	public CrawlerService() {
		links = new HashSet<String>();
		format = new SimpleDateFormat("mm/dd/yyyy");
	}

	public void getPageURL(String URL, String ticker) {
		if (!links.contains(URL)) {
			try {
				Document document = Jsoup.connect(URL).get();
				org.jsoup.select.Elements linksOnPage = document.select("a[href]");
				for (Element page : linksOnPage) {
					String url = page.attr("abs:href").trim();
					if (containsExactWord(url, ticker) && url.contains("etf")) {
						links.add(url);
						getPageURL(page.attr("abs:href"), ticker);
					}
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
	}

	public Funds getFundInfo(String url) {
		Funds fundInfo = new Funds();
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements rows = doc.select("section.fund-info table tbody tr");
			for (org.jsoup.nodes.Element tr : rows) {
				String label = tr.select("td.label").text();
//				String tips = tr.select("td.tips").text();
				String data = tr.select("td.data").text();
				if (label.contains("Ticker")) {
					fundInfo.setTicker(data);
				} else if (label.contains("Benchmark")) {
					fundInfo.setPrimaryBenchmark(data);
				} else if (label.contains("CUSIP")) {
					fundInfo.setCUSIP(data);
				} else if (label.contains("Options")) {
					if (data.contains("Yes")) {
						fundInfo.setOptionsAvailable(true);
					} else {
						fundInfo.setOptionsAvailable(false);
					}
				} else if (label.contains("Gross")) {
					fundInfo.setGrossExpenseRatio(Double.parseDouble(data));
				} else if (label.contains("Inception")) {
					fundInfo.setInceptionDate(format.parse(data));
				} else if (label.contains("Manager")) {
					fundInfo.setInvestmentManager(data);
				} else if (label.contains("Management")) {
					fundInfo.setManagementTeam(data);
				} else if (label.contains("Distributor")) {
					fundInfo.setDistributor(data);
				} else if (label.contains("Distribution")) {
					fundInfo.setDistributionFrequency(data);
				} else if (label.contains("Exchange")) {
					fundInfo.setExchange(data);
				}
			}
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
		return fundInfo;
	}

//	public void getFundCharacteristics(String url) {
//		try {
//			Document doc = Jsoup.connect(url).get();
//			org.jsoup.select.Elements rows = doc.select("section.hidden-xs table tbody tr");
//			ArrayList<ArrayList<String>> fundChars = new ArrayList<>();
//			for (org.jsoup.nodes.Element tr : rows) {
//				ArrayList<String> row = new ArrayList<>();
//				row.add(tr.select("td.label").text());
//				row.add(tr.select("td.tips").text());
//				row.add(tr.select("td.data").text());
//				fundChars.add(row);
//			}
//			table.put("fundChars", fundChars);
//		} catch (Exception e) {
//			System.err.println("For '" + url + "': " + e.getMessage());
//		}
//	}

	public FundPerformance getFundPerformance(String url) {
		FundPerformance fundPerf = new FundPerformance();
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements rows = doc.select("section.fund-perf table tbody tr");
			for (org.jsoup.nodes.Element tr : rows) {
				String label = tr.select("td.label").text();
				String[] data = tr.select("td.data").text().split(",");
				if (label.contains("1 Month")) {
					fundPerf.set_1Month(Double.parseDouble(data[0]));
					fundPerf.set_1Month_Q(Double.parseDouble(data[1]));
				} else if (label.contains("QTD")) {
					fundPerf.setQtd(Double.parseDouble(data[0]));
					fundPerf.setQtd_Q(Double.parseDouble(data[1]));
				} else if (label.contains("YTD")) {
					fundPerf.setYtd(Double.parseDouble(data[0]));
					fundPerf.setYtd_Q(Double.parseDouble(data[1]));
				} else if (label.contains("1 Year")) {
					fundPerf.set_1Year(Double.parseDouble(data[0]));
					fundPerf.set_1Year_Q(Double.parseDouble(data[1]));
				} else if (label.contains("3 Year")) {
					fundPerf.set_3Year(Double.parseDouble(data[0]));
					fundPerf.set_3Year_Q(Double.parseDouble(data[1]));
				} else if (label.contains("5 Year")) {
					fundPerf.set_5Year(Double.parseDouble(data[0]));
					fundPerf.set_5Year_Q(Double.parseDouble(data[1]));
				} else if (label.contains("10 Year")) {
					fundPerf.set_10year(Double.parseDouble(data[0]));
					fundPerf.set_10year_Q(Double.parseDouble(data[1]));
				} else if (label.contains("Inception")) {
					fundPerf.setInception(Double.parseDouble(data[0]));
					fundPerf.setInception_Q(Double.parseDouble(data[1]));
				}
			}
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
		return fundPerf;
	}

	public FundMarketPrice getFundMarketPrice(String url) {
		FundMarketPrice fundMarketPrice = new FundMarketPrice();
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements h2 = doc.select("section h2");
			for (org.jsoup.nodes.Element tr : h2) {
				if (tr.text().contains("Fund Market Price")) {
					org.jsoup.select.Elements rows = tr.select("table tbody tr");
					for (org.jsoup.nodes.Element row : rows) {
						String label = tr.select("td.label").text();
						String data = tr.select("td.data").text();
						if (label.contains("Bid")) {
							fundMarketPrice.setBid_ask(Double.parseDouble(data));
						} else if (label.contains("Closing")) {
							fundMarketPrice.setClosingPrice(Double.parseDouble(data));
						} else if (label.contains("Day High")) {
							fundMarketPrice.setDayHigh(Double.parseDouble(data));
						} else if (label.contains("Day Low")) {
							fundMarketPrice.setDayLow(Double.parseDouble(data));
						} else if (label.contains("Exchange Volume")) {
							fundMarketPrice.setExchangeVolume(Double.parseDouble(data));
						} else if (label.contains("Premium/Discount")) {
							fundMarketPrice.setDiscount_premium(Double.parseDouble(data));
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
		return fundMarketPrice;
	}

	public void getTopHoldings(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements h2 = doc.select("div#holdings section");
			List<FundTopHoldings> topFundHoldings = new ArrayList<>();
			List<FundIndexHoldings> topIndexHoldings = new ArrayList<>();

			for (org.jsoup.nodes.Element tr : h2) {
				if (tr.text().contains("Fund Top Holdings")) {
					org.jsoup.select.Elements rows = tr.select("table tbody tr");
					for (org.jsoup.nodes.Element row : rows) {
						FundTopHoldings holdings = new FundTopHoldings();
						holdings.setName(row.select("td.label").text());
						holdings.setWeight(Double.parseDouble(row.select("td.weight").text()));
						holdings.setSharesHeld(Integer.parseInt(row.select("td.data").text()));
						topFundHoldings.add(holdings);
					}
				} else if (tr.text().contains("Index Top Holdings")) {
					org.jsoup.select.Elements rows = tr.select("table tbody tr");
					for (org.jsoup.nodes.Element row : rows) {
						FundIndexHoldings holdings = new FundIndexHoldings();
						holdings.setName(row.select("td.label").text());
						holdings.setWeight(Double.parseDouble(row.select("td.weight").text()));
						topIndexHoldings.add(holdings);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	public void getCountryWeights(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements h2 = doc.select("div.row section");
			List<FundCountryWeights> fundCountryWeights = new ArrayList<>();
			for (org.jsoup.nodes.Element tr : h2) {
				if (tr.text().contains("Fund Country Weights")) {
					org.jsoup.select.Elements rows = tr.select("table tbody tr");
					for (org.jsoup.nodes.Element row : rows) {
						FundCountryWeights fcw = new FundCountryWeights();
						fcw.setCountry(row.select("td.label").text());
						fcw.setWeight(Double.parseDouble(row.select("td.data").text()));
						fundCountryWeights.add(fcw);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	public void getSectorAllocation(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements h2 = doc.select("div.row");
			List<SectorAllocation> sectorAllocation = new ArrayList<>();

			for (org.jsoup.nodes.Element tr : h2) {
				String tag = tr.text();
				if (tag.contains("Fund Sector Allocation") || tag.contains("Index Sector Allocation")) {
					org.jsoup.select.Elements rows = tr.select("div");
					boolean flag = false;
					for (org.jsoup.nodes.Element row : rows) {
						String text = row.text();
						if (text.contains("xml") && !flag) {
							sectorAllocation.addAll(buildSectorAllocationObject(
									text.substring(text.indexOf("<?xml"), text.indexOf("Index")), 1));
							flag = true;
						} else if (flag && text.contains("xml") && text.contains("Index Sector")) {
							sectorAllocation
									.addAll(buildSectorAllocationObject(text.substring(text.indexOf("<?xml")), 1));
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		}
	}

	private List<SectorAllocation> buildSectorAllocationObject(String xmlString, int type)
			throws DOMException, ParseException {
		NamedNodeMap node = null;
		List<SectorAllocation> sa = new ArrayList<>();
		org.w3c.dom.Document doc = XMLUtils.convertStringToXMLDocument(xmlString);
		node = doc.getAttributes();
		//node.
		return null;
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
		service.getPageURL(service.URL_prefix + "DGT", "DGT");
		HashSet<String> set = service.links;
		for (String s : set) {
			System.out.println(s);
		}
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
